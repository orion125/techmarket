/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitao.base;

import capitao.techmarket.domaine.TM_Composant;
import capitao.techmarket.domaine.TM_ComposantAsStock;
import capitao.techmarket.domaine.TM_Emplacement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jonathan.capitao
 */
public class CommandeStockDao {
    
    public static ArrayList<TM_ComposantAsStock> getAllStock(){
        ArrayList<TM_Composant> cmpLst = ComposantDao.getListeComp();
        ArrayList<TM_ComposantAsStock> lst = new ArrayList<TM_ComposantAsStock>();
        for (TM_Composant c : cmpLst){
            lst.add(setStockComp(c));
        }
        return lst;
    }
    
    public static TM_ComposantAsStock setStockComp (TM_Composant c) {
        TM_ComposantAsStock lst = new TM_ComposantAsStock();
        try {
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT cos_nbstockvirtuel, cos_nbstockphysique, cos_emp_id,cos_cmp_id "
                  + "FROM vw_stock "
                  + "WHERE cos_cmp_id = "+c.getId()
            );
            while (rs.next()) {
                lst = new TM_ComposantAsStock(
                        rs.getInt("cos_nbstockvirtuel"),
                        rs.getInt("cos_nbstockphysique"), 
                        c,
                        getEmplaceComp(rs.getInt("cos_emp_id"))
                );      
            }
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("CommandeStockDao.setStockComp(): " + ex.getMessage());
            return null;
        }
        return lst;
    } // setStockComp  
    
    public static TM_Emplacement getEmplaceComp (int empId){
        ArrayList<TM_Emplacement> lst = getEmplacements();
        for (TM_Emplacement e : lst){
            if (e.getId() == empId){
                return e;
            }
        }
        return new TM_Emplacement(-1, "Innexistant");
    }
    
    public static ArrayList<TM_Emplacement> getEmplacements (){
        ArrayList<TM_Emplacement> lst = new ArrayList<TM_Emplacement>();
          try {
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT emp_id, emp_nom "
                  + "FROM vw_emplacement "
            );
            while (rs.next()) {
                lst.add(new TM_Emplacement(
                        rs.getInt("emp_id"),
                        rs.getString("emp_nom")
                ));      
            }
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("CommandeStockDao.getEmplacements(): " + ex.getMessage());
            return null;
        }
        return lst;
    }
}
