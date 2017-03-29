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

    // Vérifie si une spécification est relié a une catégorie de composant donnée
    public boolean hasTypeComposant(TM_ComposantType compoType){ 
        for (int i = 1; i <= this.compoTypes.size(); i++) {
            if (compoType == this.compoTypes.get(i)){
                return true;
            }
        }
        return false;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<TM_SpecificationAsValue> getValpos() {
        return valpos;
    }

    public void addValpos (TM_SpecificationAsValue spv) {
        this.valpos.add(spv);
    }
    
    // Sert à lister les valeur possible d'une spécification
    public String printValPos(){
        String valPostoString = "";
        for (int i = 0; i < this.valpos.size();i++){
            valPostoString+= ((TM_SpecificationAsValue)valpos.get(i)).getValue();
            if (i < this.valpos.size()-1){
                
                valPostoString+= ", ";
            }
        }
        return valPostoString;
    }
    
    // Vérifie une valeur est posséder par une spécification
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
