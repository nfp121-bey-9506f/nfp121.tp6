package question3;
import question1.*;
import java.util.*;

public class VisiteurRestituer implements Visiteur<Cotisant>{
    List<Cotisant> liste = VisiteurSauvegarder.liste;
    public Cotisant visite(Contributeur c){
        return c;
    }
    public Cotisant visite(GroupeDeContributeurs g){
        GroupeDeContributeurs res = new GroupeDeContributeurs(g.nom());
        for(int i =0;i<liste.size();i++) {
            if(liste.get(i) instanceof Contributeur){
                Contributeur con = (Contributeur) liste.get(i);
                Cotisant c =visite(con);
                res.ajouter(c);
                liste.remove(i);
            }
            else{
                GroupeDeContributeurs gDC = (GroupeDeContributeurs) liste.get(i);
                Cotisant c= visite(gDC);
                res.ajouter(c);
                liste.remove(i);
            }
        }
        return res;
    }
}
