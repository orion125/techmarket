package ch.hesge.capitao.techmarket.base;

import ch.hesge.capitao.techmarket.base.oracle.Outils;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnexionBase {

    private static final String NOM_BASE = "HEGLOCAL"; 

    private static Connection con = null; 
    private static Connection conAdm = null; 

    // Établit la connexion et affecte con. 
    private static void connect () {
        try {con = Outils.connect(NOM_BASE);}
        catch (SQLException e) {System.out.println("ConnexionBase: " + e.getMessage()); e.printStackTrace();}
        catch (ClassNotFoundException e) {System.out.println("ConnexionBase: " + e.getMessage()); e.printStackTrace();}
    } // Constructeur

    // Retourne la connexion 
    public static Connection get () {
        if (con == null) {connect();}
        return con;
    } // get

    // Ferme la connexion 
    public static void close () {
        if (con == null) {return;}
        try {con.close(); con = null; }
        catch (SQLException e) {System.out.println("ConnexionBase: " + e.getMessage()); e.printStackTrace();}
    } // close
    
  } // ConnexionBase
