package ch.hesge.capitao.techmarket.outils;

import ch.hesge.capitao.techmarket.base.ConnexionBase;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

/**
 * @author jonathan.capitao
 */
public class importSql {
 
    // Importe des données dans une base de données
    public static void importDB(File f) throws SQLException
    {
        // Lecture du fichier
        String s  = new String();
        StringBuffer sb = new StringBuffer();
 
        try
        {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
 
            while((s = br.readLine()) != null)
            {
                sb.append(s);
            }
            br.close();
            // On sépare chaque instruction sql avec ";" pour chaque requète
            String[] inst = sb.toString().split(";"); 
                
        // Traitement des insertion dans la base de données
            Connection con = ConnexionBase.get();
            Statement st = con.createStatement();
            for(int i = 0; i<inst.length; i++)
            {
                if(!inst[i].trim().equals(""))
                {
                    st.executeUpdate(inst[i]);
                    System.out.println(">>"+inst[i]);
                }
            }
            st.close();
        }
        catch(Exception e)
        {
            System.out.println("*** Erreur : "+e.toString());
            System.out.println("*** Erreur : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(sb.toString());
        }
 
    } // importDB   
} // importSql
