/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isep.dei.lapr2.login;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rafael
 */
public class UserSessionTest {
    
    public UserSessionTest() {
    }

    /**
     * Test of isLoggedIn method, of class UserSession.
     */
    @Test
    public void testIsLoggedIn() {
        System.out.println("isLoggedIn");
        UserSession instance = new UserSession();
        boolean expResult = false;
        boolean result = instance.isLoggedIn();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLoggedUser method, of class UserSession.
     */
    @Test
    public void testGetLoggedUser() {
        System.out.println("getLoggedUser");
        UserSession instance = new UserSession();
        User user = new User("abc", "abc@t4j.com", "fsdjofs", User.UserType.COLLABORATOR);
        instance.doLogin(user);
        User expResult = user;
        User result = instance.getLoggedUser();
        assertEquals(expResult, result);
    }
    
}
