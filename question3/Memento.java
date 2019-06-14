package question3;

import question1.*;
import java.util.*;

public class Memento{
     // Note : Un usage du patron Memento, 
     //        d�un premier visiteur pour la sauvegarde et 
     //        d�un second pour la restitution du composite, 
     //        repr�sentent une solution possible. 
     
     public Memento(Cotisant c) {
         GroupeDeContributeurs g = (GroupeDeContributeurs) c;
         g.accepter(new VisiteurSauvegarder());
        }

     public void setState(Cotisant c) {
         GroupeDeContributeurs g = (GroupeDeContributeurs) c;
         g.accepter(new VisiteurRestituer());
     }
    
    }