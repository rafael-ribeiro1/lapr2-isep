package isep.dei.lapr2.controller;

import isep.dei.lapr2.model.Platform;
import isep.dei.lapr2.model.RegistryClasses.RegistryFreelancers;

import java.util.TimerTask;

/**
 * Task executed in the End of each Year.
 */
public class EndYearEmailTask extends TimerTask {
    /**
     * Class Registry Freelancer
     */
    RegistryFreelancers rFree ;
    /**
     * Class of the Platform
     */
    private Platform platform;

    /**
     * Constructor that initiates the necessary parameters.
     */
    public EndYearEmailTask() {
        this.platform = MainController.getPlatform();
        rFree = platform.getRegistryFreelancer();
    }

    @Override
    public void run() {
        sendEmails();
        scheduleNextTimer();
    }

    /**
     * Method that schedule the task for the next Year.
     */
    private void scheduleNextTimer() {
        rFree.scheduleAutomaticEmail();
    }

    /**
     * Method that sends Emails to Freelancers.
     */
    private void sendEmails() {
         rFree.sendEndofTheYearEmail();
    }
}
