package question3;
import question1.*;
import java.util.*;


public class VisiteurSauvegarder implements Visiteur<Boolean>{
    static List<Cotisant> liste = new ArrayList<Cotisant>();
    public Boolean visite(Contributeur c){
        liste.add(c);
        return true;
    }
    public Boolean visite(GroupeDeContributeurs g){
        for(int i = 0;i<g.getChildren().size();i++){
            if(liste.get(i) instanceof Contributeur){
                Contributeur c = (Contributeur) liste.get(i);
                visite(c);
            }else{
                GroupeDeContributeurs gDC = (GroupeDeContributeurs) liste.get(i);
                liste.add(gDC);
                visite(gDC);
            }
        }
        return true;
    }
}
