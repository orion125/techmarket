/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hesge.capitao.techmarket.domaine;

import java.util.ArrayList;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Nightwing
 */
public class TM_CommandeNGTest {
    
    
        TM_Composant temp1 = new TM_Composant();
        TM_Composant temp2= new TM_Composant();
        TM_Composant temp3 = new TM_Composant();
        
        TM_Commande test1 = new TM_Commande();
        TM_Commande test2 = new TM_Commande();
        TM_Commande test3 = new TM_Commande();
        TM_Commande test4 = new TM_Commande();
    
    public TM_CommandeNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        temp1.setPrix(10.00);
        temp2.setPrix(0.00);
        temp3.setPrix(200.00);
        
        test1.getaListComposantCommandes().add(new TM_LigneCommande(temp1, 1));
        
        test2.getaListComposantCommandes().add(new TM_LigneCommande(temp1, 5));
        test2.getaListComposantCommandes().add(new TM_LigneCommande(temp3, 2));
        
        test3.getaListComposantCommandes().add(new TM_LigneCommande(temp1, 0));
        
        test4.getaListComposantCommandes().add(new TM_LigneCommande(temp2, 6));
        test4.getaListComposantCommandes().add(new TM_LigneCommande(temp2, 4));
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        test1.setaListComposantCommandes(new ArrayList<>());
        test2.setaListComposantCommandes(new ArrayList<>());
        test3.setaListComposantCommandes(new ArrayList<>());
        test4.setaListComposantCommandes(new ArrayList<>());
    }


    /**
     * Test of getValTotCommande method, of class TM_Commande.
     */
    @Test
    public void testGetValTotCommande() {
        System.out.println("getValTotCommande");
        assertEquals(test1.getValTotCommande(), 10.0,  0.0);
        assertEquals(test2.getValTotCommande(), 450.0, 0.0);
        assertEquals(test3.getValTotCommande(), 0.0,   0.0);
        assertEquals(test4.getValTotCommande(), 0.0,   0.0);
    }

    /**
     * Test of getValTva method, of class TM_Commande.
     */
    @Test
    public void testGetValTva() {
        System.out.println("getValTva");
        System.out.println(test1.getValTotCommande());
        assertEquals(test1.getValTva(), 10.8  ,0.0);
        assertEquals(test2.getValTva(), 486.0 ,0.0);
        assertEquals(test3.getValTva(), 0.0   ,0.0);
        assertEquals(test4.getValTva(), 0.0   ,0.0);
    }

    
}
