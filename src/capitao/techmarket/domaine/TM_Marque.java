package capitao.techmarket.domaine;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author jonathan.capitao
 */
public class TM_Marque {
    private int id;
    private String nom;
    public ArrayList<TM_ComposantType> compoTypes;

    public ArrayList<TM_ComposantType> getCompoTypes() {
        return compoTypes;
    }

    public TM_Marque(int id, String nom, ArrayList<TM_ComposantType> compoTypes) {
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
    
    public void setCompoType(ArrayList<TM_ComposantType> cts){
        this.compoTypes = cts;
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

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TM_Marque other = (TM_Marque) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
    
}
