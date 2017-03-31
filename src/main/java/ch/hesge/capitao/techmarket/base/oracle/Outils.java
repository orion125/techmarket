package ch.hesge.capitao.techmarket.base.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Outils {

  // Retourne une connexion avec une base de données Oracle. 
  public static Connection connect (String nomBase) throws ClassNotFoundException, SQLException {
    Class.forName("oracle.jdbc.OracleDriver");
    Properties props = new Properties();
    props.put("user", "TechMarket_user"); props.put("password", "TechMarket_user"); props.put("charSet", "UTF-8");
    return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:" + nomBase, props);
  } // connect


} // Outils
