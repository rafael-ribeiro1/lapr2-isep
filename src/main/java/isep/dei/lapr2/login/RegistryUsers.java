
package isep.dei.lapr2.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that register and saves the users of the system
 */
public class RegistryUsers implements Serializable {

    /**
     * List of users
     */
    private List<User> m_lstUsers;

    /**
     * Constructor that initializes the list
     */
    public RegistryUsers() {
        this.m_lstUsers = new ArrayList<>();
    }

    /**
     * Register a new user in the list
     * @param user the user to be registered
     * @return true if the user was registered, false if not
     */
    public boolean newUser(User user) {
        if (validatesUser(user)) {
            return this.m_lstUsers.add(user);
        } else {
            return false;
        }
    }

    /**
     * Validates if the email of the user already exists in the system
     * @param user the user to validate
     * @return true if the email of the user does not exist in the system, false if not
     */
    public boolean validatesUser(User user) {
        boolean valid = true;
        for (User u: m_lstUsers) {
            if (u.getUserEmail().equals(user.getUserEmail())) {
                return false;
            }
        }
        return valid;
    }

    /**
     * Returns the user with the email
     * @param email the email to search
     * @return the user with the email or null if the email is not registered
     */
    public User getUserByEmail(String email) {
        for (User user: m_lstUsers) {
            if (user.hasEmail(email)) return user;
        }
        return null;
    }
    
}
