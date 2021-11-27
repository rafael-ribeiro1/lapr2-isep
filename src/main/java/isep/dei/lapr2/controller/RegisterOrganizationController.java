package isep.dei.lapr2.controller;

import isep.dei.lapr2.login.LoginSystem;
import isep.dei.lapr2.login.User;
import isep.dei.lapr2.model.*;
import isep.dei.lapr2.model.RegistryClasses.RegistryOrganizations;

/**
 * Controller class for Scene RegisterOrganizationUI
 */
public class RegisterOrganizationController {

    private Platform m_platform;
    private RegistryOrganizations m_RegistryOrganizations;
    private Organization org;
    /**
     * Constructor class that initializes all necessary parameters
     */
    public RegisterOrganizationController() {
        this.m_platform = MainController.getPlatform();
        this.m_RegistryOrganizations = m_platform.getRegistryOrganizations();
    }

    /**
     * Method that creates an returns an instance of Organization
     * @param nameOrg Name of Organization.
     * @param nif NIF of the Organization
     * @param nameCollab Name of the Collaborator of the Organization.
     * @param emailCollab Email of the Collaborator of the Organization.
     * @param nameMan Name of the Manager of the Organization.
     * @param emailMan Email of the Manager of the Organization
     * @return a new instance of Organization
     */
    public Organization newOrganization(String nameOrg, String nif, String nameCollab, String emailCollab, String nameMan, String emailMan) {
        this.m_RegistryOrganizations = this.m_platform.getRegistryOrganizations();
        org=this.m_RegistryOrganizations.newOrganization(nameOrg, nif, nameCollab, emailCollab, nameMan, emailMan);
        return org;
    }

    /**
     * Method that registers in the system a  instance of Organization
     * @return true if the process was successful.
     */
    public boolean registerOrganization() {
        boolean flag = m_RegistryOrganizations.registerOrganization(org);
        if(flag){
           LoginSystem login =MainController.getLoginSystem();
           login.newUser(org.getCollaborator().getCollaboratorName(),org.getCollaborator().getCollaboratorEmail(), User.UserType.COLLABORATOR);
           login.newUser(org.getManager().getManagerName(),org.getManager().getManagerEmail(), User.UserType.MANAGER);
        }
        return flag;
    }

}
