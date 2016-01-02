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
}
