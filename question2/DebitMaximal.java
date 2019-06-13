package question2;

import question1.*;
import java.util.List;


public class DebitMaximal implements Visiteur<Integer>{

  public Integer visite(Contributeur c){
    return c.solde();
  }
  
  public Integer visite(GroupeDeContributeurs g){
      if(g.accepter(new CompositeValide())){
           List<Cotisant> liste = g.getChildren();
           int maxDebitPossible = liste.get(0).solde();
           for(int i=1;i<liste.size();i++){
               if(liste.get(i) instanceof Contributeur){
                   Contributeur con = (Contributeur) liste.get(i);
                   if(maxDebitPossible > con.solde()) maxDebitPossible = con.solde();
               }else{
                   GroupeDeContributeurs gDC = (GroupeDeContributeurs) liste.get(i);
                   int maxDebitPossible1 = visite(gDC);
                   if(maxDebitPossible > maxDebitPossible1) maxDebitPossible = maxDebitPossible1;
                }
            }
           return maxDebitPossible ;
      }else throw  new RuntimeException("Composite n'est pa valide");
    }
}