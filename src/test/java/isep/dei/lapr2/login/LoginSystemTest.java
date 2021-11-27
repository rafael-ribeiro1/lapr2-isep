/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isep.dei.lapr2.login;

import static isep.dei.lapr2.login.User.UserType.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rafael
 */
public class LoginSystemTest {
    
    public LoginSystemTest() {
    }

    /**
     * Test of newUser method, of class LoginSystem.
     */
    @Test
    public void testNewUser() {
        System.out.println("newUser");
        String userName = "abc@t4j.com";
        String userEmail = "abc";
        User.UserType userType = COLLABORATOR;
        LoginSystem instance = new LoginSystem();
        boolean expResult = true;
        boolean result = instance.newUser(userName, userEmail, userType);
        assertEquals(expResult, result);
    }

    /**
     * Test of isLoggedIn method, of class LoginSystem.
     */
    @Test
    public void testIsLoggedIn() {
        System.out.println("isLoggedIn");
        LoginSystem instance = new LoginSystem();
        boolean expResult = false;
        boolean result = instance.isLoggedIn();
        assertEquals(expResult, result);
    }

    /**
     * Test of doLogin method, of class LoginSystem.
     */
    @Test
    public void testDoLogin() {
        System.out.println("doLogin");
        String userEmail = "abc@t4j.com";
        String userPassword = "ajD1o3T";
        LoginSystem instance = new LoginSystem();
        boolean expResult = false;
        boolean result = instance.doLogin(userEmail, userPassword);
        assertEquals(expResult, result);
    }
    
}
