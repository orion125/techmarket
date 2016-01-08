/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitao.base;

import capitao.techmarket.domaine.TM_ChangementStockEnAttente;
import capitao.techmarket.domaine.TM_Client;
import capitao.techmarket.domaine.TM_Commande;
import capitao.techmarket.domaine.TM_Composant;
import capitao.techmarket.domaine.TM_ComposantAsStock;
import capitao.techmarket.domaine.TM_Emplacement;
import capitao.techmarket.domaine.TM_LigneCommande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
            lst.add(getStockComp(c));
        }
        return lst;
    }
    
    public static TM_ComposantAsStock getStockComp (TM_Composant c) {
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
            System.err.println("CommandeStockDao.getStockComp(): " + ex.getMessage());
            return null;
        }
        return lst;
    } // setStockComp  
    
    public static void setStockComp (TM_ComposantAsStock cas){
        try {
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(
                    "UPDATE vw_stock SET cos_emp_id = "+cas.getEmplacement().getId()+" "
                  + "WHERE cos_cmp_id = "+cas.getComposantConcernee().getId()
            );
            
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("CommandeStockDao.setStockComp(): " + ex.getMessage());
        }
    }
    
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
    
    public static int creeCommande(TM_Commande com){
        int idCom = -1;
        try{
            Connection con = ConnexionBase.get();
            Statement st = con.createStatement();
            int com_id = 0;
            st.executeUpdate("INSERT INTO vw_commande "
                   + "VALUES (seq_com_id.nextval,"+com.getCli().getId()+")"
                , com_id
            );
            ResultSet generatedKeys = st.getGeneratedKeys();
            com.setId(com_id);
            
            for (TM_LigneCommande l : com.getaListComposantCommandes()){
                st.executeUpdate("INSERT INTO vw_ligne_commande VALUES ("+l.getCompo().getId()+
                        ",seq_com_id.currval,"+ l.getQte()+")");
            }
            st.close();
            System.out.println(com.getId()+" "+com_id);
            genererMoveStock(com);
        }catch (SQLException ex) {
            System.err.println("CommandeStockDao.creeCommande(): " + ex.getMessage());
            return idCom;
        }
        return idCom;
    }
       public static void genererMoveStock(TM_Commande com){
        try{
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            Date today = new Date(System.currentTimeMillis());
            DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
            for (TM_LigneCommande l : com.getaListComposantCommandes()){
                stmt.executeUpdate("INSERT INTO vw_changstockatt VALUES ("
                        +"seq_cha_id.nextval,'Vente de '"+l.getQte()+" "+l.getCompo().getNom()
                        +" pour la commande n°"+com.getId()+", 1,"+l.getQte()*-1+","
                        +"TO_DATE('"+df.format(today)
                        +"','dd-mm-yyyy'),"+l.getCompo().getId()+")");
                ResultSet rs = stmt.executeQuery("SELECT cos_nbstockvirtuel "
                        + "FROM vw_stock WHERE cos_cmp_id = "+l.getCompo().getId());
                int stockVirtuelActu = 0;
                while (rs.next()){
                    stockVirtuelActu = rs.getInt("cos_nbstockvirtuel");
                }
                stmt.executeUpdate("UPDATE vw_stock SET cos_nbstockvirtuel = "+(stockVirtuelActu+(l.getQte()*-1))
                    +" WHERE cos_cmp_id = "+l.getCompo().getId());
            }
            stmt.close() ;
        }catch (SQLException ex) {
            System.err.println("CommandeStockDao.genererMoveStock(): " + ex.getMessage());
        }
    } 
    public static void genererMoveStock(int ajout, TM_ComposantAsStock cos){
        try{
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            Date today = new Date();   
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            System.out.println(df.format(today));
            stmt.executeUpdate("INSERT INTO vw_changstockatt VALUES ("
                    +"seq_cha_id.nextval,'Achat de "+ajout+" "+cos.
                    getComposantConcernee().getNom()+"',1,"+ajout+","
                    +"TO_DATE('"+df.format(today)+"','dd-mm-yyyy'),"
                    +cos.getComposantConcernee().getId()+")");
         
            ResultSet rs = stmt.executeQuery("SELECT cos_nbstockvirtuel "
                + "FROM vw_stock WHERE cos_cmp_id = "
                    +cos.getComposantConcernee().getId());
            int stockVirtuelActu = 0;
            while (rs.next()){
                stockVirtuelActu = rs.getInt("cos_nbstockvirtuel");
            }
            stmt.executeUpdate("UPDATE vw_stock SET cos_nbstockvirtuel = "
                     +(stockVirtuelActu+(ajout))+" "
                 +"WHERE cos_cmp_id = "+cos.getComposantConcernee().getId());
            
            stmt.close() ;
        }catch (SQLException ex) {
            System.err.println("CommandeStockDao.genererMoveStock(): " + ex.getMessage());
        }
    }
    
    public static void validerMoveStockEnAtt(TM_ChangementStockEnAttente csa){
        try{
            Connection con = ConnexionBase.get();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT cos_nbstockvirtuel "
                        + "FROM vw_stock WHERE cos_cmp_id = "
                        +csa.getCompAStock().getComposantConcernee().getId());
            int stockVirtuelActu = 0;
            while (rs.next()){
                stockVirtuelActu = rs.getInt("cos_nbstockvirtuel");
            }
            stmt.executeUpdate("UPDATE vw_stock SET cos_nbstockvirtuel = "+(stockVirtuelActu+csa.getQte())
                +" WHERE cos_cmp_id = "+csa.getId());
            stmt.executeUpdate("UPDATE vw_changstockatt SET cha_etat = 0 "
                    + "WHERE cha_id ="+csa.getId());
            stmt.close();
        }catch (SQLException ex){
            System.err.println("CommandeStockDao.validerMoveStockEnAtt(): "+ex.getMessage());
        }
    }
    
    public static ArrayList<TM_ChangementStockEnAttente> recupMoveStockEnAtt(TM_Composant c){
        ArrayList<TM_ChangementStockEnAttente> lst = new ArrayList<TM_ChangementStockEnAttente>();
        try{
            Connection con = ConnexionBase.get();
            Statement st = con.createStatement();  
            ResultSet rs = st.executeQuery("SELECT cha_id, cha_comment,cha_etat,cha_qte,cha_datechange,cha_cos_cmp_id "
                    + "FROM vw_changstockatt "
                    + "WHERE cha_etat = 1 AND cha_cos_cmp_id = "+c.getId());
            ArrayList<TM_ComposantAsStock> asLst =  getAllStock();
            while (rs.next()){
                TM_ComposantAsStock asCible = null;
                for (TM_ComposantAsStock as : asLst){
                    if (as.getComposantConcernee().getId() == rs.getInt("cha_cos_cmp_id"))
                        asCible = as;
                }
                lst.add(new TM_ChangementStockEnAttente(rs.getInt("cha_id"),
                        asCible, 
                        rs.getDate("cha_datechange"), 
                        rs.getInt("cha_qte"),
                        rs.getString("cha_comment")));
            }
            st.close();
        }catch (SQLException ex) {
            System.err.println("CommandeStockDao.recupMoveStockEnAtt(): " + ex.getMessage());
            return null;
        }    
        return lst;
    }
}
