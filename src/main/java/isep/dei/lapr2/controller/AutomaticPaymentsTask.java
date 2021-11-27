
package isep.dei.lapr2.controller;

import isep.dei.lapr2.model.*;
import isep.dei.lapr2.model.RegistryClasses.RegistryFreelancers;
import isep.dei.lapr2.model.RegistryClasses.RegistryTransactions;

import java.util.List;
import java.util.TimerTask;

/**
 * Class executed when the date defined its reached.
 */
public class AutomaticPaymentsTask extends TimerTask {
    /**
     * Platform
     */
    private Platform platform;
    /**
     * Organization currently logged
     */
    private Organization org;

    /**
     * Constructor of the Class that initiates the necessary variables
     * @param org
     */
    public AutomaticPaymentsTask(Organization org) {
        this.platform = MainController.getPlatform();
        this.org = org;
    }

    @Override
    public void run() {
        makePayments(org);
        scheduleNextTimer(org);
    }

    /**
     * Method that makes the payment of the organization
     * @param org
     */
    private void makePayments(Organization org) {
        RegistryTransactions regTrans = platform.getRegistryTransaction();
        List<Transaction> paidTrans = regTrans.payTransactions(org);
        RegistryFreelancers regFree = platform.getRegistryFreelancer();
        regFree.sendEmails(paidTrans);
    }

    /**
     * Method that schedule the next Time the task is executed
     * @param org
     */
    private void scheduleNextTimer(Organization org) {
        RegistryTransactions regTrans = platform.getRegistryTransaction();
        regTrans.scheduleOrgPayments(org);
    }
    
}
