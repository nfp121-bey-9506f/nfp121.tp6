package question1;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class GroupeDeContributeurs extends Cotisant implements Iterable<Cotisant>{
  private List<Cotisant> liste;
  
  public GroupeDeContributeurs(String nomDuGroupe){
    super(nomDuGroupe);
    liste = new ArrayList<Cotisant>();
  }
  
  public void ajouter(Cotisant cotisant){
    liste.add(cotisant);
  }
  
  
  public int nombreDeCotisants(){
    int nombre = 0;
    nombre = liste.size();
    return nombre;
  }
  
  public String toString(){
    String str = new String();
    str += "Groupe de contributeurs : " +"\""+ nom  +"\"" + "formé par:";
    Iterator<Cotisant> it = iterator();
    while(it.hasNext()){
        String name = it.next().nom();
        str+= "\nContributeur :" + name;
    }
    return str;
    // En utilisant Visiteur
    // VisiteurToString visiteur = new VisiteurToString();
    // String str1 = visiteur.visite(this);
    // return str1;
    
  }
  
  public List<Cotisant> getChildren(){
    return liste;
  }
  
  public void debit(int somme) throws SoldeDebiteurException{
    for(Cotisant c : liste){
        if(c instanceof Contributeur){
            Contributeur con = (Contributeur) c;
            con.debit(somme);
        }else{
            GroupeDeContributeurs g = (GroupeDeContributeurs) c;
            g.debit(somme);
        }
    }
  }
  
  public void credit(int somme){
    for(Cotisant c : liste){
        if(c instanceof Contributeur){
            Contributeur con = (Contributeur) c;
            con.credit(somme);
        }else{
            GroupeDeContributeurs g = (GroupeDeContributeurs) c;
            g.credit(somme);
        }
    }
  }
  
  public int solde(){
    int solde = 0;
    for(Cotisant c : liste){
        if(c instanceof Contributeur){
            Contributeur con = (Contributeur) c;
            solde += con.solde();
        }else{
            GroupeDeContributeurs g =(GroupeDeContributeurs) c;
            solde += g.solde();
        }
    }
    return solde;
  }
  
  // mÃ©thodes fournies
  
 public Iterator<Cotisant> iterator(){
    return new GroupeIterator(liste.iterator());
  }
  
  private class GroupeIterator implements Iterator<Cotisant>{
    private Stack<Iterator<Cotisant>> stk;
    
    public GroupeIterator(Iterator<Cotisant> iterator){
      this.stk = new Stack<Iterator<Cotisant>>();
      this.stk.push(iterator);
    }
    
    public boolean hasNext(){
      if(stk.empty()){
        return false;
      }else{
         Iterator<Cotisant> iterator = stk.peek();
         if( !iterator.hasNext()){
           stk.pop();
           return hasNext();
         }else{
           return true;
         }
      }
    }
    
    public Cotisant next(){
      if(hasNext()){
        Iterator<Cotisant> iterator = stk.peek();
        Cotisant cpt = iterator.next();
        if(cpt instanceof GroupeDeContributeurs){
          GroupeDeContributeurs gr = (GroupeDeContributeurs)cpt;
          stk.push(gr.liste.iterator());
        }
        return cpt;
      }else{
        throw new NoSuchElementException();
      }
    }
    public void remove(){
      throw new UnsupportedOperationException();
    }
  }
  

  public <T> T accepter(Visiteur<T> visiteur){
    return visiteur.visite(this);
  }
  

}
