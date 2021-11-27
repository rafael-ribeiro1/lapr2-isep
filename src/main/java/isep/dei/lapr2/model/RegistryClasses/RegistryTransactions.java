package isep.dei.lapr2.model.RegistryClasses;


import isep.dei.lapr2.controller.AutomaticPaymentsTask;
import isep.dei.lapr2.model.Freelancer;
import isep.dei.lapr2.model.Organization;
import isep.dei.lapr2.model.Task;
import isep.dei.lapr2.model.Transaction;
import isep.dei.lapr2.model.utils.NormalDistribution;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 * Class that register and keeps the transactions
 */
public class RegistryTransactions implements Serializable {
    /**
     * The list of transactions
     */
    private List<Transaction> m_lstTransactions;

    /**
     * Initializes the list
     */
    public RegistryTransactions() {
        m_lstTransactions = new ArrayList<>();
    }

    /**
     * Returns the list of transactions
     * @return the list of transactions
     */
    public List<Transaction> getListTransaction(){
        return m_lstTransactions;
    }

    /**
     * Creates a new transaction
     * @param org the organization
     * @param transID the ID of the transaction
     * @param endDate the ending date of the transaction
     * @param delay the delay of the execution of the task
     * @param descriptionWork the description of the execution of the task
     * @param task the task associated
     * @param free the freelancer associated
     * @return the transaction created
     */
    public Transaction newTransaction(Organization org, String transID, Date endDate, int delay, String descriptionWork, Task task, Freelancer free) {
        return new Transaction(org,transID,endDate,delay,descriptionWork,task,free);
    }

    /**
     * Register the transaction in the list
     * @param trans the transaction to register
     * @return true if it was registered, false if not
     */
    public boolean registerTransaction(Transaction trans){
        if(validate(trans)){
            return m_lstTransactions.add(trans);
        }
        return false;
    }

    /**
     * Schedule the automatic payments of an organization
     * @param org the organization to schedule the payments
     */
    public void scheduleOrgPayments(Organization org) {
        AutomaticPaymentsTask task = new AutomaticPaymentsTask(org);
        Date paymentsDate = org.getNextPaymentsDate();
        Timer t = new Timer();
        t.schedule(task, paymentsDate);
    }

    /**
     * Pays the transactions not-paid of an organization
     * @param org the organization to pay the transactions
     * @return a list of the transactions payed
     */
    public List<Transaction> payTransactions(Organization org) {
        List<Transaction> paidTrans = new ArrayList<>();
        for (Transaction transaction: m_lstTransactions) {
            if (transaction.isPaid() || !transaction.getOrg().equals(org)) continue;
            transaction.payAndRegister();
            paidTrans.add(transaction);
        }
        return paidTrans;
    }

    /**
     * Returns the list of transactions of an organization
     * @param org the organization to search
     * @return the list of transactions of the organization
     */
    public List<Transaction> getListOrganizationTransactions(Organization org){
        List<Transaction> lstOrgTransactions=new ArrayList<>();
        for(Transaction t : m_lstTransactions){
            if(t.getOrg().equals(org)){
                lstOrgTransactions.add(t);
            }
        }
        return lstOrgTransactions;
    }

    /**
     * Validates a transaction
     * @param trans the transaction to validate
     * @return true if it is valid, false if not
     */
    public boolean validate(Transaction trans){
        {
            for(Transaction T: m_lstTransactions){
                if(T.getTransID().equals(trans.getTransID()))throw new IllegalArgumentException("ID of the Transaction already registered! ");
            }
            return true;
        }

    }

    /**
     * Calculates the mean of delays of a list of transactions
     * @param lstTransactions the list of transactions
     * @return the mean of delays
     */
    public double getMeanDelays(List<Transaction> lstTransactions) {
        if (lstTransactions.size() == 0) return 0;
        else {
            double sum = 0;
            for (Transaction t: lstTransactions) {
                sum += t.getDelay();
            }
            return sum / lstTransactions.size();
        }
    }

    /**
     * Calculates the standard deviation of delays of a list of transactions
     * @param lstTransactions the list of transactions
     * @return the standard deviation of delays
     */
    public double getDeviationDelays(List<Transaction> lstTransactions) {
        if (lstTransactions.size() == 0) return 0;
        else {
            double mean = getMeanDelays(lstTransactions);
            double sum = 0;
            for (Transaction t: lstTransactions) {
                sum += Math.pow(t.getDelay() - mean, 2);
            }
            return Math.sqrt(sum / lstTransactions.size());
        }
    }

    /**
     * Returns an integer array with the number of delays to the intervals of the histogram
     * @param lstTransactions the list of transactions
     * @return an integer array with the number of delays to the intervals of the histogram
     */
    public int[] getHistogramDataDelays(List<Transaction> lstTransactions) {
        if (lstTransactions.size() == 0) return new int[3];
        else {
            double mean = getMeanDelays(lstTransactions);
            double deviation = getDeviationDelays(lstTransactions);
            double infLim = mean - deviation;
            double supLim = mean + deviation;
            int[] histData = new int[3];
            for (Transaction t: lstTransactions) {
                if (t.getDelay() <= infLim) histData[0]++;
                else if (t.getDelay() >= supLim) histData[2]++;
                else histData[1]++;
            }
            return histData;
        }
    }

    /**
     * Calculates the mean of payments of a list of transactions
     * @param lstTransactions the list of transactions
     * @return the mean of payments
     */
    public double getMeanPays(List<Transaction> lstTransactions) {
        if (lstTransactions.size() == 0) return 0;
        else {
            double sum = 0;
            for (Transaction t: lstTransactions) {
                sum += t.getAmountToPay();
            }
            return sum / lstTransactions.size();
        }
    }

    /**
     * Calculates the standard deviation of payments of a list of transactions
     * @param lstTransactions the list of transactions
     * @return the standard deviation of payments
     */
    public double getDeviationPays(List<Transaction> lstTransactions) {
        if (lstTransactions.size() == 0) return 0;
        else {
            double mean = getMeanPays(lstTransactions);
            double sum = 0;
            for (Transaction t: lstTransactions) {
                sum += Math.pow(t.getAmountToPay() - mean, 2);
            }
            return Math.sqrt(sum / lstTransactions.size());
        }
    }

    /**
     * Returns an integer array with the number of payments to the intervals of the histogram
     * @param lstTransactions the list of transactions
     * @return an integer array with the number of payments to the intervals of the histogram
     */
    public int[] getHistogramDataPays(List<Transaction> lstTransactions) {
        if (lstTransactions.size() == 0) return new int[3];
        else {
            double mean = getMeanPays(lstTransactions);
            double deviation = getDeviationPays( lstTransactions);
            double infLim = mean - deviation;
            double supLim = mean + deviation;
            int[] histData = new int[3];
            for (Transaction t: lstTransactions) {
                if (t.getAmountToPay() <= infLim) histData[0]++;
                else if (t.getAmountToPay() >= supLim) histData[2]++;
                else histData[1]++;
            }
            return histData;
        }
    }

    /**
     * Returns the probability of the sample delays mean being higher than 3 hours
     * @return the probability of the sample delays mean being higher than 3 hours
     */
    public double getProbability() {
        if (m_lstTransactions.size() == 0) return 0;
        else {
            double mean = 2;
            double var = Math.pow(1.5, 2) / m_lstTransactions.size();
            double dev = Math.sqrt(var);
            return 1 - NormalDistribution.cdf(3, mean, dev);
        }
    }

    /**
     * Calculates the mean of delays to a freelancer
     * @param frl the freelancer
     * @param lstTransactions the list of transactions
     * @return the mean of delays to the freelancer
     */
    public double getMeanDelaysOfFreelancer(Freelancer frl,List<Transaction> lstTransactions) {
        if (lstTransactions.size() == 0) return 0;
        else {
            double sum = 0;
            int n = 0;
            for (Transaction t: lstTransactions) {
                if (t.getFreelancer().equals(frl)) {
                    sum += t.getDelay();
                    n++;
                }
            }
            return n > 0 ? sum / n : 0;
        }
    }

    /**
     * Calculates the standard deviation of delays to a freelancer
     * @param frl the freelancer
     * @param lstTransactions the list of transactions
     * @return the standard deviation of delays to the freelancer
     */
    public double getDeviationDelaysOfFreelancer(Freelancer frl,List<Transaction> lstTransactions) {
        if (lstTransactions.size() == 0) return 0;
        else {
            double mean = getMeanDelaysOfFreelancer(frl,lstTransactions);
            double sum = 0;
            int n = 0;
            for (Transaction t: lstTransactions) {
                if (t.getFreelancer().equals(frl)) {
                    sum += Math.pow(t.getDelay() - mean, 2);
                    n++;
                }
            }
            return n > 0 ? Math.sqrt(sum / n) : 0;
        }
    }

    /**
     * returns an integer array with the delays for each interval of the histogram
     * @param frl the freelancer
     * @param lstTransactions the list of freelancers
     * @return an integer array with the delays for each interval of the histogram
     */
    public int[] getHistogramDataDelaysOfFreelacer(Freelancer frl,List<Transaction> lstTransactions) {
        if (lstTransactions.size() == 0) return new int[3];
        else {
            double mean = getMeanDelaysOfFreelancer(frl,lstTransactions);
            double deviation = getDeviationDelaysOfFreelancer(frl,lstTransactions);
            double infLim = mean - deviation;
            double supLim = mean + deviation;
            int[] histData = new int[3];
            for (Transaction t: lstTransactions) {
                if (t.getFreelancer().equals(frl)) {
                    if (t.getDelay() <= infLim) histData[0]++;
                    else if (t.getDelay() >= supLim) histData[2]++;
                    else histData[1]++;
                }
            }
            return histData;
        }
    }

    /**
     * Calculates the mean of payments to a freelancer
     * @param frl the freelancer
     * @param lstTransactions the list of transactions
     * @return the mean of payments to the freelancer
     */
    public double getMeanPaysOfFreelancer(Freelancer frl,List<Transaction> lstTransactions) {
        if (lstTransactions.size() == 0) return 0;
        else {
            double sum = 0;
            int n = 0;
            for (Transaction t: lstTransactions) {
                if (t.getFreelancer().equals(frl)) {
                    sum += t.getAmountToPay();
                    n++;
                }
            }
            return n > 0 ? sum / n : 0;
        }
    }

    /**
     * Calculates the standard deviation of payments to a freelancer
     * @param frl the freelancer
     * @param lstTransactions the list of transactions
     * @return the standard deviation of payments to the freelancer
     */
    public double getDeviationPaysOfFreelancer(Freelancer frl,List<Transaction> lstTransactions) {
        if (lstTransactions.size() == 0) return 0;
        else {
            double mean = getMeanPaysOfFreelancer(frl,lstTransactions);
            double sum = 0;
            int n = 0;
            for (Transaction t: lstTransactions) {
                if (t.getFreelancer().equals(frl)) {
                    sum += Math.pow(t.getAmountToPay() - mean, 2);
                    n++;
                }
            }
            return n > 0 ? Math.sqrt(sum / n) : 0;
        }
    }

    /**
     * returns an integer array with the payments for each interval of the histogram
     * @param frl the freelancer
     * @param lstTransactions the list of freelancers
     * @return an integer array with the payments for each interval of the histogram
     */
    public int[] getHistogramDataPaysOfFreelancer(Freelancer frl,List<Transaction> lstTransactions) {
        if (lstTransactions.size() == 0) return new int[3];
        else {
            double mean = getMeanPaysOfFreelancer(frl, lstTransactions);
            double deviation = getDeviationPaysOfFreelancer(frl, lstTransactions);
            double infLim = mean - deviation;
            double supLim = mean + deviation;
            int[] histData = new int[3];
            for (Transaction t : lstTransactions) {
                if (t.getFreelancer().equals(frl)) {
                    if (t.getAmountToPay() <= infLim) histData[0]++;
                    else if (t.getAmountToPay() >= supLim) histData[2]++;
                    else histData[1]++;
                }
            }
            return histData;
        }
    }

    /**
     * Returns the sum of payments to a freelancer
     * @param f the freelancer
     * @return the sum of payments to the freelancer
     */
    public double sumPaymentesByFreelancer(Freelancer f){
        double sum =0;
        for(Transaction t: m_lstTransactions){
            if(t.getFreelancer().equals(f)){
                sum+=t.getAmountToPay();
            }
        }
        return sum;
    }

}