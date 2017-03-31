package ch.hesge.capitao.techmarket.base;


import ch.hesge.capitao.techmarket.domaine.TM_Marque;
import ch.hesge.capitao.techmarket.domaine.TM_ComposantType;
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
public class MarqueDao {
  
    // Retourne la liste des marques 
    public static ArrayList getListeMarque () {
        ArrayList<TM_ComposantType> ar = ComposantTypeDao.getListeCatCompo();
        ArrayList lst = new ArrayList();
        try {
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT mar_id, mar_nom "
                  + "FROM vw_marque "
            );

            while (rs.next()) {
                ArrayList<TM_ComposantType> ArrayCt = new ArrayList<TM_ComposantType>();
                int id = rs.getInt("mar_id");
                ArrayList ArrayId = getCompType(id);
                for (int i = 0; i < ar.size();i++)
                    if (ArrayId.contains(ar.get(i).getId()))
                        ArrayCt.add(ar.get(i));
                lst.add(new TM_Marque(
                        id,
                        rs.getString("mar_nom"),
                        ArrayCt
                ));      
            }
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("MarqueDao.getListeMarque(): " + ex.getMessage());
            return null;
        }
        return lst;
    } // getListeMarque
    
    // Récupère la liste des marque pour une catégorie de composant donnée
    public static ArrayList getListeMarque (TM_ComposantType ct) {
        ArrayList<TM_ComposantType> ar = ComposantTypeDao.getListeCatCompo();
        ArrayList lst = new ArrayList();
        try {
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT mar_id, mar_nom "
                  + "FROM vw_marque "
                  + "JOIN vw_marque_as_categorie ON ctm_mar_id = mar_id "
                  + "WHERE ctm_cot_id = "+ct.getId()
            );

            while (rs.next()) {
                ArrayList<TM_ComposantType> ArrayCt = new ArrayList<TM_ComposantType>();
                int id = rs.getInt("mar_id");
                ArrayList ArrayId = getCompType(id);
                for (int i = 0; i < ar.size();i++)
                    if (ArrayId.contains(ar.get(i).getId()))
                        ArrayCt.add(ar.get(i));
                lst.add(new TM_Marque(
                        id,
                        rs.getString("mar_nom"),
                        ArrayCt
                ));      
            }
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("MarqueDao.getListeMarque(): " + ex.getMessage());
            return null;
        }
        return lst;
    } // getListeMarque (TM_ComposantType)
    
    // Récupère le type de composant pour une marque
    public static ArrayList getCompType(int marid){
        ArrayList lst = new ArrayList();
        try {
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT ctm_cot_id "
                  + "FROM vw_marque_as_categorie "
                  + "WHERE ctm_mar_id = "+marid
            );

            while (rs.next()) {
                lst.add(rs.getInt("ctm_cot_id"));  
            }
            stmt.close(); 
        } catch (SQLException ex) {
            System.err.println("MarqueDao.getCompType(): " + ex.getMessage());
            return null;
        }
        return lst;
    } // getCompType
   
    // Crée une marque
    public static void insert (TM_Marque m) {
        try {
            Connection con = ConnexionBase.get();
            ArrayList<TM_ComposantType> cts = m.getCompoTypes();   
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO vw_marque (mar_id,mar_nom) "
                  + "VALUES (seq_mar_id.nextval, '"+m.getNom()+"')"
            );
            stmt.executeUpdate();
            stmt.close();
            Statement stmtgmc = con.createStatement();
            for (int i = 0; i < cts.size (); i++){
                PreparedStatement stmtInsert = con.prepareCall(
                        "INSERT INTO vw_marque_as_categorie "
                      + "VALUES (seq_mar_id.currval,"+cts.get(i).getId()+")"
                );
                stmtInsert.executeUpdate();
                stmtInsert.close();
            }
        } catch (SQLException ex) {
            System.err.println("MarqueDao.insert(): " + ex.getMessage());
        }   
    } // insert
    
    // Modifier une marque
    public static void update (TM_Marque m) {
        int step = 0;
        try {
            step++;
            Connection con = ConnexionBase.get();
            ArrayList<TM_ComposantType> cts = m.getCompoTypes();       
            PreparedStatement stmt = con.prepareStatement(
                      "UPDATE vw_marque "
                      + "SET mar_nom = '"+m.getNom()+"' "
                      + "WHERE mar_id = "+m.getId()
            );
            stmt.executeUpdate();
            stmt.close();
            step++;
            PreparedStatement stmtClean = con.prepareStatement(
                    "DELETE "
                  + "FROM vw_marque_as_categorie "
                  + "WHERE ctm_mar_id = "+m.getId()
            );
            stmtClean.executeUpdate();
            stmtClean.close();
            step++;
            for (int i = 0; i < cts.size (); i++){
                PreparedStatement stmtInsert = con.prepareCall(
                        "INSERT INTO vw_marque_as_categorie "
                      + "VALUES ("+m.getId()+","+cts.get(i).getId()+")"
                );
                stmtInsert.executeUpdate();
                stmtInsert.close();
            }
        } catch (SQLException ex) {
            System.err.println("MarqueDao.update() - etape "+step+" : " + ex.getMessage());
        }   
    } // update
    
    // Supprimer une marque
    public static void delete (TM_Marque m) {
        int step = 0;
        try {
            Connection con = ConnexionBase.get(); 
            step++;
            PreparedStatement stmtClean = con.prepareStatement(
                    "DELETE "
                  + "FROM vw_marque_as_categorie "
                  + "WHERE ctm_mar_id = "+m.getId()
            );
            stmtClean.executeUpdate();
            stmtClean.close();
            step++;     
            PreparedStatement stmt = con.prepareStatement(
                        "DELETE "
                      + "FROM vw_marque "
                      + "WHERE mar_id = "+m.getId()
            );
            stmt.executeUpdate();
            stmt.close();
            step++;
        } catch (SQLException ex) {
            System.err.println("MarqueDao.delete() - etape "+step+" : " + ex.getMessage());
        }   
    } // delete
} // MarqueDao
