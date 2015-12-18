/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitao.base;

import capitao.techmarket.domaine.TM_Composant;
import capitao.techmarket.domaine.TM_ComposantAsStock;
import capitao.techmarket.domaine.TM_Composant;
import capitao.techmarket.domaine.TM_ComposantType;
import capitao.techmarket.domaine.TM_SpecificationAsValue;
import capitao.techmarket.domaine.TM_Marque;
import capitao.techmarket.domaine.TM_Specification;
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
public class ComposantDao {
  
    /** Liste des composants **/
    public static ArrayList<TM_Composant> getListeComp () {
        ArrayList<TM_Composant> lst = new ArrayList<TM_Composant>();
        try {
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT cmp_id, cmp_nom, cmp_prix "
                  + "FROM vw_composant "
            );
            while (rs.next()) {
                int id = rs.getInt("cmp_id");
                TM_ComposantType cot = getCompType(id);
                TM_Marque mar = getMarque(id);
                TM_Composant comp = new TM_Composant(
                        id,
                        rs.getString("cmp_nom"),
                        cot,
                        mar,
                        rs.getDouble("cmp_prix")
                );  
                lst.add(comp);
            }
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("ComposantDao.getListeComp(): " + ex.getMessage());
            return null;
        }
        return lst;
    } // getListeEmployes
    
    /** Récupère les valeurs de spécification auquel un composant est relié **/
    public static void setSpecAsValue(TM_Composant c){
        ArrayList<TM_SpecificationAsValue> lst = new ArrayList<TM_SpecificationAsValue>();
        try {
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT spv_spc_id, spv_value " 
                   +"FROM vw_valeur_spec " 
                   +"OUTER JOIN vw_valeur_spec_as_compo ON cov_spv_id = spv_id " 
                   +"WHERE cov_cmp_id = "+c.getId()
            );
            while (rs.next()) {
                c.addSpecification(new TM_SpecificationAsValue(
                        getSpecForVal(rs.getInt("spv_spc_id"))
                        ,rs.getString("spv_value")
                ));
            }
            stmt.close(); 
        }catch (SQLException ex) {
            System.err.println("ComposantDao.setSpecAsValue(): " + ex.getMessage());
            return;  
        }
    }
    
    /** Récupère la spécification relié à un id **/
    public static TM_Specification getSpecForVal(int id){
        TM_Specification s = new TM_Specification(id);
        ArrayList<TM_Specification> specs = SpecificationDao.getListeSpec();
        for (TM_Specification sp : specs ){
            if (sp.equals(s))
                s = sp;
        }
        return s;
    }
    
    /** Récupère le type de composant relié à un id **/
    public static TM_ComposantType getCompType(int cotid){
        ArrayList<TM_ComposantType> cts = ComposantTypeDao.getListeCatCompo();
        for (TM_ComposantType cot : cts){
            if (cot.getId() == cotid){
                return cot;
            }
        }
        return new TM_ComposantType(-1,"inconnu");
    }
    
    /** Récupère la marque relié à un id **/
    public static TM_Marque getMarque(int marid){
        ArrayList<TM_Marque> marques = MarqueDao.getListeMarque();
        for (TM_Marque m : marques){
            if (m.getId() == marid){
                return m;
            }
        }
        return new TM_Marque(-1,"inconnu",new ArrayList<TM_ComposantType>());
    }
    
    /** Insert un composant **/
 /*   public static void insert (TM_Composant c) {
        try {
            Connection con = ConnexionBase.get();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO vw_spec "
                  + "VALUES (seq_spc_id.nextval, '"+s.getNom()+"')"
            );
            stmt.executeUpdate();
            stmt.close();
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
            System.err.println("ComposantDao.insert(): " + ex.getMessage());
        }   
    } // insert
    
    /** Modifie un composant **/
    /*
    public static void update (TM_Composant s) {
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
            PreparedStatement stmtCleanval = con.prepareStatement(
                    "DELETE "
                  + "FROM vw_valeur_spec "
                  + "WHERE spv_spc_id = "+s.getId()
            );
            stmtCleanval.executeUpdate();
            stmtCleanval.close();
            step++;            
            insertValPos(s,false);
        } catch (SQLException ex) {
            System.err.println("ComposantDao.update() - etape "+step+" : " + ex.getMessage());
        }   
    } // update
    */
    
    /** Supprime un composant **/
    public static void delete (TM_Composant c) {
        int step = 0;
        try {
            Connection con = ConnexionBase.get(); 
            step++;
            PreparedStatement stmtCleanval = con.prepareStatement(
                    "DELETE *"
                  + "FROM vw_valeur_spec_as_compo "
                  + "WHERE cov_cmp_id = "+c.getId()
            );
            stmtCleanval.executeUpdate();
            stmtCleanval.close();
            step++;
            PreparedStatement stmt = con.prepareStatement(
                  "DELETE "
                + "FROM vw_composant "
                + "WHERE cmp_id = "+c.getId()
            );
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("ComposantDao.delete() - etape "+step+" : " + ex.getMessage());
        }   
    } // delete
}
