package question1;


public class Contributeur extends Cotisant{
  private int solde;
  
  public Contributeur(String nom, int somme){
    super(nom);
    if(somme<0) throw new RuntimeException("la somme allouée lors de la création ne peut être négative");
    solde = somme;
  }
  
  public int solde(){
    return this.solde;
  }
  
  public int nombreDeCotisants(){
    return 1;
  }
    public void debit(int somme) throws SoldeDebiteurException{
      if(somme > solde) throw new SoldeDebiteurException("la somme débitée ne peut être négative ???");
      solde -= somme; 
    }
    
    /**
     * throws RuntimeException new RuntimeException("nombre nÃ©gatif !!!");
     */
  public  void credit(int somme){
      if (somme<0) throw new RuntimeException("nombre nÃ©gatif !!!");
      solde += somme;
    }
    
    /**
     * throws RuntimeException new RuntimeException("nombre nÃ©gatif !!!");
     */
  public void affecterSolde(int somme){
    if(somme <0) throw new RuntimeException("nombre nÃ©gatif !!!");
    try{
      debit(solde()); credit(somme);// mode Ã©lÃ©gant ... 
    }catch(SoldeDebiteurException sde){ 
      // exception peu probable
      this.solde = somme; // mode efficace ...
    }
  }
  
  public <T> T accepter(Visiteur<T> visiteur){
    return visiteur.visite(this);
  }
  
  public String toString(){
    return "<Contributeur : " + nom + "," + solde + ">";
  }

}
