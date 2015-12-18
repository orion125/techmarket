/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public TM_ComposantAsStock(int nbEnStockVirtuelle, int nbEnStockPhysique, 
            TM_Composant composantConcernee, TM_Emplacement emplacement) {
        this.nbEnStockVirtuelle = nbEnStockVirtuelle;
        this.nbEnStockPhysique = nbEnStockPhysique;
        this.composantConcernee = composantConcernee;
        this.emplacement = emplacement;
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
