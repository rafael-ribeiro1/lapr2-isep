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
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * UI class of the Menu Manager Scene
 * @author G33
 */
public class ManagerMenuUI implements Initializable {
    SetPaymentsDatetimeUI setPaymentsDatetimeUI;
    StatsAboutFreelancerInOrganizationUI statsFreelancerOrgUI;
    private LoginPageUI loginPageUI;

    @FXML
    private Label loginMessageLBL;
    @FXML
    private Label lblNome;
    @FXML
    private Label lblOrganizacao;
    @FXML
    private BorderPane borderPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    /**
     * Method that shows the scene of setting payment Time.
     * @param mouseEvent Clicking on the label
     */
    public void setPaymentTimeAction(MouseEvent mouseEvent) {
        if(setPaymentsDatetimeUI==null){
            setPaymentsDatetimeUI= new SetPaymentsDatetimeUI();
        }
        setPaymentsDatetimeUI.openWindow(this);
    }
    /**
     * Method that shows the scene of show Statistics of the Organization.
     * @param mouseEvent Clicking on the label
     */
    public void showStatisticsOrganizationAction(MouseEvent mouseEvent) {
        if(statsFreelancerOrgUI==null){
            statsFreelancerOrgUI= new StatsAboutFreelancerInOrganizationUI();
        }
        statsFreelancerOrgUI.openWindow(this);
    }
    /**
     * Method that shows the scene of Login after the LogOut.
     * @param mouseEvent Clicking on the label
     */
    public void logoutAction(MouseEvent mouseEvent) {
        loginPageUI.logout();
        ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        Label label = new Label("Welcome Back, Manager");
        label.setFont(Font.font("System Bold", FontWeight.BOLD, 15));
        borderPane.setCenter(label);
    }

    /**
     * Method that sets in the Label the name of the current user.
     */
    public void setLabelNome(){
        lblOrganizacao.setText(String.format("Organization: %s",MainController.getPlatform().getRegistryOrganizations().getOrgByUserEmail(MainController.getLoginSystem().getLoggedUserEmail()).getOrgName()));
        lblNome.setText(String.format("Name: %s", MainController.getLoginSystem().getLoggedUserName()));
    }

    /**
     * Method that sets the Controller of the Parent Scene.
     * @param parentUI Parent Controller
     */
    public void associateParentUI(LoginPageUI parentUI) {
        loginPageUI = parentUI;
    }

    /**
     *  Method that sets the scene in the border.
     * @param load Scene
     */
    public void setBorderCenter(Node load) {
        borderPane.setCenter(load);
    }
    public void mouseExited(MouseEvent mouseEvent) {
        ((Label) mouseEvent.getSource()).setOpacity(1);
    }

    public void mouseMoved(MouseEvent mouseEvent) {
        ((Label) mouseEvent.getSource()).setOpacity(0.6);
    }
}
