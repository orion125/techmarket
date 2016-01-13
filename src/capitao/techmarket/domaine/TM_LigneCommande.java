/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitao.techmarket.domaine;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author jonathan.capitao
 */
public class TM_LigneCommande {
    private TM_Composant compo;
    private int qte;

    public TM_LigneCommande(TM_Composant compo, int qte) {
        this.compo = compo;
        this.qte = qte;
    }

    public TM_Composant getCompo() {
        return compo;
    }

    public void setCompo(TM_Composant compo) {
        this.compo = compo;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    @Override
    public String toString() {
        Locale caLoc = new Locale("fr","CH");
        NumberFormat money = NumberFormat.getCurrencyInstance(caLoc); 
        return this.getCompo().getNom() + " x" + this.getQte()
                +" "+ money.format(this.getCompo().getPrix()*this.getQte());
    }

    // La vérification d'une ligne de commande avec une autre permet de voir
    // si les 2 concerne le même objet (utiliser pour le panier entre autre
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TM_LigneCommande other = (TM_LigneCommande) obj;
        if (!(this.getCompo().equals(other.getCompo()))) {
            return false;
        }
        return true;
    } 
    
    // La vérification d'une ligne de commande avec un composant
    public boolean equals(TM_Composant cmp) {
        if (cmp == null) {
            return false;
        }
        if (!(this.getCompo().equals(cmp))) {
            return false;
        }
        return true;
    }
}
