/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isep.dei.lapr2.model;

import java.util.Calendar;
import java.util.Date;
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
public class TransactionTest {
    
    public TransactionTest() {
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


    /**
     * Test to calculate if the amount from a transaction is calculated correctly
     */

    @Test
    public void testGetAmountToPay() {
        System.out.println("getAmountToPay");
        Organization org = new Organization("Candido","123456789","Eduardo","ecouto93@gmail.com","Vasco","Vasco@gmail.com");
        Date date = new Date(Calendar.YEAR,Calendar.JANUARY,Calendar.DATE);
        Freelancer free= new Freelancer("Eduardo", Freelancer.Expertise.Senior,"ecouto93@gmail.com","123456786","PT32","adress","Portugal");
        Task task = new Task("5",50,10.0,"Pintura","Pintura carros");
        Transaction instance = new Transaction(org,"1",date,0,"good work",task,free);
        double expResult = 1000;
        double result = instance.getAmountToPay();
        assertEquals(expResult, result,0.0);
        Freelancer free2= new Freelancer("Candido", Freelancer.Expertise.Junior,"candido@gmail.com","153456786","PT32","adress","Portugal");
        Transaction instance2 = new Transaction(org,"5",date,0,"good work",task,free2);
        double expResult2 =500;
        double result2 = instance2.getAmountToPay();
        assertEquals(expResult2, result2,0.0);


    }


}
