package ch.hesge.capitao.techmarket.base;


import ch.hesge.capitao.techmarket.domaine.TM_Client;
// import capitao.techmarket.domaine.TM_Commande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jonathan.capitao
 */
public class ClientDao {
  
    // Retourne la liste des clients 
    public static ArrayList getListeClient () {
      ArrayList lst = new ArrayList();
      try {
          Connection con = ConnexionBase.get();
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(
                  "SELECT cli_id,cli_nom,cli_prenom, cli_telephone, cli_address,cli_mail "
                + "FROM vw_client "
          );
          while (rs.next()) {
              lst.add(new TM_Client(
                      rs.getInt("cli_id"),
                      rs.getString("cli_nom"), 
                      rs.getString("cli_prenom"),
                      rs.getString("cli_address"),
                      rs.getString("cli_telephone"),
                      rs.getString("cli_mail")
              ));      
          }
          stmt.close();
      } catch (SQLException ex) {
          System.err.println("ClientDao.getListeEmployes(): " + ex.getMessage());
          return null;
      }
      return lst;
    } // getListeClient
 
    // Ajoute un client 
    public static int insert (TM_Client client) {
        int id_empl = -1;
        try {
            Connection con = ConnexionBase.get();
            System.out.println("INSERT INTO vw_client (cli_id,cli_nom,cli_prenom,cli_telephone,cli_address,cli_mail) "
                  + "VALUES (seq_cli_id.NEXTVAL,'"+client.getNom()
                  +"','"+client.getPrenom()+"','"+client.getTelephone()
                  +"','"+client.getAddress()+"','"+client.getMail()+"')");
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO vw_client (cli_id,cli_nom,cli_prenom,cli_telephone,cli_address,cli_mail) "
                  + "VALUES (seq_cli_id.nextval,'"+client.getNom()+"','"+client.getPrenom()
                            +"','"+client.getTelephone()+"','"+client.getAddress()
                            +"','"+client.getMail()+"')"
            );            
            stmt.executeUpdate();
            ResultSet key = stmt.getGeneratedKeys(); 
            if(key.next()) {
               id_empl =  key.getInt(1);
            }
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("ClientDao.insert(): " + ex.getMessage());
            return id_empl;
        }   
        return id_empl;
    } // insert
} // ClientDao
