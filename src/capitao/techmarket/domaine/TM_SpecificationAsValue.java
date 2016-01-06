/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitao.techmarket.domaine;

import java.util.Objects;

/**
 *
 * @author jonathan.capitao
 */
public class TM_SpecificationAsValue {
    private TM_Specification spec;
    private int id;

    private String value;

    public TM_SpecificationAsValue(int id, TM_Specification spec, String value) {
        this.id = id;
        this.spec = spec;
        this.value = value;
    }

    public int getId() {
        return id;
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TM_Specification getSpec() {
        return spec;
    }

    public void setSpec(TM_Specification spec) {
        this.spec = spec;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.spec);
        hash = 79 * hash + Objects.hashCode(this.value);
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
        final TM_SpecificationAsValue other = (TM_SpecificationAsValue) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }
    
    public boolean equals(String s) {
        if (s == "") {
            return false;
        }
        if (!this.getValue().equals(s)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return spec.toString()+" "+this.getValue(); 
    }
    
    
    
}
