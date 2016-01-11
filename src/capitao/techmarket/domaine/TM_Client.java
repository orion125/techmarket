package capitao.techmarket.domaine;

/**
 *
 * @author jonathan.capitao
 */
public class TM_Client {

    private int id;
    private String prenom;
    private String nom;
    private String address;
    private String telephone;
    private String mail;

    public TM_Client(int id, String prenom, String nom, String address, String telephone, String mail) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.address = address;
        this.telephone = telephone;
        this.mail = mail;
    }
    public int getId() {
        return id;
    }

    
    public String getPrenom() {
        return prenom;
    }


    public String getNom() {
        return nom;
    }

    public String getAddress() {
        return address;
    }


    public String getTelephone() {
        return telephone;
    }

    public String getMail() {
        return mail;
    }


    @Override
    public String toString() {
        return getNom()+" "+getPrenom()+" "+getAddress(); 
    }

    @Override
    public boolean equals(Object obj) {
        TM_Client cli = (TM_Client)obj;
        return ((this.getNom().toLowerCase().equals(cli.getNom().toLowerCase())) ||
                (this.getPrenom().toLowerCase().equals(cli.getPrenom().toLowerCase())) ||
                (this.getMail().toLowerCase().equals(cli.getMail().toLowerCase())));
                        
    }
    
    
}
