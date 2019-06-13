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
    for(int i =0;i < liste.size();i++){
        Cotisant c = liste.get(i);
        for(int j=i+1;j<liste.size();j++){
            Cotisant c2 = liste.get(j);
            if(c.nom().equals(c2.nom())) {res =false; break;}
        }
    }
    return res ;
  }
  
}