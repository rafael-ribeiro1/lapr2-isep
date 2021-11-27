
package isep.dei.lapr2.model;

import isep.dei.lapr2.algorithms.CurrencyConverter;
import isep.dei.lapr2.algorithms.EmailSender;
import isep.dei.lapr2.model.RegistryClasses.RegistryFreelancers;
import isep.dei.lapr2.model.RegistryClasses.RegistryOrganizations;
import isep.dei.lapr2.model.RegistryClasses.RegistryTransactions;
import isep.dei.lapr2.model.utils.PropertiesManager;

import java.io.IOException;
import java.io.Serializable;

/**
 * Class that represents the Platform.
 */
public class Platform implements Serializable {
    /**
     * Registry of the Freelancer
     */
    private RegistryFreelancers m_regFree;
    /**
     * Registry of the Organization.
     */
    private RegistryOrganizations m_regOrgs;
    /**
     * Registry of the Transaction.
     */
    private RegistryTransactions m_regTransaction;
    /**
     * External Email Sender implemented in the Platform
     */
    private ExternalEmailSender emailSender;
    /**
     * External Converter Currency implemented in the Platform.
     */
    private ExternalConverterCurrency converterCurrency;

    /**
     * Constructor of the Platform.
     */
    public Platform() {
        m_regFree= new RegistryFreelancers();
        m_regOrgs = new RegistryOrganizations();
        m_regTransaction=new RegistryTransactions();
        try{
            PropertiesManager props = new PropertiesManager();
            emailSender=props.getEmailSender();
            converterCurrency=props.getCurrencyConverter();
        }catch (IOException e){
         emailSender = new EmailSender();
         converterCurrency = new CurrencyConverter();
        }
    }

    /**
     * Getter of the Registry of Organizations
     * @return Registry Organizations.
     */
    public RegistryOrganizations getRegistryOrganizations() {
        return m_regOrgs;
    }

    /**
     * Getter of the Registry of the Freelancers.
     * @return Registry of the Freelancers
     */
    public RegistryFreelancers getRegistryFreelancer(){return m_regFree;}

    /**
     * Getter of the Registry of the Transaction
     * @return Registry of The Transaction.
     */
    public RegistryTransactions getRegistryTransaction(){return m_regTransaction;}

    /**
     * Getter of the Email Sender
     * @return Interface of the email Sender
     */
    public ExternalEmailSender getExternalEmailSender() {
        return this.emailSender;
    }

    /**
     * Getter of the Converter Currency
     * @return Converter Currency
     */
    public ExternalConverterCurrency getExternalConverterCurrency() {
        return this.converterCurrency;
    }
}
