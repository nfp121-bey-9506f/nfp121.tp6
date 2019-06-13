package question2;

import java.util.*;
import question1.*;

public class TestsDesVisiteurs extends junit.framework.TestCase{

    public void testACompleter(){
        try{
            GroupeDeContributeurs g1 = new GroupeDeContributeurs("groupe1");
            GroupeDeContributeurs g2 = new GroupeDeContributeurs("groupe2");
            GroupeDeContributeurs g3 = new GroupeDeContributeurs("groupe3");
            Contributeur c1 = new Contributeur("sami",100);
            Contributeur c2 = new Contributeur("joe",200);
            Contributeur c3 = new Contributeur("ahmad",300);
            Contributeur c4 = new Contributeur("sami",300);
            Contributeur c5 = new Contributeur("yara",1000);
            Contributeur c6 = new Contributeur("sara",50);
            
            //tester le visiteur CompositeValide
            g1.ajouter(c1);g1.ajouter(c2);g1.ajouter(c4);
            assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g1.accepter(new CompositeValide()));
            assertFalse("Ce compsite n'est pas valide",g2.accepter(new CompositeValide()));//g2 est vide
            
            //tester le visiteur sansdoublon
            g2.ajouter(c1);g2.ajouter(c2);g2.ajouter(c3);
            assertTrue(" Ce composite est sans doublon", g2.accepter(new SansDoublon()));
            assertFalse("Ce compsite contient des doublons",g1.accepter(new SansDoublon()));// g1 contient un doublon c1 et c4
            
            //teste le visiteur DebitMaximal
            g3.ajouter(c1);g3.ajouter(c2);g3.ajouter(c3);g3.ajouter(c5);g3.ajouter(c6);
            assertEquals("Debit maximal groupe3",g3.accepter(new DebitMaximal()),new Integer(50));
            assertEquals("Debit maximal groupe2",g2.accepter(new DebitMaximal()),new Integer(100));
            
            
        }catch(Exception e){
        }
        //fail(" cette méthode de tests, est à compléter, appels des trois visiteurs....");
    }



    public void testCompositeValide(){
        try{
            GroupeDeContributeurs g = new GroupeDeContributeurs("g");
            assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));

            GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
            g.ajouter(g1);
            assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));

            g1.ajouter(new Contributeur("c",100));
            assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));

        }catch(Exception e){
            fail("exception inattendue !!! " + e.getMessage());
        }
    }

    public void testTroisContributeursUnGroupe(){
        try{
            GroupeDeContributeurs g = new GroupeDeContributeurs("g");
            g.ajouter(new Contributeur("g_a",100));
            g.ajouter(new Contributeur("g_b",200));
            g.ajouter(new Contributeur("g_c",300));
            assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));
            assertEquals(" Revoyez DébitMaximal !!!", new Integer(100), g.accepter(new DebitMaximal()));
            GroupeDeContributeurs g1 = new GroupeDeContributeurs("g1");
            g.ajouter(g1);
            assertFalse(" Ce composite n'est pas valide, revoyez CompositeValide !!!", g1.accepter(new CompositeValide()));
        }catch(Exception e){
            fail("exception inattendue !!! " + e.getMessage());
        }
    }

    public void testUnContributeurUnGroupeAvecLeMemeNom(){
        try{
            GroupeDeContributeurs g = new GroupeDeContributeurs("g_a");
            g.ajouter(new Contributeur("g_a",100));
            g.ajouter(new Contributeur("g_b",200));
            g.ajouter(new Contributeur("g_c",300));
            g.ajouter(new Contributeur("g_d",80));
            g.ajouter(new Contributeur("g_a",80));
            assertTrue(" Ce composite est valide, revoyez CompositeValide !!!", g.accepter(new CompositeValide()));
            assertFalse(" Ce composite a au moins un doublon, revoyez SansDoublon !!!", g.accepter(new SansDoublon()));
        }catch(Exception e){
            fail("exception inattendue !!! " + e.getMessage());
        }
    }
}

