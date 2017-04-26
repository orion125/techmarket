/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hesge.capitao.techmarket.domaine;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author JonathanCapitao
 */
public class TM_ClientNGTest {
    
    TM_Client clientTest = new TM_Client(1, "prenom", "nom", "address", "1212121", "prenomnom@gmail.com");
    
    public TM_ClientNGTest() {
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
     * Test of getId method, of class TM_Client.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        int expResult = 1;
        int result = clientTest.getId();
        assertSame(result, expResult);
    }

    /**
     * Test of getPrenom method, of class TM_Client.
     */
    @Test
    public void testGetPrenom() {
        System.out.println("getPrenom");
        String expResult = "";
        String result = clientTest.getPrenom();
        assertNotSame(result, expResult);
    }

    /**
     * Test of getNom method, of class TM_Client.
     */
    @Test
    public void testGetNom() {
        System.out.println("getNom");
        String expResult = "";
        String result = clientTest.getNom();
        assertNotSame(result, expResult);
    }

    /**
     * Test of getAddress method, of class TM_Client.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        String expResult = "";
        String result = clientTest.getAddress();
        assertNotSame(result, expResult);
    }

    /**
     * Test of getTelephone method, of class TM_Client.
     */
    @Test
    public void testGetTelephone() {
        System.out.println("getTelephone");
        String expResult = "";
        String result = clientTest.getTelephone();
        assertNotSame(result, expResult);
    }

    /**
     * Test of getMail method, of class TM_Client.
     */
    @Test
    public void testGetMail() {
        System.out.println("getMail");
        String expResult = "";
        String result = clientTest.getMail();
        assertNotSame(result, expResult);
    }

    /**
     * Test of toString method, of class TM_Client.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "";
        String result = clientTest.toString();
        assertNotSame(result, expResult);
    }

    /**
     * Test of equals method, of class TM_Client.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj1 = new TM_Client(1, "prenomr", "nom", "address", "1212121", "prenomnom@gmail.com");
        Object obj2 = new TM_Client(1, "prenom", "nomd", "address", "1212121", "prenomnom@gmail.com");
        Object obj3 = new TM_Client(1, "prenom", "nom", "address", "1212121", "prenomnom@gmfcail.com");
        Object objOk = new TM_Client(1, "prenom", "nom", "address", "1212121", "prenomnom@gmail.com");
        boolean expResult = false;
        boolean result1 = clientTest.equals(obj1);
        assertSame(result1, expResult);
        
        
        boolean result2 = clientTest.equals(obj2);
        assertSame(result2, expResult);
        
        
        boolean result3 = clientTest.equals(obj3);
        assertSame(result3, expResult);
        
        
        boolean resultOk = clientTest.equals(objOk);
        assertNotSame(resultOk, expResult);
    }
    
}
