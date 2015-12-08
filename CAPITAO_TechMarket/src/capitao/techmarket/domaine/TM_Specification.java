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
public class TM_Specification {
    private int id;
    private String nom;
    private ArrayList compoTypes;
    private ArrayList valpos;
    
    

    public TM_Specification(int id, String nom, ArrayList compoTypes ) {
        this.id = id;
        this.nom = nom;
        this.compoTypes = compoTypes;
        this.valpos = new ArrayList(); 
    }
    public TM_Specification(int id, String nom, ArrayList compoTypes, ArrayList valpos ) {
        this.id = id;
        this.nom = nom;
        this.compoTypes = compoTypes;
        this.valpos = valpos; 
    }
    
    @Override
    public String toString() {
        return nom;
    }


    public String getValMin(){
        return valpos.get(0).toString();
    }
    
    
    public String getValMax(){
        return valpos.get(valpos.size()-1).toString();
    }
    
    
    public boolean hasTypeComposant(TM_ComposantType compoType){ 
        for (int i = 1; i <= this.compoTypes.size(); i++) {
            if (compoType == this.compoTypes.get(i)){
                return true;
            }
        }
        return false;
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

    public ArrayList getCompoType() {
        return compoTypes;
    }

    public void setCompoType(ArrayList compoTypes) {
        this.compoTypes = compoTypes;
    }
    public void addCompoType(TM_ComposantType compoType) {
        this.compoTypes.add(compoType);
    }

    public ArrayList getValpos() {
        return valpos;
    }

    public void addValpos (TM_SpecificationAsValue spv) {
        this.valpos.add(spv);
    }


    
    public String printValPos(){
        String valPostoString = "";
        for (int i = 0; i < this.valpos.size();i++){
            valPostoString+= ((TM_SpecificationAsValue)valpos.get(i)).getValue()+" ";
        }
        return valPostoString;
    }
    
    public boolean hasValPos(String s){
        int pos = -1;
        for (int i = 0; i < this.valpos.size();i++){
            if (valpos.get(i).equals(s)){
                pos = i;
            }
        }
        return pos != -1;
    }
    
}
