package capitao.techmarket.domaine;

import java.sql.Date;

/**
 *
 * @author jonathan.capitao
 */
public class TM_ChangementStockEnAttente {
    
    private int id;
    private TM_ComposantAsStock compAStock;
    private Date dateDuChangement;
    private int qte; // positive approvisionnment, négative livraison
    private String commentaire;
    private boolean etat = true;

    // Constructeur
    public TM_ChangementStockEnAttente(TM_ComposantAsStock compAStock, Date dateDuChangement, int qte, String commentaire) {
        this(-1,compAStock,dateDuChangement,qte,commentaire);
    } // TM_ChangementStockEnAttente
    
    // Constructeur
    public TM_ChangementStockEnAttente(int id , TM_ComposantAsStock compAStock, Date dateDuChangement, int qte, String commentaire) {
        this.id = id;
        this.compAStock = compAStock;
        this.dateDuChangement = dateDuChangement;
        this.qte = qte;
        this.commentaire = commentaire;        
    } // TM_ChangementStockEnAttente
    
    
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    // On crée vérifie uniquement les changements de stocks par ID
    public boolean equals (TM_ChangementStockEnAttente o){
        return (this.getId() == o.getId());
    } // equals

    @Override
    public String toString() {
        return this.getDateDuChangement() + " " + this.getCommentaire();
    }
}
