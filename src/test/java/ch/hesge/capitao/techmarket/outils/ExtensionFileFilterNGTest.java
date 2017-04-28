/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hesge.capitao.techmarket.outils;

import java.io.File;
import javax.swing.filechooser.FileFilter;
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
public class ExtensionFileFilterNGTest {
    
    ExtensionFileFilter ExfilterTest = new ExtensionFileFilter("Fichier de base de données", new String[] {"sql"});
    
    public ExtensionFileFilterNGTest() {
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
     * Test of getDescription method, of class ExtensionFileFilter.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        String expResult = "Fichier de base de données";
        String result = ExfilterTest.getDescription();
        assertSame(result, expResult);
    }

    /**
     * Test of accept method, of class ExtensionFileFilter.
     */
    @Test
    public void testAccept() {
        System.out.println("accept");
        File file = new File("./File.txt");
        boolean expResult = false;
        boolean result = ExfilterTest.accept(file);
        assertEquals(result, expResult);
        
        
        file = new File("./File.sql");
        expResult = true;
        result = ExfilterTest.accept(file);
        assertEquals(result, expResult);
    }
    
}
