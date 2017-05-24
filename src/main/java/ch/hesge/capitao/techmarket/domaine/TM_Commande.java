package ch.hesge.capitao.techmarket.domaine;

import java.util.ArrayList;

/**
 *
 * @author jonathan.capitao
 */
public class TM_Commande {
    private int id;
    private TM_Client cli;
    private ArrayList<TM_LigneCommande> aListComposantCommandes;
    
    private final double TVA = 1.08;
     
    public TM_Commande(int id ,TM_Client cli, ArrayList<TM_LigneCommande> aListComposantCommandes) {
        this.id = id;
        this.cli = cli;
        this.aListComposantCommandes = aListComposantCommandes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public double getValTotCommande(){
        double tot = 0.0;
        for (TM_LigneCommande lc : aListComposantCommandes){
            tot+= lc.getTot();
        }
        return tot;
    }
    
    public double getValTva(){
        return getValTotCommande()*TVA;
    }

    public TM_Client getCli() {
        return cli;
    }

    public void setCli(TM_Client cli) {
        this.cli = cli;
    }

    public ArrayList<TM_LigneCommande> getaListComposantCommandes() {
        return aListComposantCommandes;
    }

    public void setaListComposantCommandes(ArrayList<TM_LigneCommande> aListComposantCommandes) {
        this.aListComposantCommandes = aListComposantCommandes;
    }
}
