/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitao.base;

import capitao.techmarket.domaine.TM_Client;
import capitao.techmarket.domaine.TM_Manager;
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
public class ManagerDao {
  
    /** Retourne la liste des manager */
    public static ArrayList getListeManager () {
      ArrayList lst = new ArrayList();
      try {
          Connection con = ConnexionBase.get();
          Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(
                  "SELECT man_username, man_password "
                + "FROM vw_manager "
                + "ORDER BY man_id"
          );
          while (rs.next()) {
              lst.add(new TM_Manager(
                      rs.getString("man_username"), 
                      rs.getString("man_password")
              ));      
          }
          stmt.close();
      } catch (SQLException ex) {
          System.err.println("ManagerDao.getListeManager(): " + ex.getMessage());
          return null;
      }
      return lst;
    } // getListeManager
    
}
 

