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
    private ArrayList<TM_ComposantType> compoTypes;
    private ArrayList<TM_SpecificationAsValue> valpos;

    public TM_Specification(int id){
        this(id,"",new ArrayList<TM_ComposantType>());
    }
    
    public TM_Specification(int id, String nom, ArrayList compoTypes ) {
        this(id, nom, compoTypes, new ArrayList<TM_SpecificationAsValue>());
    }


    public TM_Specification(int id, String nom, ArrayList<TM_ComposantType> compoTypes, ArrayList<TM_SpecificationAsValue> valpos ) {
        this.id = id;
        this.nom = nom;
        this.compoTypes = compoTypes;
        this.valpos = valpos; 
    }
    
    public void setValpos(ArrayList<TM_SpecificationAsValue> valpos) {
        this.valpos = valpos;
    }
       
    public ArrayList<TM_ComposantType> getCompoTypes() {
        return compoTypes;
    }

    public void setCompoTypes(ArrayList<TM_ComposantType> compoTypes) {
        this.compoTypes = compoTypes;
    }
    
    @Override
    public String toString() {
        return nom;
    }


    public String getValMin(){
        return getValpos().get(0).getValue();
    }
    
    
    public String getValMax(){
        return getValpos().get(valpos.size()-1).getValue();
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

    public ArrayList<TM_SpecificationAsValue> getValpos() {
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
            if (valpos.get(i).getValue().equals(s)){
                pos = i;
            }
        }
        return pos != -1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TM_Specification other = (TM_Specification) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
}
