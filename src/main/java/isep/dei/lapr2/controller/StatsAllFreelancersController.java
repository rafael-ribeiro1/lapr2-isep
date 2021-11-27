package isep.dei.lapr2.controller;

import isep.dei.lapr2.model.*;
import isep.dei.lapr2.model.RegistryClasses.RegistryFreelancers;
import isep.dei.lapr2.model.RegistryClasses.RegistryTransactions;

import java.util.List;

/**
 * Controller Class for Statistics about all freelancers in system
 */
public class StatsAllFreelancersController {

    /**
     * The platform
     */
    private Platform platform;
    /**
     * The registry of transactions
     */
    private RegistryTransactions regTrans;
    /**
     * The registry of freelancers
     */
    private RegistryFreelancers regFrl;
    /**
     * List of transactions
     */
    private List<Transaction> lstTransactions;

    /**
     * Constructor that initiates the atributes
     */
    public StatsAllFreelancersController() {
        this.platform = MainController.getPlatform();
        this.regTrans = platform.getRegistryTransaction();
        this.regFrl = platform.getRegistryFreelancer();
        this.lstTransactions=regTrans.getListTransaction();
    }

    /**
     * Returns the mean of delays
     * @return the mean of delays
     */
    public double getMeanDelays() {
        return regTrans.getMeanDelays(lstTransactions);
    }

    /**
     * Returns the standard deviation of delays
     * @return the standard deviation of delays
     */
    public double getDeviationDelays() {
        return regTrans.getDeviationDelays(lstTransactions);
    }

    /**
     * Returns an array with the data to delays histogram
     * @return an array with the data to delays histogram
     */
    public int[] getHistogramDataDelays() {
        return regTrans.getHistogramDataDelays(lstTransactions);
    }

    /**
     * Returns the mean of payments
     * @return the mean of payments
     */
    public double getMeanPays() {
        return regTrans.getMeanPays(lstTransactions);
    }

    /**
     * Returns the standard deviation of payments
     * @return the standard deviation of payments
     */
    public double getDeviationPays() {
        return regTrans.getDeviationPays(lstTransactions);
    }

    /**
     * Returns an array with the data to payments histogram
     * @return an array with the data to payments histogram
     */
    public int[] getHistogramDataPays() {
        return regTrans.getHistogramDataPays(lstTransactions);
    }

    /**
     * Returns the probability that the sample delays mean is higher than 3 hours,
     * considering that is normally distributed with 2 hours of mean and 1.5 hours of standard deviation
     * @return the probability that the sample delays mean is higher than 3 hours
     */
    public double getProbability() {
        return regTrans.getProbability();
    }

    /**
     * Returns the list of freelancers
     * @return the list of freelancers
     */
    public List<Freelancer> getListFreelancers() {
        return regFrl.getListFreelancers();
    }

    /**
     * Returns the freelancer located in the specified index
     * @param index the location in the list of freelancers
     * @return the freelancer located in the specified index
     */
    public Freelancer getFreelancer(int index) {
        return regFrl.getFreelancerByIndex(index);
    }

    /**
     * Returns the mean of the delays associated to the specfied freelancer
     * @param frl the freelancer wanted
     * @return the mean of the delays associated to the specfied freelancer
     */
    public double getMeanDelaysOfFrl(Freelancer frl) {
        return regTrans.getMeanDelaysOfFreelancer(frl,lstTransactions);
    }

    /**
     * Returns the standard deviation of the delays associated to the specfied freelancer
     * @param frl the freelancer wanted
     * @return the standard deviation of the delays associated to the specfied freelancer
     */
    public double getDeviationDelaysOfFrl(Freelancer frl) {
        return regTrans.getDeviationDelaysOfFreelancer(frl,lstTransactions);
    }

    /**
     * Returns an array with the data related to the specified freelancer to delays histogram
     * @param frl the freelancer wanted
     * @return an array with the data related to the specified freelancer to delays histogram
     */
    public int[] getHistogramDataDelaysOfFrl(Freelancer frl) {
        return regTrans.getHistogramDataDelaysOfFreelacer(frl,lstTransactions);
    }

    /**
     * Returns the mean of the payments associated to the specfied freelancer
     * @param frl the freelancer wanted
     * @return the mean of the payments associated to the specfied freelancer
     */
    public double getMeanPaysOfFrl(Freelancer frl) {
        return regTrans.getMeanPaysOfFreelancer(frl,lstTransactions);
    }

    /**
     * Returns the standard deviation of the payments associated to the specfied freelancer
     * @param frl the freelancer wanted
     * @return the standard deviation of the payments associated to the specfied freelancer
     */
    public double getDeviationPaysOfFrl(Freelancer frl) {
        return regTrans.getDeviationPaysOfFreelancer(frl,lstTransactions);
    }

    /**
     * Returns an array with the data related to the specified freelancer to payments histogram
     * @param frl the freelancer wanted
     * @return an array with the data related to the specified freelancer to payments histogram
     */
    public int[] getHistogramDataPaysOfFrl(Freelancer frl) {
        return regTrans.getHistogramDataPaysOfFreelancer(frl,lstTransactions);
    }

}
