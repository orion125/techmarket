/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitao.techmarket.domaine;

import java.sql.Date;

/**
 *
 * @author jonathan.capitao
 */
public class TM_ChangementStockEnAttente {
    
    private TM_ComposantAsStock compAStock;
    private Date dateDuChangement;
    private int qte; // positive approvisionnment, négative livraison
    private String commentaire;
    private boolean etat = true;

    public TM_ChangementStockEnAttente(TM_ComposantAsStock compAStock, Date dateDuChangement, int qte, String commentaire) {
        this.compAStock = compAStock;
        this.dateDuChangement = dateDuChangement;
        this.qte = qte;
        this.commentaire = commentaire;
    }
    
    public Date getDateDuChangement() {
        return dateDuChangement;
    }

    public void setDateDuChangement(Date dateDuChangement) {
        this.dateDuChangement = dateDuChangement;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
 
    public TM_ComposantAsStock getCompAStock() {
        return compAStock;
    }

    public void setCompAStock(TM_ComposantAsStock compAStock) {
        this.compAStock = compAStock;
    }   
    
    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }
}
