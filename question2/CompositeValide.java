package question2;

import question1.Contributeur;
import question1.GroupeDeContributeurs;
import question1.Visiteur;
import question1.Cotisant;
import java.util.List;

public class CompositeValide implements Visiteur<Boolean>{
  // Le solde de chaque contributeur doit être supérieur ou égal à 0 
  // et il n’existe pas de groupe n’ayant pas de contributeurs.
  
  public Boolean visite(Contributeur c){
      int solde = c.solde();
      if(solde<0) return false;
      return true;
  }
  
  public Boolean visite(GroupeDeContributeurs g){
    boolean res = false;
    List<Cotisant> liste = g.getChildren();
    if(liste.size()!=0) {
        for(int i = 0;i<liste.size();i++){
            Cotisant c = liste.get(i);
            if(c instanceof GroupeDeContributeurs) {
                GroupeDeContributeurs gDC = (GroupeDeContributeurs) c;
                int size = gDC.nombreDeCotisants();
                if(size!=0) {res=true;break;}
            }else{
                res=true;
                break;
            }
            
        }
    }
    return res ;
  }
}