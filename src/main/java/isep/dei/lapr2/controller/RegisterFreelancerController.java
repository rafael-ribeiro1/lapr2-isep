package isep.dei.lapr2.controller;

import isep.dei.lapr2.model.ExternalConverterCurrency;
import isep.dei.lapr2.model.Freelancer;
import isep.dei.lapr2.model.Platform;
import isep.dei.lapr2.model.RegistryClasses.RegistryFreelancers;

/**
 * Controller Class for Scene RegisterFreelancerUI
 */
public class RegisterFreelancerController {
    private Platform m_platform;
    private RegistryFreelancers registryFreelancers;
    private Freelancer free;
    /**
     * Constructor class that initializes all necessary parameters
     */
    public RegisterFreelancerController() {
        this.m_platform = MainController.getPlatform();
        registryFreelancers =m_platform.getRegistryFreelancer();
    }

    /**
     * Method that creates and returns a new Instance of Freelancer.
     * @param name Name of the Freelancer
     * @param expertise Expertise of the Freelancer
     * @param email Email of the Freelancer
     * @param nif NIF of the Freelancer
     * @param bankAccount Bank Account of the Freelancer
     * @param address Address of the Freelancer
     * @param country Country of the Freelancer
     * @return new Instance of Freelancer
     */
    public Freelancer newFreelancer(String name, Freelancer.Expertise expertise ,String email,String nif,String bankAccount, String address, String country){
        free=registryFreelancers.newFreelancer(name,expertise,email,nif,bankAccount,address,country);
        return free;
    }

    /**
     * Register in the system the instance of Freelancer of this Controller
     * @return True if it was registered
     */
    public boolean registerFreelancer(){
            registryFreelancers.generateFreelancerID(free);
            return registryFreelancers.registerFreelancer(free);
    }

    /**
     * Method that returns a list of available values of Expertise
     * @return A array with all values of Expertise
     */
    public Freelancer.Expertise[] getExpertiseArray(){
        return Freelancer.Expertise.values();
    }

    /**
     * Method that returns a array with all available country's
     * @return array with all available country's
     */
    public String[] getCountrys(){
        ExternalConverterCurrency cr = MainController.getPlatform().getExternalConverterCurrency();
        return cr.getAvalilableCountrys();
    }
}
