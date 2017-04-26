/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hesge.capitao.techmarket.outils;

import java.io.File;
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
public class FileToStrNGTest {
    
    String fileName = "./src/test/ressources/configtest.cfg";
    
    public FileToStrNGTest() {
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
     * Test of read method, of class FileToStr.
     */
    @Test
    public void testRead() {
        System.out.println("read");
                     
        
        try{FileToStr.read(fileName);} 
        catch (Exception ex){
            fail("Une exception est apparu"+ex.getMessage());
        }
        
        System.out.println(fileName);
        String result = FileToStr.read(new File("").getAbsolutePath()+fileName);
        System.out.println(FileToStr.read(new File("").getAbsolutePath()+fileName));
        
        String expResult = "";     
        assertNotSame(result, expResult);
        
        
        String expResult2 = "fdsfsdfsf3";
        assertNotSame(result, expResult2);
        
        
    }
    // demander pourquoi l'ouverture d'un fichier marche pas dans ce contexte
    
    
    /**
     * Test of write method, of class FileToStr.
     */
    @Test
    public void testWrite() {
        System.out.println("write");
        String[] str = {"PrestigeSa","14 rue de Aubépine","1215"};
        String notExcepted = FileToStr.read(new File("").getAbsolutePath()+fileName);
        try{FileToStr.write(fileName, str);} 
        catch (Exception ex){
            fail("Une exception est apparu"+ex.getMessage());
        }
        
        
        String result = FileToStr.read(new File("").getAbsolutePath()+fileName);
        
        assertNotSame(notExcepted, result);
    }
    
}
