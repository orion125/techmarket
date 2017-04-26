/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hesge.capitao.techmarket.view;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
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
public class TM_LoginNGTest {
    
    public TM_LoginNGTest() {
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


    @Test
    public void testLoginActivation() {
        System.out.println("loginActivation");
        TM_Login instance = TM_Login.getInstance();
        instance.getJtf_Username().setText(" test");
        instance.getJpw_Password().setText("");
        instance.loginActivation();
        if (instance.getJbtLogin().isEnabled())
            fail("Login button must be disabled when one ore more field is empty.");
        
        
        instance.getJtf_Username().setText("");
        instance.getJpw_Password().setText("53");
        instance.loginActivation();
        if (instance.getJbtLogin().isEnabled())
            fail("Login button must be disabled when one ore more field is empty.");
        
        
        instance.getJtf_Username().setText("");
        instance.getJpw_Password().setText("");
        instance.loginActivation();
        if (instance.getJbtLogin().isEnabled())
            fail("Login button must be disabled when one ore more field is empty.");
        
        
        instance.getJtf_Username().setText("Admin");
        instance.getJpw_Password().setText("123");
        instance.loginActivation();
        if (!instance.getJbtLogin().isEnabled())
            fail("Login button must be enabled when all field have content.");
        
    }

    /**
     * Test of infoCorrect method, of class TM_Login.
     */
    @Test
    public void testInfoCorrect() {
        System.out.println("infoCorrect");
        TM_Login instance = TM_Login.getInstance();
        
        instance.getJtf_Username().setText("Admin");
        instance.getJpw_Password().setText("1234");
        boolean expResult = true;
        boolean result = instance.infoCorrect();
        assertEquals(expResult, result);
    }
    

    


    
}
