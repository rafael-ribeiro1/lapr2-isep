package isep.dei.lapr2.model;

import java.io.Serializable;

/**
 * Class that represents Manager
 */
public class Manager implements Serializable {
    /**
     * Name of the manager
     */
    private String manName;
    /**
     * Email of the manager.
     */
    private String manEmail;

    /**
     * Construtor of the class
     * @param manName Name of the Manager
     * @param manEmail Email of the Manager
     */
    public Manager(String manName, String manEmail) {
        setManName(manName);
        setManEmail(manEmail);
    }

    /**
     * Setter that validates and defines the name of the Manager
     * @param manName Name of the Manager
     */
    public void setManName(String manName) {
        if(manName.trim().isEmpty()||manName==null){
            throw new IllegalArgumentException("Empty Manager Name !");
        }
        this.manName = manName;
    }

    /**
     * Setter that validates and defines the Email of the Manager
     * @param manEmail Email of the manager
     */
    public void setManEmail(String manEmail) {
        if(manEmail.trim().isEmpty()||manEmail==null){
            throw new IllegalArgumentException("Empty Manager Email !");
        }
        this.manEmail = manEmail;
    }

    /**
     * Getter of the Manager name
     * @return Manager Name
     */
    public String getManagerName(){
        return this.manName;
    }

    /**
     * Getter of the Manager Email
     * @return Manager Email
     */
    public String getManagerEmail(){
        return this.manEmail;
    }

    /**
     * Method that returns if the Manager has email or not
     * @param email
     * @return
     */
    public boolean hasEmail(String email) {
        return this.manEmail.equals(email);
    }

}

