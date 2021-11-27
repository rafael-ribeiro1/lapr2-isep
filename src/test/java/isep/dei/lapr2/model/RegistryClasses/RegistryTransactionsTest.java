/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isep.dei.lapr2.model.RegistryClasses;

import isep.dei.lapr2.controller.MainController;
import isep.dei.lapr2.model.Freelancer;
import isep.dei.lapr2.model.Organization;
import isep.dei.lapr2.model.Task;
import isep.dei.lapr2.model.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 * @author edu-c
 */
public class RegistryTransactionsTest {
    
    public RegistryTransactionsTest() {
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

        Freelancer free = new Freelancer("EC1","Eduardo Couto", Freelancer.Expertise.Senior, "ecouto93@gmail.com", "123456786", "PT32", "adress", "Portugal");
        RegistryFreelancers instance = new RegistryFreelancers();
        Organization org = new Organization("Candido", "124456789", "Eduardo", "ecouto93@gmail.com", "Vasco", "Vasco@gmail.com");
        Date date = new Date(2001, 11, 22);
        Task task = new Task("2", 5, 5.0, "Candido", "aaa");
        Transaction trans = MainController.getPlatform().getRegistryTransaction().newTransaction(org, "a23", date, 3, "Mau", task, free);
        RegistryTransactions rga = MainController.getPlatform().getRegistryTransaction();

    


    @Test
    public void testRegisterTransaction() {
        System.out.println("registerTransaction");
        Transaction trans = MainController.getPlatform().getRegistryTransaction().newTransaction(org, "a232", date, 3, "Mau", task, free);
        RegistryTransactions instance = new RegistryTransactions();
        boolean expResult = true;
        boolean result = instance.registerTransaction(trans);
        assertEquals(expResult, result);
    }



    @Test
    public void testValidate() {
        System.out.println("validate");
        Transaction trans = MainController.getPlatform().getRegistryTransaction().newTransaction(org, "a2321", date, 3, "Mau", task, free);
        RegistryTransactions instance = new RegistryTransactions();
        boolean expResult = true;
        boolean result = instance.validate(trans);
        assertEquals(expResult, result);

    }

    @Test
    public void testGetMeanDelays() {
        System.out.println("getMeanDelays");
        List<Transaction> lstTransactions = new ArrayList<>();
        Transaction trans = MainController.getPlatform().getRegistryTransaction().newTransaction(org, "a22321", date, 5, "Mau", task, free);
        lstTransactions.add(trans);
        RegistryTransactions instance = new RegistryTransactions();
        double expResult = 5.0;
        double result = instance.getMeanDelays(lstTransactions);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testGetDeviationDelays() {
        System.out.println("getDeviationDelays");
        List<Transaction> lstTransactions = new ArrayList<>();
        Transaction trans = MainController.getPlatform().getRegistryTransaction().newTransaction(org, "a22321", date, 5, "Mau", task, free);
        lstTransactions.add(trans);
        RegistryTransactions instance = new RegistryTransactions();
        double expResult = 0.0;
        double result = instance.getDeviationDelays(lstTransactions);
        assertEquals(expResult, result, 0.0);

    }


    @Test
    public void testGetMeanPays() {
        System.out.println("getMeanPays");
        List<Transaction> lstTransactions = new ArrayList<>();
        Transaction trans = MainController.getPlatform().getRegistryTransaction().newTransaction(org, "a22321", date, 5, "Mau", task, free);
        lstTransactions.add(trans);
        RegistryTransactions instance = new RegistryTransactions();
        double expResult = 50.0;
        double result = instance.getMeanPays(lstTransactions);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testGetDeviationPays() {
        System.out.println("getDeviationPays");
        List<Transaction> lstTransactions = new ArrayList<>();
        Transaction trans = MainController.getPlatform().getRegistryTransaction().newTransaction(org, "a22321", date, 5, "Mau", task, free);
        lstTransactions.add(trans);
        RegistryTransactions instance = new RegistryTransactions();
        double expResult = 0.0;
        double result = instance.getDeviationPays(lstTransactions);
        assertEquals(expResult, result, 0.0);

    }

    @Test
    public void testGetProbability() {
        System.out.println("getProbability");
        RegistryTransactions instance = new RegistryTransactions();
        double expResult = 0.0;
        double result = instance.getProbability();
        assertEquals(expResult, result, 0.0);

    }

    @Test
    public void testGetMeanDelaysOfFreelancer() {
        System.out.println("getMeanDelaysOfFreelancer");
        Freelancer frl = new Freelancer("ED1","Eduardo Cou1", Freelancer.Expertise.Senior, "ecouto93@gmail.com", "123455786", "PT232", "adress", "Portugal");;
        List<Transaction> lstTransactions = new ArrayList<>();
        RegistryTransactions instance = new RegistryTransactions();
        double expResult = 0.0;
        double result = instance.getMeanDelaysOfFreelancer(frl, lstTransactions);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testGetDeviationDelaysOfFreelancer() {
        System.out.println("getDeviationDelaysOfFreelancer");
        Freelancer frl = new Freelancer("ED1","Eduardo Cou1", Freelancer.Expertise.Senior, "ecouto93@gmail.com", "123455786", "PT232", "adress", "Portugal");
        List<Transaction> lstTransactions = new ArrayList<>();
        RegistryTransactions instance = new RegistryTransactions();
        double expResult = 0.0;
        double result = instance.getDeviationDelaysOfFreelancer(frl, lstTransactions);
        assertEquals(expResult, result, 0.0);
    }


    @Test
    public void testGetMeanPaysOfFreelancer() {
        System.out.println("getMeanPaysOfFreelancer");
        Freelancer frl = new Freelancer("ED1","Eduardo Cou1", Freelancer.Expertise.Senior, "ecouto93@gmail.com", "123455786", "PT232", "adress", "Portugal");;
        List<Transaction> lstTransactions = new ArrayList<>();
        RegistryTransactions instance = new RegistryTransactions();
        double expResult = 0.0;
        double result = instance.getMeanPaysOfFreelancer(frl, lstTransactions);
        assertEquals(expResult, result, 0.0);

    }

    @Test
    public void testGetDeviationPaysOfFreelancer() {
        System.out.println("getDeviationPaysOfFreelancer");
        Freelancer frl = new Freelancer("ED1","Eduardo Cou1", Freelancer.Expertise.Senior, "ecouto93@gmail.com", "123455786", "PT232", "adress", "Portugal");
        List<Transaction> lstTransactions = new ArrayList<>();
        RegistryTransactions instance = new RegistryTransactions();
        double expResult = 0.0;
        double result = instance.getDeviationPaysOfFreelancer(frl, lstTransactions);
        assertEquals(expResult, result, 0.0);

    }

    @Test
    public void testSumPaymentesByFreelancer() {
        System.out.println("sumPaymentesByFreelancer");
        Freelancer free = new Freelancer("ED1","Eduardo Cou1", Freelancer.Expertise.Senior, "ecouto93@gmail.com", "123455786", "PT232", "adress", "Portugal");
        RegistryFreelancers instance = new RegistryFreelancers();
        instance.registerFreelancer(free);
        Organization org = new Organization("Candido", "124456789", "Eduardo", "ecouto93@gmail.com", "Vasco", "Vasco@gmail.com");
        Date date = new Date(2001, 11, 22);
        Task task = new Task("5", 5, 5.0, "Candido", "aaa");
        Transaction trans = MainController.getPlatform().getRegistryTransaction().newTransaction(org, "a23", date, 3, "Mau", task, free);
        RegistryTransactions rga = new RegistryTransactions();
        rga.registerTransaction(trans);
        double expResult = 50.0;
        double result = rga.sumPaymentesByFreelancer(free);
        Assert.assertEquals(expResult, result, 0.0);
    }


    
}
