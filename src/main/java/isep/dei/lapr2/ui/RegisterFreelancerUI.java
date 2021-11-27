package isep.dei.lapr2.ui;

import isep.dei.lapr2.controller.RegisterFreelancerController;
import isep.dei.lapr2.model.Collaborator;
import isep.dei.lapr2.model.Freelancer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * UI Class for the Register Freelancer Scene.
 */
public class RegisterFreelancerUI implements Initializable {

    private CollaboratorMenuUI collaboratorMenuUI;

    private RegisterFreelancerController controller;
    @FXML
    private javafx.scene.layout.BorderPane BorderPane;
    @FXML
    private TextField txtName;
    @FXML
    private ComboBox<String> cmbCountry;
    @FXML
    private ComboBox<Freelancer.Expertise> cmbExpertise;
    @FXML
    private TextField txtBank;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtNIF;
    @FXML
    private TextField txtEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Method that initializes the scene
     */
    public void start(){
        this.controller = new RegisterFreelancerController();
        Freelancer.Expertise[] valuesExpertise= controller.getExpertiseArray();
        for (Freelancer.Expertise p : valuesExpertise){
            cmbExpertise.getItems().add(p);
        }
        for(String country : controller.getCountrys()){
            cmbCountry.getItems().add(country);
        }

    }

    /**
     * Method that register a Freelancer
     * @param event Clicking in the Button
     */
    public void actionRegister(ActionEvent event) {
            try{
                if(cmbCountry.getSelectionModel().getSelectedIndex()!=-1&&cmbExpertise.getSelectionModel().getSelectedIndex()!=-1){
                    controller.newFreelancer(txtName.getText(),cmbExpertise.getSelectionModel().getSelectedItem(),txtEmail.getText(),txtNIF.getText(),txtBank.getText(),txtAddress.getText(),cmbCountry.getSelectionModel().getSelectedItem());
                    controller.registerFreelancer();
                    AlertBox.createAlert(Alert.AlertType.INFORMATION, "Freelancer Creation Successful", "The Freelancer was registered on the system !").show();
                    openWindow(collaboratorMenuUI);
                }else{
                    AlertBox.createAlert(Alert.AlertType.ERROR, "Error", "Select the items first !").show();
                }
            }catch(IllegalArgumentException e) {
                AlertBox.createAlert(Alert.AlertType.ERROR, "Error", e.getMessage()).show();
            }
    }

    /**
     * Method that sets in the parent Scene , the current Scene.
     * @param collaboratorMenuUI
     */
    public void openWindow(CollaboratorMenuUI collaboratorMenuUI){
        try{
            this.collaboratorMenuUI=collaboratorMenuUI;
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/RegisterFreelancerScene.fxml"));
            loader.setController(this);
            this.collaboratorMenuUI.setBorderCenter((Node)loader.load());
            start();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
