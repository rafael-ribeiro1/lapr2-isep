/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isep.dei.lapr2.login;

import isep.dei.lapr2.login.User.UserType;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rafael
 */
public class RegistryUsersTest {
    
    public RegistryUsersTest() {
    }

    /**
     * Test of newUser method, of class RegistryUsers.
     */
    @Test
    public void testNewUser() {
        System.out.println("newUser");
        User user = new User("abc", "abc@t4j.com", "Ksa32ks", UserType.COLLABORATOR);
        RegistryUsers instance = new RegistryUsers();
        boolean expResult = true;
        boolean result = instance.newUser(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of validatesUser method, of class RegistryUsers.
     */
    @Test
    public void testValidatesUser() {
        System.out.println("validatesUser");
        User user = new User("abc", "abc@t4j.com", "Ksa32ks", UserType.COLLABORATOR);;
        RegistryUsers instance = new RegistryUsers();
        boolean expResult = true;
        boolean result = instance.validatesUser(user);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserByEmail method, of class RegistryUsers.
     */
    @Test
    public void testGetUserByEmail() {
        System.out.println("getUserByEmail");
        String email = "abc@t4j.com";
        RegistryUsers instance = new RegistryUsers();
        User user = new User("abc", "abc@t4j.com", "fsdjofs", UserType.COLLABORATOR);
        instance.newUser(user);
        User expResult = user;
        User result = instance.getUserByEmail(email);
        assertEquals(expResult, result);
    }
    
}
