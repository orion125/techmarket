package capitao.techmarket.domaine;

/**
 *
 * @author jonathan.capitao
 */
public class TM_ComposantAsStock {
    
    private int nbEnStockVirtuelle;
    private int nbEnStockPhysique;
    private TM_Composant composantConcernee;
    private TM_Emplacement emplacement;

    public TM_ComposantAsStock(){
        this(0,0,null,null);
    }
    
    public TM_ComposantAsStock(int nbEnStockVirtuelle, int nbEnStockPhysique, 
            TM_Composant composantConcernee, TM_Emplacement emplacement) {
        this.nbEnStockVirtuelle = nbEnStockVirtuelle;
        this.nbEnStockPhysique = nbEnStockPhysique;
        this.composantConcernee = composantConcernee;
        this.emplacement = emplacement;
    }

    @Override
    public String toString() {
        return this.getComposantConcernee().getNom() ;
    }

    public int getNbEnStockVirtuelle() {
        return nbEnStockVirtuelle;
    }

    public void addStockVirtuelle(int nbAdd) {
        this.nbEnStockVirtuelle += nbAdd;
    }
    
    public void soustStockVirtuelle(int nbSoust) {
        this.nbEnStockVirtuelle -= nbSoust;
    }


    public int getNbEnStockPhysique() {
        return nbEnStockPhysique;
    }

    public void addStockPhysique(int nbAdd) {
        this.nbEnStockPhysique += nbAdd;
    } 
    
    public void soustStockPhysique(int nbSoust) {
        this.nbEnStockPhysique -= nbSoust;
    }
    
    public TM_Composant getComposantConcernee() {
        return composantConcernee;
    }
    
    public void setComposantConcernee(TM_Composant composantConcernee) {
        this.composantConcernee = composantConcernee;
    }
    
    public TM_Emplacement getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(TM_Emplacement emplacement) {
        this.emplacement = emplacement;
    }
}
