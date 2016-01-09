package capitao.techmarket.domaine;

/**
 *
 * @author jonathan.capitao
 */
public class TM_ComposantType {  
    private int id;
    private String nom;
    
    public TM_ComposantType(String nom) {
        this.id = -1;
        this.nom = nom;
    }
    
    public TM_ComposantType(int id ,String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
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


    public boolean equals (String nom){
        if (this.getNom().equals(nom)){
            return true;
        }else{
            return false;
        }
    } 
    public boolean equals (TM_ComposantType cp){
        if (this.getNom().equals(cp.getNom())){
            return true;
        }else{
            return false;
        }
    }
  
    public boolean equals (int id){
        if (this.getId()== id){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return nom;
    }
    
    
}
