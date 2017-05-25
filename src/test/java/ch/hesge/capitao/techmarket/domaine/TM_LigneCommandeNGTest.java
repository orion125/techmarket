/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hesge.capitao.techmarket.domaine;

import java.awt.Component;
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
public class TM_LigneCommandeNGTest {
    
    public TM_LigneCommandeNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }


    /**
     * Test of getTot method, of class TM_LigneCommande.
     */
    @Test
    public void testGetTot() {
        System.out.println("getTot");
        TM_Composant temp1 = new TM_Composant();
        temp1.setPrix(55.10);
        TM_Composant temp2= new TM_Composant();
        temp2.setPrix(0.00);
        TM_LigneCommande test1 = new TM_LigneCommande(temp1, 5);
        TM_LigneCommande test2 = new TM_LigneCommande(temp1, 0);
        TM_LigneCommande test3 = new TM_LigneCommande(temp2, 2);
        TM_LigneCommande test4 = new TM_LigneCommande(temp2, 0);
        assertEquals(275.50, test1.getTot(), 0);
        assertEquals(0, test2.getTot(), 0);
        assertEquals(0, test3.getTot(), 0);
        assertEquals(0, test4.getTot(), 0);
    }

 
    
}
