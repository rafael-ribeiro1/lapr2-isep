package isep.dei.lapr2.controller;

import isep.dei.lapr2.model.*;
import isep.dei.lapr2.model.RegistryClasses.RegistryFreelancers;
import isep.dei.lapr2.model.RegistryClasses.RegistryTransactions;

import java.util.Date;
import java.util.List;

/**
 * Controller of Register Transaction Scene
 */
public class RegisterTransactionController {
    /**
     * Platform
     */
    private Platform m_platform;
    /**
     * Registry Transaction
     */
    private RegistryTransactions m_Transaction;
    /**
     * Transaction
     */
    private Transaction transaction;

    /**
     * Constructor that initiates the variables
     */
    public RegisterTransactionController() {
        this.m_platform = MainController.getPlatform();
        this.m_Transaction=m_platform.getRegistryTransaction();
    }

    /**
     * Method that creates a new Transaction.
     * @param transID ID of the Transaction.
     * @param endDate Date of the Transaction.
     * @param delay Delay of the execution of the Task.
     * @param descriptionWork Description of the work executed on the task.
     * @param task Task of the Transaction.
     * @param free Freelancer of the Transaction.
     */
    public void newTransaction(String transID,Date endDate,int delay,String descriptionWork,Task task,Freelancer free){
        this.transaction=m_Transaction.newTransaction(getOrganizationLogin(),transID,endDate,delay,descriptionWork,task,free);
    }

    /**
     * Get organization of the User currently using the application.
     * @return a Organization
     */
    public Organization getOrganizationLogin(){
        String email = MainController.getLoginSystem().getLoggedUserEmail();
        return m_platform.getRegistryOrganizations().getOrgByUserEmail(email);
    }

    /**
     *  Method that returns the List of tasks with no transaction.
     * @return A list of tasks
     */
    public List<Task> getListTaskWithNoTransaction(){
        return getOrganizationLogin().getListTask().getListTaskWithNoTransaction();
    }

    /**
     * Method that return all Freelancers in the system
     * @return a list of all Freelancers registered in the system.
     */
    public List<Freelancer> getListFreelancers(){
        RegistryFreelancers m_Freelancer=m_platform.getRegistryFreelancer();
        return m_Freelancer.getListFreelancers();
    }

    /**
     * Method that return the amount to pay for the executed task
     * @return a double
     */
    public double getAmountToPay(){
        return transaction.getAmountToPay();
    }

    /**
     * Register the transaction.
     * @return true if the process was successful.
     */
    public boolean registerTransation(){
            return m_Transaction.registerTransaction(transaction);
    }

    /**
     * Validates the data of the transaction.
     * @return
     */
    public boolean validate(){
        return m_Transaction.validate(transaction);
    }

    /**
     * Sets the task of the current transaction has a transaction.
     */
    public void setTaskHasTransactionTrue(){
        transaction.getTask().setTaskHasTransactionTrue();
    }

    /**
     * Getter for the current Transaction.
     * @return Current Existing Transaction.
     */
    public Transaction getTransaction(){
        return this.transaction;
    }

}
