
package isep.dei.lapr2.controller;

import isep.dei.lapr2.login.LoginSystem;
import isep.dei.lapr2.model.*;

/**
 * Main Controller of the Application
 */
public class MainController {
    /**
     * Platform Class
     */
    public static Platform m_platform = new Platform();
    /**
     * Login System Class
     */
    public static LoginSystem m_login = new LoginSystem();

    /**
     * Constructor of the Class
     */
    private MainController() {}

    /**
     * Getter for the Platform
     * @return Platform
     */
    public static Platform getPlatform() {
        return m_platform;
    }

    /**
     * Getter for the login system.
     * @return Login System
     */
    public static LoginSystem getLoginSystem() {
        return m_login;
    }

    /**
     * Setter of the platform Class
     * @param platform
     */
    public static void setPlatform(Platform platform) {
        m_platform = platform;
    }

    /**
     * Setter of the Login System.
     * @param loginSystem
     */
    public static void setLoginSystem(LoginSystem loginSystem) {
        m_login = loginSystem;
    }
    
}
