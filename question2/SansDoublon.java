package question2;

import question1.*;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;

public class SansDoublon implements Visiteur<Boolean>{
  public Boolean visite(Contributeur c){
    int solde = c.solde();
      if(solde<0) return false;
      return true;
  }
  
  public Boolean visite(GroupeDeContributeurs g){
    boolean res = true;
    List<Cotisant> liste = g.getChildren();
    int i = 0;
    while(i < liste.size()){
        Cotisant c1 = liste.get(i);
        if(i==liste.size()-1) break;
        Cotisant c2 = liste.get(++i);
        
        if(c1.nom().equals(c2.nom())) {res =false; break;}
        
    }
    return res ;
  }
  
}