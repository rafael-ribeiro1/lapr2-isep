
package isep.dei.lapr2.login;

import java.io.Serializable;

/**
 * Class that represents an user
 */
public class User implements Serializable {

    /**
     * The name of the user
     */
    private String userName;
    /**
     * The email of the user
     */
    private String userEmail;
    /**
     * The password of the user
     */
    private String userPassword;
    /**
     * The type of the user
     */
    private UserType userType;

    /**
     * Constructor that initializes a new user
     * @param userName the name of the user
     * @param userEmail the email of the user
     * @param userPassword the password of the user
     * @param userType the type of the user
     */
    public User(String userName, String userEmail, String userPassword, UserType userType) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userType = userType;
    }

    /**
     * Enumerator that contains the types of the user
     */
    public enum UserType {
        ADMINISTRATOR,
        COLLABORATOR,
        MANAGER;
    }

    /**
     * Returns the name of the user
     * @return the name of the user
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the email of the user
     * @return the email of the user
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Verifies if the user has an email
     * @param email email to verify
     * @return true if the user has the email, false if not
     */
    public boolean hasEmail(String email) {
        return this.userEmail.equals(email);
    }

    /**
     * Verifies if the user has a password
     * @param password password to verify
     * @return true if the password is right, false if not
     */
    public boolean hasPassword(String password) {
        return this.userPassword.equals(password);
    }

    /**
     * Returns the type of the user
     * @return the type of the user
     */
    public UserType getUserType() {
        return userType;
    }
    
}
