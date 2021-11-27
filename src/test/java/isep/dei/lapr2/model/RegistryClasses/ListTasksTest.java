/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isep.dei.lapr2.model.RegistryClasses;

import isep.dei.lapr2.model.Task;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 * @author edu-c
 */
public class ListTasksTest {
    
    public ListTasksTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetListTask() {
        System.out.println("getListTask");
        ListTasks instance = new ListTasks();
        List<Task> expResult = new ArrayList<>();
        List<Task> result = instance.getListTask();
        assertEquals(expResult, result);
    }


    @Test
    public void testGetListTaskWithNoTransaction() {
        System.out.println("getListTaskWithNoTransaction");
        ListTasks instance = new ListTasks();
        Task task = new Task("1",1,1.0,"1","1");
        instance.registerTask(task);
        List<Task> expResult = new ArrayList<>();
        expResult.add(task);
        List<Task> result = instance.getListTaskWithNoTransaction();
        assertEquals(expResult, result);
    }

    @Test
    public void testRegisterTask() {
        System.out.println("registerTask");
        Task task = new Task("1",1,1.0,"1","1");
        ListTasks instance = new ListTasks();
        boolean expResult = true;
        boolean result = instance.registerTask(task);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidate() {
        System.out.println("validate");
        Task task = new Task("1",1,1.0,"1","1");
        ListTasks instance = new ListTasks();
        boolean expResult = true;
        boolean result = instance.validate(task);
        assertEquals(expResult, result);

    }
    
}
