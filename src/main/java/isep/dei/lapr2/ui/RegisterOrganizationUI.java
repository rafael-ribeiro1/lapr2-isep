package isep.dei.lapr2.ui;

import isep.dei.lapr2.controller.RegisterOrganizationController;
import isep.dei.lapr2.model.Organization;
import isep.dei.lapr2.ui.AdminMenuUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * UI Class for the Register Organization Register.
 */
public class RegisterOrganizationUI implements Initializable {
        private AdminMenuUI adminMenuUI;

        private RegisterOrganizationController controller;

        @FXML
        private TextField txtOrgNIF;

        @FXML
        private TextField txtCollabName;

        @FXML
        private TextField txtOrgName;

        @FXML
        private TextField txtManName;

        @FXML
        private TextField txtManEmail;

        @FXML
        private TextField txtCollabEmail;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
        }

    /**
     * Method that register the Organization
     * @param event Clicking the button.
     */
    @FXML
        void registerOrgAction(ActionEvent event) {
            try{
                controller.newOrganization(txtOrgName.getText(),txtOrgNIF.getText(),txtCollabName.getText(),txtCollabEmail.getText(),txtManName.getText(),txtManEmail.getText());
                if(controller.registerOrganization()){
                    AlertBox.createAlert(Alert.AlertType.INFORMATION, "Success", "Organization Register was Successful").show();
                    openWindow(adminMenuUI);
                }else{
                    AlertBox.createAlert(Alert.AlertType.ERROR, "Couldn't Create Organization", "The Organization was not registered on the system !").show();
                }
            }catch(IllegalArgumentException e) {
                AlertBox.createAlert(Alert.AlertType.ERROR, "Error", e.getMessage()).show();
            }
        }

    /**
     * Method that initiates the Scene
     */
    public void start(){
            this.controller = new RegisterOrganizationController();
        }

    /**
     * Method that sets in the parent Scene the current Scene.
     * @param adminMenuUI Parent Scene UI
     */
        public void openWindow(AdminMenuUI adminMenuUI){
            try{
                this.adminMenuUI=adminMenuUI;
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/RegisterOrganizationScene.fxml"));
                loader.setController(this);
                this.adminMenuUI.setBorderCenter((Node)loader.load());
                start();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }


