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
public class TM_Emplacement {
    private String Emplacement;

    public TM_Emplacement(String Emplacement) {
        this.Emplacement = Emplacement;
    }

    public String getEmplacement() {
        return Emplacement;
    }

    public void setEmplacement(String Emplacement) {
        this.Emplacement = Emplacement;
    }

    @Override
    public String toString() {
        return Emplacement;
    }
   
}
