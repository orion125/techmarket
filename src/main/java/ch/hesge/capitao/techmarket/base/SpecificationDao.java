package ch.hesge.capitao.techmarket.base;

import ch.hesge.capitao.techmarket.domaine.TM_Specification;
import ch.hesge.capitao.techmarket.domaine.TM_ComposantType;
import ch.hesge.capitao.techmarket.domaine.TM_SpecificationAsValue;
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
public class SpecificationDao {
  
    // Retourne la liste des spécification 
    public static ArrayList getListeSpec () {
        ArrayList<TM_ComposantType> ar = ComposantTypeDao.getListeCatCompo();
        ArrayList lst = new ArrayList();
        try {
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT spc_id, spc_nom "
                  + "FROM vw_spec "
            );

            while (rs.next()) {
                ArrayList<TM_ComposantType> ArrayCt = new ArrayList<TM_ComposantType>();
                int id = rs.getInt("spc_id");
                ArrayList ArrayId = getCompType(id);
                for (int i = 0; i < ar.size();i++)
                    if (ArrayId.contains(ar.get(i).getId()))
                        ArrayCt.add(ar.get(i));
                TM_Specification spec = new TM_Specification(
                        id,
                        rs.getString("spc_nom"),
                        ArrayCt
                );
                getSpecAsValue(spec);
                lst.add(spec);  
            }
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("SpecificationDao.getListeMarque(): " + ex.getMessage());
            return null;
        }
        return lst;
    } // getListeSpec
    
    // Retourne la liste des spécifications pour une catégorie de composant donnée
    public static ArrayList<TM_Specification> getListeSpec(TM_ComposantType ct){
        ArrayList<TM_ComposantType> ar = ComposantTypeDao.getListeCatCompo();
        ArrayList lst = new ArrayList();
        try {
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT spc_id, spc_nom "
                  + "FROM vw_spec "
                  + "JOIN vw_spec_as_categorie ON cts_spc_id = spc_id "
                  + "WHERE cts_cot_id = "+ct.getId()
            );

            while (rs.next()) {
                ArrayList<TM_ComposantType> ArrayCt = new ArrayList<TM_ComposantType>();
                int id = rs.getInt("spc_id");
                ArrayList ArrayId = getCompType(id);
                for (int i = 0; i < ar.size();i++)
                    if (ArrayId.contains(ar.get(i).getId()))
                        ArrayCt.add(ar.get(i));
                TM_Specification spec = new TM_Specification(
                        id,
                        rs.getString("spc_nom"),
                        ArrayCt
                );
                spec.setValpos(getSpecAsValue(spec));
                lst.add(spec);  
            }
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("SpecificationDao.getListeMarque(): " + ex.getMessage());
            return null;
        }
        return lst;
    } // getListeSpec (TM_ComposantType)
    
    // Récupère les valeurs d'une spécification
    public static ArrayList<TM_SpecificationAsValue> getSpecAsValue(TM_Specification spec){
        ArrayList<TM_SpecificationAsValue> lst = new ArrayList<TM_SpecificationAsValue>();
        try {
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT spv_id, spv_value "
                  + "FROM vw_valeur_spec "
                  + "WHERE spv_spc_id = "+spec.getId()
            );

            while (rs.next()) {
                lst.add(new TM_SpecificationAsValue(rs.getInt("spv_id"),spec, rs.getString("spv_value")));  
            }
            stmt.close(); 
        }catch (SQLException ex) {
            System.err.println("SpecificationDao.setSpecAsValue(): " + ex.getMessage());
            return null;  
        }
        return lst;
    } // getSpecAsValue
    
    // Récupère les catégories de composant disponible pour une spécification
    public static ArrayList getCompType(int specId){
        ArrayList lst = new ArrayList();
        try {
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT cts_cot_id "
                  + "FROM vw_spec_as_categorie "
                  + "WHERE cts_spc_id = "+specId
            );

            while (rs.next()) {
                lst.add(rs.getInt("cts_cot_id"));  
            }
            stmt.close(); 
        } catch (SQLException ex) {
            System.err.println("SpecificationDao.getCompType(): " + ex.getMessage());
            return null;
        }
        return lst;
    } // getCompType
   
    // Crée une spécification
    public static void insert (TM_Specification s) {
            int step = 1;
        try {
            Connection con = ConnexionBase.get();
            ArrayList<TM_ComposantType> cts = s.getCompoTypes();  
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO vw_spec "
                  + "VALUES (seq_spc_id.nextval, '"+s.getNom()+"')"
            );
            System.out.println("INSERT INTO vw_spec "
                  + "VALUES (seq_spc_id.nextval, '"+s.getNom()+"')");
            stmt.executeUpdate();
            stmt.close();
            step++;
            for (int i = 0; i < cts.size (); i++){
                PreparedStatement stmtInsertc = con.prepareCall(
                        "INSERT INTO vw_spec_as_categorie "
                      + "VALUES (seq_spc_id.currval,"+cts.get(i).getId()+")"
                );
                stmtInsertc.executeUpdate();
                stmtInsertc.close();
            }
            insertValPos(s,true);
        } catch (SQLException ex) {
            System.err.println("SpecificationDao.insert(): " +step+" "+ ex.getMessage());
        }   
    } // insert
    
    // Insert dans la base de données les valeurs possibles pour une spécification.
    public static void insertValPos(TM_Specification s, boolean isInsert){
        try {
            Connection con = ConnexionBase.get();
            String modResult = (isInsert)? "seq_spc_id.currval": String.valueOf(s.getId());
            ArrayList<TM_SpecificationAsValue> vals = s.getValpos();
            System.out.println(vals.toArray().toString());
            for (int i = 0; i < s.getValpos().size(); i++){
                PreparedStatement stmtInsertv = con.prepareCall(
                        "INSERT INTO vw_valeur_spec "
                      + "VALUES (seq_spv_id.nextval,"+ modResult
                      +",'"+vals.get(i).getValue()+"')"
                );
                stmtInsertv.executeUpdate();
                stmtInsertv.close();
            }
        } catch (SQLException ex) {
            System.err.println("SpecificationDao.insertValPos(): " + ex.getMessage());
        }   
    } // insertValPos
    
    // Modifie une spécification
    public static void update (TM_Specification s) {
        int step = 0;
        try {
            step++;
            Connection con = ConnexionBase.get();
            ArrayList<TM_ComposantType> cts = s.getCompoTypes();       
            PreparedStatement stmt = con.prepareStatement(
                      "UPDATE vw_spec "
                      + "SET spc_nom = '"+s.getNom()+"' "
                      + "WHERE spc_id = "+s.getId()
            );
            stmt.executeUpdate();
            stmt.close();
            step++;
            PreparedStatement stmtClean = con.prepareStatement(
                    "DELETE "
                  + "FROM vw_spec_as_categorie "
                  + "WHERE cts_spc_id = "+s.getId()
            );
            stmtClean.executeUpdate();
            stmtClean.close();
            step++;
            for (int i = 0; i < cts.size (); i++){
                PreparedStatement stmtInsert = con.prepareCall(
                        "INSERT INTO vw_spec_as_categorie "
                      + "VALUES ("+s.getId()+","+cts.get(i).getId()+")"
                );
                stmtInsert.executeUpdate();
                stmtInsert.close();
            }
            step++;
            // On vérifie ici si la valeur de la spécification éxistait 
            // déjà pour éviter de la réinsérer pour rien.
            ArrayList<TM_SpecificationAsValue> als = getSpecAsValue(s);
            ArrayList<TM_SpecificationAsValue> alsNew = new ArrayList<TM_SpecificationAsValue>();
            for (TM_SpecificationAsValue spvmain : s.getValpos()){
                boolean test = true;
                for (TM_SpecificationAsValue spv : als){
                    if (spv.equals(spvmain)) test = false;
                }
                if (test) alsNew.add(spvmain); 
            }
            // On utilise un ArrayList temporaire pour préparer l'insertion puis on insert.
            ArrayList<TM_SpecificationAsValue> temp = s.getValpos();
            s.setValpos(alsNew);
            step++;            
            insertValPos(s,false);
            s.setValpos(temp);
        } catch (SQLException ex) {
            System.err.println("SpecificationDao.update() - etape "+step+" : " + ex.getMessage());
        }   
    } // update
    
    // Supprime une spécification
    public static void delete (TM_Specification s) {
        int step = 0;
        try {
            Connection con = ConnexionBase.get(); 
            Statement stSelect = con.createStatement();
            ResultSet rs = stSelect.executeQuery(
                    "SELECT spv_id "
                  + "FROM vw_valeur_spec "
                  + "WHERE spv_spc_id = "+s.getId());
            ArrayList alId = new ArrayList();
            Statement stDelete = con.createStatement();
            while (rs.next()) {
                stDelete.executeUpdate("DELETE "
                        +"FROM vw_valeur_spec_as_compo "
                        +"WHERE cov_spv_id="+rs.getInt("spv_id"));  
            }
            stDelete.close();
            step++;
            PreparedStatement stmtCleanval = con.prepareStatement(
                    "DELETE "
                  + "FROM vw_valeur_spec "
                  + "WHERE spv_spc_id = "+s.getId()
            );
            stmtCleanval.executeUpdate();
            stmtCleanval.close();
            step++;
            PreparedStatement stmtClean = con.prepareStatement(
                    "DELETE "
                  + "FROM vw_spec_as_categorie "
                  + "WHERE cts_spc_id = "+s.getId()
            );
            stmtClean.executeUpdate();
            stmtClean.close();
            step++;  
              
            PreparedStatement stmt = con.prepareStatement(
                  "DELETE "
                + "FROM vw_spec "
                + "WHERE spc_id = "+s.getId()
            );
            stmt.executeUpdate();
            stmt.close();
            step++;
        } catch (SQLException ex) {
            System.err.println("SpecificationDao.delete() - etape "+step+" : " + ex.getMessage());
        }   
    } // delete
} // SpecificationDao
