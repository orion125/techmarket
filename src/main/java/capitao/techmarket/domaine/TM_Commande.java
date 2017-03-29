package capitao.techmarket.domaine;

import java.util.ArrayList;

/**
 *
 * @author jonathan.capitao
 */
public class TM_Commande {
    private int id;
    private TM_Client cli;
    private ArrayList<TM_LigneCommande> aListComposantCommandes;
     
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
            tot+= lc.getCompo().getPrix()*lc.getQte();
        }
        return tot;
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
