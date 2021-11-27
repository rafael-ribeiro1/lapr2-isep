/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isep.dei.lapr2.ui;


import java.net.URL;
import java.util.ResourceBundle;

import isep.dei.lapr2.controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * UI Class for the Scene of Menu of the Administrator
 */
public class AdminMenuUI implements Initializable {
    RegisterOrganizationUI registerOrganizationUI;
    StatsAllFreelancersUI statsUI;
    SendEmailHighDelaysUI sendEmailUI;
    LoginPageUI loginPageUI;

    @FXML
    private Label lblEmail;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    @FXML
    private BorderPane borderPane;

    /**
     * Method that shows the scene for adding a Organization
     * @param mouseEvent Mouse Click in the Label
     */
    public void addNewOrganizationAction(MouseEvent mouseEvent) {
        if(registerOrganizationUI==null){
            registerOrganizationUI=new RegisterOrganizationUI();
        }
        registerOrganizationUI.openWindow(this);
    }

    /**
     *  Method that opens the scene for sending Email to Freelancers with High Task Delay
     * @param event Mouse Click in the Label
     */
    @FXML
    void showSendEmail(MouseEvent event) {
        if(sendEmailUI==null){
            sendEmailUI=new SendEmailHighDelaysUI();
        }
        sendEmailUI.openWindow(this);
    }

    /**
     * Method that show the Statics's of all Freelancers and Tasks in the System
     * @param mouseEvent Mouse Click in the Label
     */
    public void showStatisticsAction(MouseEvent mouseEvent) {
        if (statsUI == null) statsUI = new StatsAllFreelancersUI();
        statsUI.openWindow(this);
    }

    /**
     * Method that opens the Scene of Login after the Logout
     * @param mouseEvent
     */
    public void logoutAction(MouseEvent mouseEvent) {
        loginPageUI.logout();
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        Label label = new Label("Welcome Back, Administrator");
        label.setFont(Font.font("System Bold", FontWeight.BOLD, 15));
        borderPane.setCenter(label);
    }

    /**
     * Method that associates the controller of the parent Scene.
     * @param parentUI Parent Controller
     */
    public void associateParentUI(LoginPageUI parentUI) {
        loginPageUI = parentUI;
    }

    /**
     * Method that sets the Scene in the Main Border Center
     * @param a Scene pretented to colocate.
     */
    public void setBorderCenter(Node a){
        borderPane.setCenter(a);
    }

    /**
     * Method that collocates the name of the current User in the Menu.
     */
    public void setLabel(){
        lblEmail.setText(String.format("Email: %s",MainController.getLoginSystem().getLoggedUserEmail()));
    }

    public void mouseExit(MouseEvent mouseEvent) {
        ((Label) mouseEvent.getSource()).setOpacity(1);
    }
    /**
     * Method that changes the opacity depend in the Cursor
     * @param mouseEvent
     */
    public void mouseMoved(MouseEvent mouseEvent) {
        ((Label) mouseEvent.getSource()).setOpacity(0.6);
    }
}
