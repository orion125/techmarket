package capitao.techmarket.domaine;

/**
 *
 * @author jonathan.capitao
 */
public class TM_Emplacement {
    private int id;
    private String Emplacement;

    public TM_Emplacement(String Emplacement) {
        this(-1,Emplacement);
    }
    public TM_Emplacement(int id, String Emplacement) {
        this.id = id;
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
    
    // Un emplacement ce vérifie avec toutes ces données
    public boolean equals(TM_Emplacement o){
        return ((this.getId() == o.getId()) || 
                (this.getEmplacement() == o.getEmplacement())
        );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
}
