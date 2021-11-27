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
 * UI for the Scene of Collaborator Menu
 */
public class CollaboratorMenuUI implements Initializable {

    private LoginPageUI loginPageUI;
    private RegisterTaskUI registerTaskUI;
    private RegisterFreelancerUI registerFreelancerUI;
    private RegisterTransactionUI transactionCreationUI;
    private UploadTransactionFromFileUI uploadTransactionFromFileUI;
    private StatsAboutFreelancerInOrganizationUI statsFreelancerOrgUI;
    @FXML
    private Label lblNome;
    @FXML
    private Label lblOrganizacao;
    @FXML
    private Label lblMensagem;

    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Method that shows the scene of Creating a task
     * @param mouseEvent Clicking the Button
     */
    @FXML
    public void createTaskAction(MouseEvent mouseEvent) {
        if (registerTaskUI == null) {
            registerTaskUI = new RegisterTaskUI();
        }
        registerTaskUI.openWindow(this);
    }

    /**
     * Method that shows the Scene of Login Menu after Logout
     * @param mouseEvent Clicking the logout Button
     */
    @FXML
    public void logoutAction(MouseEvent mouseEvent) {
        loginPageUI.logout();
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
        Label label = new Label("Welcome Back, Collaborator");
        label.setFont(Font.font("System Bold", FontWeight.BOLD, 15));
        borderPane.setCenter(label);
    }

    /**
     * Method that shows the Scene of Creating a new Freelancer
     * @param mouseEvent Clicking a new Freelancer Button
     */
    @FXML
    void createFreelancerAction(MouseEvent mouseEvent) {
        if (registerFreelancerUI == null) {
            registerFreelancerUI = new RegisterFreelancerUI();
        }
        registerFreelancerUI.openWindow(this);
    }
    /**
     * Method that shows the Scene of Creating a new Transaction
     * @param mouseEvent Clicking a new Transaction Button
     */
    @FXML
    void createTransactionAction(MouseEvent mouseEvent) {
        if (transactionCreationUI == null) {
            transactionCreationUI = new RegisterTransactionUI();
        }
        transactionCreationUI.openWindow(this);
    }
    /**
     * Method that shows the Scene of Uploading Transactions
     * @param mouseEvent Clicking uploading Transactions Button
     */
    @FXML
    void uploadTransactionsAction(MouseEvent mouseEvent) {
        if (uploadTransactionFromFileUI == null) {
            uploadTransactionFromFileUI = new UploadTransactionFromFileUI();
        }
        uploadTransactionFromFileUI.openWindow(this);
    }
    /**
     * Method that shows the Scene of Showing statistics of Organization
     * @param mouseEvent Clicking a Show Statistics of Organization Button
     */
    @FXML
    void showStatisticsOrganizationAction(MouseEvent mouseEvent) {
        if (statsFreelancerOrgUI == null) {
            statsFreelancerOrgUI = new StatsAboutFreelancerInOrganizationUI();
        }
        statsFreelancerOrgUI.openWindow(this);
    }

    /**
     * Method that sets in the menu the name of Current Collaborator using the application
     */
    public void setLabelNome(){
        lblOrganizacao.setText(String.format("Organization: %s",MainController.getPlatform().getRegistryOrganizations().getOrgByUserEmail(MainController.getLoginSystem().getLoggedUserEmail()).getOrgName()));
        lblNome.setText(String.format("Name: %s", MainController.getLoginSystem().getLoggedUserName()));
    }

    /**
     * Method that sets the Parent Controller of the Scene
     * @param parentUI
     */
    public void associateParentUI(LoginPageUI parentUI) {
        loginPageUI = parentUI;
    }

    /**
     * Method that sets in the Border Center a new Scene.
     * @param a
     */
    public void setBorderCenter(Node a ){
        borderPane.setCenter(a);
    }

    public void mouseExited(MouseEvent mouseEvent) {
        ((Label) mouseEvent.getSource()).setOpacity(1);
    }

    public void mouseMoved(MouseEvent mouseEvent) {
        ((Label) mouseEvent.getSource()).setOpacity(0.6);
    }


}
