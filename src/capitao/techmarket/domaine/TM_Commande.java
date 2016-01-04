/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        for (int i = 0; i < aListComposantCommandes.size();i++){
            tot=+ aListComposantCommandes.get(i).getCompo().getPrix();
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
