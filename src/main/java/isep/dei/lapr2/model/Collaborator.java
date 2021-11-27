
package isep.dei.lapr2.model;
import java.io.Serializable;

/**
 * Class that represents a Collaborator from a Organization
 */
public class Collaborator implements Serializable {
    /**
     * Name of the collaborator.
     */
    private String collabName;
    /**
     * Email of the collaborator.
     */
    private String collabEmail;

    /**
     * Constructor of the class
     * @param collabName Collaborator Name
     * @param collabEmail Collaborator Email
     */
    public Collaborator(String collabName, String collabEmail) {
        setCollabName(collabName);
        setCollabEmail(collabEmail);
    }

    /**
     * Setter that defines the name of the Collaborator and Validates the introduced paramater.
     * @param collabName Name of Collaborator.
     */
    public void setCollabName(String collabName) {
        if(collabName.trim().isEmpty()||collabName==null){
            throw new IllegalArgumentException("Empty Collaborator Name !");
        }
        this.collabName = collabName;
    }
    /**
     * Setter that defines the Email of the Collaborator and Validates the introduced paramater.
     * @param collabEmail Email of Collaborator.
     */
    public void setCollabEmail(String collabEmail) {
        if(collabEmail.trim().isEmpty()||collabEmail==null){
            throw new IllegalArgumentException("Empty Collaborator Email !");
        }
        this.collabEmail = collabEmail;
    }

    /**
     * Getter that returns the name of the Collaborator.
     * @return Name of the collaborator.
     */
    public String getCollaboratorName(){
        return this.collabName;
    }

    /**
     * Getter that returns the email of the Collaborator.
     * @return Email of the collaborator.
     */
    public String getCollaboratorEmail(){
        return this.collabEmail;
    }

    /**
     * Getter that return if the Freelancer has email
     * @param email Email that its supposed to verify.
     * @return true if the email is equal
     */
    public boolean hasEmail(String email) {
        return this.collabEmail.equals(email);
    }
    
}
