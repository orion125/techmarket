package capitao.base;

import capitao.techmarket.domaine.TM_ComposantType;
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
public class ComposantTypeDao {
  
    /** Retourne la liste des employés, dans l'ordre des nom et prénom. */
    public static ArrayList<TM_ComposantType> getListeCatCompo () {
      ArrayList lst = new ArrayList();
      try {
          Connection con = ConnexionBase.get();
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(
                  "SELECT cot_id, cot_nom "
                + "FROM vw_categorie_compo "
          );
          while (rs.next()) {
              lst.add(new TM_ComposantType(
                      rs.getInt("cot_id"),
                      rs.getString("cot_nom")
              ));      
          }
          stmt.close();
      } catch (SQLException ex) {
          System.err.println("ComposantTypeDao.getListeCatCompo(): " + ex.getMessage());
          return null;
      }
      return lst;
    } // getListeEmployes
 
    
    
    public static void insert (TM_ComposantType ct) {
        try {
            Connection con = ConnexionBase.get();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO vw_categorie_compo "
                  + "VALUES (seq_cot_id.nextval, '"+ct.getNom()+"')"
            );
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("ComposantTypeDao.insert(): " + ex.getMessage());
        }   
    } // insert
    
    public static void update (TM_ComposantType ct) {
        try {
            Connection con = ConnexionBase.get();
            System.out.println("UPDATE vw_categorie_compo "
                      + "SET cot_nom = '"+ct.getNom()+"' "
                      + "WHERE cot_id = "+ct.getId());
                    
            PreparedStatement stmt = con.prepareStatement(
                      "UPDATE vw_categorie_compo "
                      + "SET cot_nom = '"+ct.getNom()+"' "
                      + "WHERE cot_id = "+ct.getId()
            );
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("ComposantTypeDao.update(): " + ex.getMessage());
        }   
    } // update
    
  
    
}
