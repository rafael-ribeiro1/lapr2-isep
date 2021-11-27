package isep.dei.lapr2.model;

import isep.dei.lapr2.controller.MainController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.*;

import java.util.Calendar;
import java.util.Date;

/**
 * Class that represents Transaction.
 */
public class Transaction implements Serializable {
    /**
     * ID of the transaction.
     */
    private String transID;
    /**
     * Task of the Transaction.
     */
    private Task task;
    /**
     * Freelancer of the Transaction.
     */
    private Freelancer free ;
    /**
     * Organization of the Transaction.
     */
    private Organization org;
    /**
     * Date of the Transaction.
     */
    private Date endDate;
    /**
     * Delay of the Transaction.
     */
    private int delay;
    /**
     * Description of the Work
     */
    private String descriptionWork;
    /**
     * Boolean that represents if the transaction was paid or not.
     */
    private boolean paid;

    /**
     * Constructor of the class
     * @param org Organization of the Transaction
     * @param transID ID of the Transaction
     * @param endDate End Date of the Transaction
     * @param delay Delay of the Transaction
     * @param descriptionWork Description of the Work of the Transaction
     * @param task Task of the Transaction.
     * @param free Freelancer of the Freelancer.
     */
    public Transaction(Organization org,String transID,Date endDate,int delay,String descriptionWork,Task task,Freelancer free){
        setOrg(org);
        setTransID(transID);
        setEndDate(endDate);
        setDelay(delay);
        setDescriptionWork(descriptionWork);
        this.paid=false;
        setTask(task);
        setFreelancer(free);
    }

    /**
     * Constructor of the class of a paid Transaction
     * @param org Organization of the Transaction
     * @param transID ID of the Transaction
     * @param endDate End Date of the Transaction
     * @param delay Delay of the Transaction
     * @param descriptionWork Description of the Work of the Transaction
     * @param task Task of the Transaction.
     * @param free Freelancer of the Freelancer.
     * @param paid Transaction is Paid
     */
    public Transaction(Organization org,String transID,Date endDate,int delay,String descriptionWork,boolean paid,Task task,Freelancer free){
        setOrg(org);
        setTransID(transID);
        setEndDate(endDate);
        setDelay(delay);
        setDescriptionWork(descriptionWork);
        this.paid=paid;
        setTask(task);
        setFreelancer(free);
    }

    /**
     * Getter of Organization
     * @return Organization of the Transaction.
     */
    public Organization getOrg() {
        return org;
    }

    /**
     * Getter of the Task of the Transaction.
     * @return Task of the Transaction.
     */
    public Task getTask(){
        return this.task;
    }

    /**
     * Setter that validates and defines the task of the Transaction.
     * @param task Task of the Transaction.
     */

    public void setTask(Task task){
        if(task==null){
            throw new IllegalArgumentException("You need to select first a task");
        }this.task=task;
    }

    /**
     * Setter that validates and defines the ID of the Transaction.
     * @param transID ID of the Transaction.
     */
    public void setTransID(String transID) {
        if(transID==null || transID.trim().isEmpty()){
            throw new IllegalArgumentException("You need to insert a transID");
        }
        this.transID = transID;
    }

    /**
     * Getter that return the Freelancers of the Transaction.
     * @return Freelancer of the Transaction.
     */
    public Freelancer getFreelancer() {
        return this.free;
    }

    /**
     * Setter that validates and defines the Freelancer
     * @param free Freelancer
     */
    public void setFreelancer(Freelancer free){
        if(free==null){
            throw new IllegalArgumentException("You need to select first a Freelancer !");
        }
        this.free=free;
    }

    /**
     * Setter that validate and defines the Organization
     * @param org Organization to set
     */
    public void setOrg(Organization org) {
        if(org==null)throw new IllegalArgumentException("No Organization associated !");
        this.org = org;
    }

    /**
     * Setter that validate and defines the EndDate
     * @param endDate EndDate of the Transaction
     */
    public void setEndDate(Date endDate) {
        if(endDate==null){
            throw new IllegalArgumentException("Invalid date !");
        }
        this.endDate = endDate;
    }

    /**
     * Setter that validate and defines the Delay
     * @param delay Delay of the Transaction
     */
    public void setDelay(int delay) {

        this.delay = delay;
    }


    /**
     * Setter that validate and defines the Description of the work of the Freelancer
     * @param descriptionWork Description of the work of the Task.
     */
    public void setDescriptionWork(String descriptionWork) {
        if(descriptionWork==null||descriptionWork.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid description of Work");
        }
        this.descriptionWork = descriptionWork;
    }

    /**
     * Setter that return the value of the Date of the Transaction
     * @return Date of transaction
     */
    public Date getEndDate() {
        return endDate;
    }


    /**
     * Getter that defines the Delay of the execution of the task.
     * @return Delay of the execution of the Task.
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Getter that defines the ID of the Transaction.
     * @return ID of the Transaction.
     */
    public String getTransID() {
        return transID;
    }

    /**
     * Method that returns the amount to pay to the Freelancer
     * @return amount to pay to the Freelancer.
     */
    public double getAmountToPay() {
        double amountToPay = task.getTaskCostPerHour() * task.getTaskAssignDuration();
        if (free.getExpertise() == Freelancer.Expertise.Senior){
            amountToPay *= 2;
        }
        return amountToPay;
    }

    /**
     * Getter that return the value if the transaction is paid or not
     * @return Value the transaction was paid or not.
     */
    public boolean isPaid() {
        return this.paid;
    }

    /**
     * Method that register all the transactions in the file payments.txt
     */
    public void payAndRegister() {
        this.paid = true;
        try {
            File file = new File("payments.txt");
            FileWriter fileWriter = new FileWriter(file,true);
            PrintWriter pw = new PrintWriter(fileWriter);
            if (file.length() == 0) {
                pw.println("Date;TaskID;TaskDescription;FreelancerID;FreelancerName;BankAccount;AmountEUR;AmountFrlCurrency");
            }
            Calendar day = Calendar.getInstance();
            double amountEUR = getAmountToPay();
            String amountConv = MainController.getPlatform().getExternalConverterCurrency()
                    .convertCurrency(amountEUR,free.getCountry());
            pw.println(String.format("%d-%d-%d;%s;%s;%s;%s;%s;%.2f€;%s",day.get(Calendar.DAY_OF_MONTH),day.get(Calendar.MONTH)+1,
                    day.get(Calendar.YEAR),task.getTaskID(),task.getTaskDescription(),free.getId(),
                    free.getName(),free.getBankAccount(),amountEUR,amountConv));
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Payment Error");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Equals Method of Transaction.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return delay == that.delay &&
                paid == that.paid &&
                transID.equals(that.transID) &&
                task.equals(that.task) &&
                free.equals(that.free) &&
                endDate.equals(that.endDate) &&
                descriptionWork.equals(that.descriptionWork);
    }

    /**
     * Returns String with Information about transaction.
     * @return Information about transaction.
     */
    @Override
    public String toString() {
        return String.format("The task '%s', with the ID '%s'",this.task.getTaskDescription(),task.getTaskID());
    }
}
