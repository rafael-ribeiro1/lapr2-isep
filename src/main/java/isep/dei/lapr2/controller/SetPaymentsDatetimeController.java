
package isep.dei.lapr2.controller;

import isep.dei.lapr2.model.*;
import isep.dei.lapr2.model.RegistryClasses.RegistryOrganizations;

/**
 * Controller Class for Setting Payments DateTime Scene
 */
public class SetPaymentsDatetimeController {
    /**
     * Platform
     */
    private Platform m_platform;
    /**
     * Organization.
     */
    private Organization org;

    /**
     * Constructor that initiate the necessary variable.
     */
    public SetPaymentsDatetimeController() {
        this.m_platform = MainController.getPlatform();
    }

    /**
     * Method that sets the variable Organization to the current Organization logged
     */
    public void getOrganization() {
        String email = MainController.getLoginSystem().getLoggedUserEmail();
        RegistryOrganizations regOrg = m_platform.getRegistryOrganizations();
        org = regOrg.getOrgByUserEmail(email);
    }

    /**
     * Method that register the Payment Date Time.
     * @param day Day of Payment
     * @param hour Hour of the Payment.
     * @param min Minute of the Payment
     * @return True if the process was successful.
     */
    public boolean registerPaymentsDatetime(int day, int hour, int min) {
        return org.registerPaymentsDatetime(day, hour, min);
    }
    
}
