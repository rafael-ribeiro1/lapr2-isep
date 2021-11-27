package isep.dei.lapr2.controller;

import isep.dei.lapr2.model.Freelancer;
import isep.dei.lapr2.model.Platform;
import isep.dei.lapr2.model.RegistryClasses.RegistryFreelancers;

import java.util.List;

/**
 * Controller Class for the Send Email to Freelancers with High Delay Scene
 */
public class SendEmailHighDelaysContoller {
    /**
     * Platform
     */
    private Platform m_platform;
    /**
     * Class Registry Freelancer
     */
    private RegistryFreelancers registryFreelancers;

    /**
     * Constructor that initiates the necessary variables.
     */
    public SendEmailHighDelaysContoller() {
        this.m_platform = MainController.getPlatform();
        registryFreelancers =m_platform.getRegistryFreelancer();
    }
    /**
     * Method that returns the Freelancers with High Mean Task Delay.
     * @return a list of Freelancers with High Mean Delay
     */
    public List<Freelancer> getFreelancersWithMeanTaskDelay(){
        return registryFreelancers.getFreelancersWithHighDelays();
    }

    /**
     * Method that send email to the selected Freelancer.
     * @param f Selected Freelancer.
     */
    public void sendEmail(Freelancer f){
            registryFreelancers.sendEmailWithInfo(f);
    }
}
