package ch.hesge.capitao.techmarket.base;

import ch.hesge.capitao.techmarket.domaine.TM_Composant;
import ch.hesge.capitao.techmarket.domaine.TM_ComposantType;
import ch.hesge.capitao.techmarket.domaine.TM_SpecificationAsValue;
import ch.hesge.capitao.techmarket.domaine.TM_Marque;
import ch.hesge.capitao.techmarket.domaine.TM_Specification;
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
  
    // Liste des composants
    public static ArrayList<TM_Composant> getListeComp () {
        ArrayList<TM_Composant> lst = new ArrayList<TM_Composant>();
        try {
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT cmp_id, cmp_nom, cmp_prix, cmp_cot_id, cmp_mar_id  "
                  + "FROM vw_composant "
            );
            while (rs.next()) {
                TM_ComposantType cot = getCompType(rs.getInt("cmp_cot_id"));
                TM_Marque mar = getMarque(rs.getInt("cmp_mar_id"));
                TM_Composant comp = new TM_Composant(
                        rs.getInt("cmp_id"),
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
    } // getListeComp
    
    // Récupère les valeurs de spécification auquel un composant est relié
    public static void setSpecAsValue(TM_Composant c){
        ArrayList<TM_SpecificationAsValue> lst = new ArrayList<TM_SpecificationAsValue>();
        try {
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT spv_id, spv_spc_id, spv_value " 
                   +"FROM vw_valeur_spec " 
                   +"OUTER JOIN vw_valeur_spec_as_compo ON cov_spv_id = spv_id " 
                   +"WHERE cov_cmp_id = "+c.getId()
            );
            while (rs.next()) {
                c.addSpecification(new TM_SpecificationAsValue(
                        rs.getInt("spv_id"),
                        getSpecForVal(rs.getInt("spv_spc_id")),
                        rs.getString("spv_value")
                ));
            }
            stmt.close(); 
        }catch (SQLException ex) {
            System.err.println("ComposantDao.setSpecAsValue(): " + ex.getMessage());
            return;  
        }
    } // setSpecAsValue
     
    // Récupère la spécification relié à un id
    public static TM_Specification getSpecForVal(int id){
        TM_Specification s = new TM_Specification(id);
        ArrayList<TM_Specification> specs = SpecificationDao.getListeSpec();
        for (TM_Specification sp : specs ){
            if (sp.equals(s)){
                s = sp;
                s.setValpos(SpecificationDao.getSpecAsValue(s));
            }
        }
        return s;
    } // getSpecForVal
    
    /** Récupère le type de composant relié à un id **/
    public static TM_ComposantType getCompType(int cotid){
        ArrayList<TM_ComposantType> cts = ComposantTypeDao.getListeCatCompo();
        for (TM_ComposantType cot : cts){
            if (cot.getId() == cotid){
                return cot;
            }
        }
        return new TM_ComposantType(-1,"inconnu");
    } // getCompType
    
    // Récupère la marque relié à un id
    public static TM_Marque getMarque(int marid){
        ArrayList<TM_Marque> marques = MarqueDao.getListeMarque();
        for (TM_Marque m : marques){
            if (m.getId() == marid){
                return m;
            }
        }
        return new TM_Marque(-1,"inconnu",new ArrayList<TM_ComposantType>());
    } // getMarque
    
    // Crée un composant 
    public static void insert (TM_Composant c) {
        try {
            Connection con = ConnexionBase.get();
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO vw_composant "
                  + "VALUES (seq_cmp_id.nextval, '"+c.getNom()+"',"
                            + c.getPrix()+", "+c.getMarque().getId()
                            + ", "+c.getCompoType().getId()+")"
            );
            stmt.executeUpdate();
            stmt.close();
            for (int i = 0; i < c.getSpecifications().size(); i++){
                PreparedStatement stmtInsertspv = con.prepareCall(
                        "INSERT INTO vw_valeur_spec_as_compo "
                      + "VALUES ("+c.getSpecifications().get(i).
                              getId()+",seq_cmp_id.currval)"
                );
                stmtInsertspv.executeUpdate();
                stmtInsertspv.close();
            }
            PreparedStatement stmtInsertStock = con.prepareCall(
                    "INSERT INTO vw_stock "
                  + "VALUES (0,0,1,seq_cmp_id.currval)"
            );
            stmtInsertStock.executeUpdate();
            stmtInsertStock.close();
        } catch (SQLException ex) {
            System.err.println("ComposantDao.insert(): " + ex.getMessage());
        }   
    } // insert
    
    // Modifie un composant 
    public static void update (TM_Composant c) {
        int step = 0;
        try {
            step++;
            Connection con = ConnexionBase.get();
            TM_ComposantType ct = c.getCompoType();       
            PreparedStatement stmt = con.prepareStatement(
                      "UPDATE vw_composant "
                      + "SET cmp_nom = '"+c.getNom()+"', "
                      + "cmp_prix = "+c.getPrix()+", "
                      + "cmp_mar_id = "+c.getMarque().getId()+", "
                      + "cmp_cot_id = "+c.getCompoType().getId()+" "
                      + "WHERE cmp_id = "+c.getId()
            );
            stmt.executeUpdate();
            stmt.close();
            step++;
            PreparedStatement stmtClean = con.prepareStatement(
                    "DELETE "
                  + "FROM vw_valeur_spec_as_compo "
                  + "WHERE cov_cmp_id = "+c.getId()
            );
            stmtClean.executeUpdate();
            stmtClean.close();
            step++;
            for (int i = 0; i < c.getSpecifications().size (); i++){
                PreparedStatement stmtInsertspv = con.prepareCall(
                        "INSERT INTO vw_valeur_spec_as_compo "
                      + "VALUES ("+c.getSpecifications().get(i).
                              getId()+","+c.getId()+")"
                );
                stmtInsertspv.executeUpdate();
                stmtInsertspv.close();
            }
        } catch (SQLException ex) {
            System.err.println("ComposantDao.update() - etape "+step+" : " + ex.getMessage());
        }   
    } // update
    
    
    // Supprime un composant 
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
            PreparedStatement stmtstock = con.prepareStatement(
                  "DELETE "
                + "FROM vw_stock "
                + "WHERE cmp_id = "+c.getId()
            );
            stmtstock.executeUpdate();
            stmtstock.close();
        } catch (SQLException ex) {
            System.err.println("ComposantDao.delete() - etape "+step+" : " + ex.getMessage());
        }   
    } // delete
} // ComposantDao
