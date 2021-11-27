
package isep.dei.lapr2.login;

import isep.dei.lapr2.algorithms.PasswordGenerator;
import isep.dei.lapr2.controller.MainController;

import java.io.Serializable;
import java.util.List;

/**
 * Class that manages the login system
 */
public class LoginSystem implements Serializable {

    /**
     * The registry of users
     */
    private RegistryUsers regUsers;
    /**
     * The actual user session
     */
    private UserSession userSession;

    /**
     * Constructor that initializes the atributes
     */
    public LoginSystem() {
        this.regUsers = new RegistryUsers();
        this.userSession = new UserSession();
    }

    /**
     * Register a new user in the system
     * @param userName the name of the user
     * @param userEmail the email of the user
     * @param userType the type of the user
     * @return true if the user was registered, false if not
     */
    public boolean newUser(String userName, String userEmail, User.UserType userType) {
        PasswordGenerator pg = new PasswordGenerator();
        String password = pg.generatePassword(7);
        MainController.getPlatform().getExternalEmailSender().sendEmail(userEmail, "Password of new " + userType.name() + " \"" + userName+"\"", "The password is the following: "+password);
        User user = new User(userName, userEmail, password, userType);
        return regUsers.newUser(user);
    }

    /**
     * Verifies if there is a logged user
     * @return true if there is a logged user, false if not
     */
    public boolean isLoggedIn() {
        return userSession.isLoggedIn();
    }

    /**
     * Do login in the system
     * @param userEmail the email of the user
     * @param userPassword the password of the user
     * @return true if the user exists and the password is right, false if not
     */
    public boolean doLogin(String userEmail, String userPassword) {
        User user = regUsers.getUserByEmail(userEmail);
        if (user == null) return false;
        if (user.hasPassword(userPassword)) {
            this.userSession.doLogin(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Do logout of the user logged in the system
     */
    public void doLogout() {
        this.userSession.doLogout();
    }

    /**
     * Returns the name of the logged user
     * @return the name of the logged user
     */
    public String getLoggedUserName() {
        if (isLoggedIn()) {
            return this.userSession.getLoggedUser().getUserName();
        } else return null;
    }

    /**
     * Returns the email of the logged user
     * @return the email of the logged user
     */
    public String getLoggedUserEmail() {
        if (isLoggedIn()) {
            return this.userSession.getLoggedUser().getUserEmail();
        } else return null;
    }

    /**
     * Returns the type of the logged user
     * @return the type of the logged user
     */
    public User.UserType getLoggedUserType() {
        if (isLoggedIn()) {
            return this.userSession.getLoggedUser().getUserType();
        } else return null;
    }
    
}
