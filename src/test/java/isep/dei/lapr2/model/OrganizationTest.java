package isep.dei.lapr2.model;

import isep.dei.lapr2.model.RegistryClasses.ListTasks;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

public class OrganizationTest {

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
    public void testValidDatetime() {
        System.out.println("validDatetime");
        int day = 2;
        int hour = 12;
        int min = 5;
        Organization instance = new Organization("Candido","123456789","Eduardo","ecouto93@gmail.com","Vasco","Vasco@gmail.com");
        boolean expResult = true;
        boolean result = instance.validDatetime(day, hour, min);
        assertEquals(expResult, result);
    }




}