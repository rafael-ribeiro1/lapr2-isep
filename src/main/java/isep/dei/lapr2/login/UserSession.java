
package isep.dei.lapr2.login;

import java.io.Serializable;

/**
 * Class that represents the actual user session
 */
public class UserSession implements Serializable {

    /**
     * The logged user
     */
    private User m_loggedUser;

    /**
     * Constructor that initializes the logged user as null
     */
    public UserSession() {
        this.m_loggedUser = null;
    }

    /**
     * Verifies if there is a logged user
     * @return true if there is a logged user, false if not
     */
    public boolean isLoggedIn() {
        return this.m_loggedUser != null;
    }

    /**
     * Do login of a user
     * @param user the user to login
     */
    public void doLogin(User user) {
        this.m_loggedUser = user;
    }

    /**
     * Do logout to the user logged
     */
    public void doLogout() {
        this.m_loggedUser = null;
    }

    /**
     * Returns the logged user
     * @return the logged user or null if there is not a logged user
     */
    public User getLoggedUser() {
        return this.m_loggedUser;
    }
    
}
