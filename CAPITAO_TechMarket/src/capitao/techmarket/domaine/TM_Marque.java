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
public class TM_Marque {
    private int id;
    private String nom;
    public ArrayList compoTypes;

    public TM_Marque(int id, String nom, ArrayList compoTypes) {
        this.id = id;
        this.nom = nom;
        this.compoTypes = compoTypes;
    }
    
    public boolean hasTypeComposant(TM_ComposantType compoType){ 
        for (int i = 1; i <= this.compoTypes.size(); i++) {
            if (compoType == this.compoTypes.get(i)){
                return true;
            }
        }
        return false;
    }
    
    public void addCompoType(TM_ComposantType ct){
        compoTypes.add(ct);
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
