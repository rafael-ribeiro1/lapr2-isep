
package isep.dei.lapr2.model.RegistryClasses;

import isep.dei.lapr2.controller.MainController;
import isep.dei.lapr2.model.Collaborator;
import isep.dei.lapr2.model.Manager;
import isep.dei.lapr2.model.Organization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages and keeps the organizations
 */
public class RegistryOrganizations implements Serializable {

    /**
     * The list of organizations
     */
    private List<Organization> m_lstOrganizations;

    /**
     * Initializes the list
     */
    public RegistryOrganizations() {
        m_lstOrganizations = new ArrayList<>();
    }

    /**
     * Returns the organization with an user with an email
     * @param email the email to search
     * @return the organization with an user with the email
     */
    public Organization getOrgByUserEmail(String email) {
        Organization orgReturn = null;
        for (Organization org: m_lstOrganizations) {
            Collaborator collab = org.getCollaborator();
            Manager man = org.getManager();
            if (collab.hasEmail(email) || man.hasEmail(email)) {
                orgReturn = org;
            }
        }
        return orgReturn;
    }

    /**
     * Creates a new organization
     * @param nameOrg the name of the organization
     * @param nif the NIF of the organization
     * @param nameCollab the name of the collaborator of the organization
     * @param emailCollab the email of the collaborator of the organization
     * @param nameMan the name of the manager of the organization
     * @param emailMan the email of the manager of the organization
     * @return the organization created
     */
    public Organization newOrganization(String nameOrg, String nif, String nameCollab, String emailCollab, String nameMan, String emailMan) {
        return new Organization(nameOrg, nif, nameCollab, emailCollab, nameMan, emailMan );
    }

    /**
     * Register a new organization in the list
     * @param org the organization to register
     * @return true if it was registered, false if not
     */
    public boolean registerOrganization(Organization org) {
        if (validate(org)){
            return addOrganization(org);
        }else{
            return false;
        }
    }

    /**
     * Add organization to the list
     * @param org the organization  to add
     * @return ture if it was added, false if not
     */
    public boolean addOrganization(Organization org){
        return m_lstOrganizations.add(org);
    }

    /**
     * Validates a organization
     * @param org the organization to validate
     * @return true if the organization is valid, false if not
     */
    public boolean validate(Organization org ){
        if(org.getCollaborator().getCollaboratorEmail().equals(org.getManager().getManagerEmail()))throw new IllegalArgumentException("Manager and Collaborator can not have the same Email");
        verifyExistance(org);
        if(existsOrgNif(org)) return false;
        return true;
    }

    /**
     * Verify if an organization exists in the list
     * @param org the organization to verify
     */
    public void verifyExistance(Organization org){
        for(Organization o : m_lstOrganizations){
            if(org.getCollaborator().getCollaboratorEmail().equals(o.getCollaborator().getCollaboratorEmail())||org.getCollaborator().getCollaboratorEmail().equals(o.getManager().getManagerEmail()))  {
                throw new IllegalArgumentException("The collaborator indicated already works for another Organization !");
            }
        }
        for(Organization o : m_lstOrganizations){
            if(org.getManager().getManagerEmail().equals(o.getCollaborator().getCollaboratorEmail())||org.getManager().getManagerEmail().equals(o.getManager().getManagerEmail()))  {
                throw new IllegalArgumentException("The Manager indicated already works for another Organization !");
            }
        }
        for (Organization o : m_lstOrganizations){
            if (org.equals(o)){
                throw new IllegalArgumentException("Organization already registered !");
            }
        }

    }

    /**
     * Verify if exists an organization with the same NIF registered
     * @param org the organization with the NIF to verify
     * @return true if it exists, false if not
     */
    public boolean existsOrgNif(Organization org){
        for (Organization o :m_lstOrganizations){
            if(o.getOrgNIF().equals(org.getOrgNIF())){
                if(!org.equals(o)){
                    throw new IllegalArgumentException("Organization NIF already registered on the system !");
                }else{
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Schedule automatic payments at the specified datetime of the organization
     */
    public void schedulePayments() {
        for (Organization org: m_lstOrganizations) {
            MainController.getPlatform().getRegistryTransaction().scheduleOrgPayments(org);
        }
    }



}
