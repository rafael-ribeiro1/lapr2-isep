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
public class UserTest {
    
    public UserTest() {
    }

    /**
     * Test of hasEmail method, of class User.
     */
    @Test
    public void testHasEmail() {
        System.out.println("hasEmail");
        String email = "abc@t4j.com";
        User instance = new User("abc", "abc@t4j.com", "fsdjofs", User.UserType.COLLABORATOR);
        boolean expResult = true;
        boolean result = instance.hasEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of hasPassword method, of class User.
     */
    @Test
    public void testHasPassword() {
        System.out.println("hasPassword");
        String password = "fsdjofs";
        User instance = new User("abc", "abc@t4j.com", "fsdjofs", User.UserType.COLLABORATOR);
        boolean expResult = true;
        boolean result = instance.hasPassword(password);
        assertEquals(expResult, result);
    }
    
}
