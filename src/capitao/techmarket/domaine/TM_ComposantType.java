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
    
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Pour une catégorie de composant on peut vérifie avec le nom
    public boolean equals (String nom){
        if (this.getNom().equals(nom)){
            return true;
        }else{
            return false;
        }
    } 
    
    // Ou avec une catégorie de composant complête
    public boolean equals (TM_ComposantType cp){
        if (this.getNom().equals(cp.getNom())){
            return true;
        }else{
            return false;
        }
    }
    
    // Ou encore avec l'id uniquement
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
