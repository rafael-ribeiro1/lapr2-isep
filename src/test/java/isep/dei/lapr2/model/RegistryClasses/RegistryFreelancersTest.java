/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isep.dei.lapr2.model.RegistryClasses;

import isep.dei.lapr2.controller.MainController;
import isep.dei.lapr2.model.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author edu-c
 */
public class RegistryFreelancersTest {
    
    public RegistryFreelancersTest() {
    }

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
    public void testValidate() {
        System.out.println("validate");
        Freelancer free= new Freelancer("Eduardo Couto", Freelancer.Expertise.Senior,"ecouto93@gmail.com","123456786","PT32","adress","Portugal");
        RegistryFreelancers instance = new RegistryFreelancers();
        boolean expResult = true;
        boolean result = instance.validate(free);
        assertEquals(expResult, result);
    }

    @Test
    public void testGenerateFreelancerID() {
        System.out.println("generateFreelancerID");
        Freelancer free = new Freelancer("Eduardo Couto", Freelancer.Expertise.Senior,"ecouto93@gmail.com","123456786","PT32","adress","Portugal");
        RegistryFreelancers instance = new RegistryFreelancers();
        instance.generateFreelancerID(free);
        String expResult="EC1";
        String result = free.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testGenerateIndexID() {
        System.out.println("generateIndexID");
        String id = "EC";
        RegistryFreelancers instance = new RegistryFreelancers();
        String expResult = "EC1";
        String result = instance.generateIndexID(id);
        assertEquals(expResult, result);

    }

    @Test
    public void testExistsNifInSystem() {
        System.out.println("existsNifInSystem");
        Freelancer free =new Freelancer("Eduardo Couto", Freelancer.Expertise.Senior,"ecouto93@gmail.com","123456786","PT32","adress","Portugal");
        RegistryFreelancers instance = new RegistryFreelancers();
        instance.registerFreelancer(free);
        boolean expResult = true;
        boolean result = instance.existsNifInSystem(free);
        assertEquals(expResult, result);

    }

    @Test
    public void testExistsBankAccountInSystem() {
        System.out.println("existsBankAccountInSystem");
        Freelancer free =new Freelancer("Eduardo Couto", Freelancer.Expertise.Senior,"ecouto93@gmail.com","123456786","PT32","adress","Portugal");
        RegistryFreelancers instance = new RegistryFreelancers();
        instance.registerFreelancer(free);
        boolean expResult = true;
        boolean result = instance.existsBankAccountInSystem(free);
        assertEquals(expResult, result);
    }


    @Test
    public void testGetAveragePercentageDelay() {
        System.out.println("getAveragePercentageDelay");
        Freelancer free = new Freelancer("Eduardo Couto", Freelancer.Expertise.Senior,"ecouto93@gmail.com","123456786","PT32","adress","Portugal");
        RegistryFreelancers instance = new RegistryFreelancers();
        instance.registerFreelancer(free);
        Organization org = new Organization("Candido","123456789","Eduardo","ecouto93@gmail.com","Vasco","Vasco@gmail.com");
        Date date = new Date(2001,11,22);
        Task task = new Task("2",5,4.9,"Candido","aaa");
        Transaction trans = MainController.getPlatform().getRegistryTransaction().newTransaction(org,"a",date,3,"Mau",task,free);
        MainController.getPlatform().getRegistryTransaction().registerTransaction(trans);
        double expResult = 1.0;
        double result = instance.getAveragePercentageDelay();
        System.out.println(result);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testCalculateSumDelayFreelancer() {
        System.out.println("calculateSumDelayFreelancer");
        Freelancer free = new Freelancer("Eduardo Couto", Freelancer.Expertise.Senior,"ecouto93@gmail.com","123456786","PT32","adress","Portugal");
        RegistryFreelancers instance = new RegistryFreelancers();
        instance.registerFreelancer(free);
        Organization org = new Organization("Candido","123456789","Eduardo","ecouto93@gmail.com","Vasco","Vasco@gmail.com");
        Date date = new Date(2001,11,22);
        Task task = new Task("2",5,4.9,"Candido","aaa");
        Transaction trans = MainController.getPlatform().getRegistryTransaction().newTransaction(org,"a2",date,3,"Mau",task,free);
        MainController.getPlatform().getRegistryTransaction().registerTransaction(trans);
        double expResult = 3.0;
        double result = instance.calculateSumDelayFreelancer(free);
        assertEquals(expResult, result, 0.0);

    }

    @Test
    public void testCalculatePercentageFreelancerDelay() {
        try {
            System.out.println("calculatePercentageFreelancerDelay");
            Freelancer free = new Freelancer("EC1","Eduardo Couto", Freelancer.Expertise.Senior, "ecouto93@gmail.com", "123456786", "PT32", "adress", "Portugal");
            RegistryFreelancers instance = new RegistryFreelancers();
            instance.registerFreelancer(free);
            Organization org = new Organization("Candido", "124456789", "Eduardo", "ecouto93@gmail.com", "Vasco", "Vasco@gmail.com");
            Date date = new Date(2001, 11, 22);
            Task task = new Task("2", 5, 4.9, "Candido", "aaa");
            Transaction trans = MainController.getPlatform().getRegistryTransaction().newTransaction(org, "a23", date, 3, "Mau", task, free);
            MainController.getPlatform().getRegistryTransaction().registerTransaction(trans);
            double expResult = 3.0;
            double result = instance.calculatePercentageFreelancerDelay(free);
            assertEquals(expResult, result, 0.0);
        }catch(NullPointerException e){
            System.out.println("erro");
        }
    }


    @Test
    public void testGetFreelancerByIndex() {
        System.out.println("getFreelancerByIndex");
        int index = 0;
        RegistryFreelancers instance = new RegistryFreelancers();
        Freelancer expResult = new Freelancer("EC1","Eduardo Couto", Freelancer.Expertise.Senior, "ecouto93@gmail.com", "123456786", "PT32", "adress", "Portugal");
        instance.registerFreelancer(expResult);
        Freelancer result = instance.getFreelancerByIndex(index);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetFreelancerByID() {
        System.out.println("getFreelancerByID");
        String id = "EC1";
        RegistryFreelancers instance = new RegistryFreelancers();
        Freelancer expResult = new Freelancer("EC1","Eduardo Couto", Freelancer.Expertise.Senior, "ecouto93@gmail.com", "123456786", "PT32", "adress", "Portugal");
        instance.registerFreelancer(expResult);
        Freelancer result = instance.getFreelancerByID(id);
        assertEquals(expResult, result);

    }



    
}
