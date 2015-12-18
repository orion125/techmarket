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
