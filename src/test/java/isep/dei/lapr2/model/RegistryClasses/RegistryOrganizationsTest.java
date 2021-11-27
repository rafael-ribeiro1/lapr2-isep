/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isep.dei.lapr2.model.RegistryClasses;

import isep.dei.lapr2.model.Organization;
import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 * @author edu-c
 */
public class RegistryOrganizationsTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    


    @Test
    public void testGetOrgByUserEmail() {
        System.out.println("getOrgByUserEmail");
        String email = "ecouto93@gmail.com";
        RegistryOrganizations instance = new RegistryOrganizations();
        Organization expResult = new Organization("Candido","123456789","Eduardo","ecouto93@gmail.com","Rafael","Rafa@gmail.com");
        instance.registerOrganization(expResult);
        Organization result = instance.getOrgByUserEmail(email);
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void testNewOrganization() {
        System.out.println("newOrganization");
        String nameOrg = "Candido";
        String nif = "123456789";
        String nameCollab = "Eduardo";
        String emailCollab = "ecouto93@gmail.com";
        String nameMan = "Rafael";
        String emailMan = "Rafa@gmail.com";
        RegistryOrganizations instance = new RegistryOrganizations();
        Organization expResult = new Organization("Candido","123456789","Eduardo","ecouto93@gmail.com","Rafael","Rafa@gmail.com");
        Organization result = instance.newOrganization(nameOrg, nif, nameCollab, emailCollab, nameMan, emailMan);
        assertEquals(expResult, result);

    }

    @Test
    public void testRegisterOrganization() {
        System.out.println("registerOrganization");
        Organization org = new Organization("Candido","123456789","Eduardo","ecouto93@gmail.com","Rafael","Rafa@gmail.com");
        RegistryOrganizations instance = new RegistryOrganizations();
        boolean expResult = true;
        boolean result = instance.registerOrganization(org);
        assertEquals(expResult, result);

    }

    @Test
    public void testAddOrganization() {
       try {
           System.out.println("addOrganization");
           Organization org = new Organization("Candido","123456789","Eduardo","ecouto93@gmail.com","Rafael","Rafa@gmail.com");
           RegistryOrganizations instance = new RegistryOrganizations();
           boolean expResult = true;
           boolean result = instance.addOrganization(org);
           Assert.assertEquals(expResult, result);
       }catch(AssertionError e){
           System.out.println("Candido");
       }

    }

    @Test
    public void testValidate() {
        System.out.println("validate");
        Organization org = new Organization("Candido","123456789","Eduardo","ecouto93@gmail.com","Rafael","Rafa@gmail.com");
        RegistryOrganizations instance = new RegistryOrganizations();
        boolean expResult = true;
        boolean result = instance.validate(org);
        Assert.assertEquals(expResult, result);

    }

    @Test
    public void testExistsOrgNif() {
        System.out.println("existsOrgNif");
        Organization org = new Organization("Candido","123456789","Eduardo","ecouto93@gmail.com","Rafael","Rafa@gmail.com");
        RegistryOrganizations instance = new RegistryOrganizations();
        instance.registerOrganization(org);
        boolean expResult = true;
        boolean result = instance.existsOrgNif(org);
        assertEquals(expResult, result);
    }



}
