package isep.dei.lapr2.ui;

import isep.dei.lapr2.controller.SendEmailHighDelaysContoller;
import isep.dei.lapr2.model.Freelancer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * UI class for the Sender of Emails with High Delay Scene.
 */
public class SendEmailHighDelaysUI implements Initializable {
    private AdminMenuUI adminMenuUI;
    private SendEmailHighDelaysContoller controller;

    @FXML
    private ListView<Freelancer> lstViewFreelancer;

    /**
     * Method that sends an email to the Current Selected Freelancer
     * @param event
     */
    @FXML
    void sendEmail(ActionEvent event) {
        try{
            controller.sendEmail(lstViewFreelancer.getSelectionModel().getSelectedItem());
            AlertBox.createAlert(Alert.AlertType.INFORMATION,"Success","Email sent to the Freelancer");
            openWindow(adminMenuUI);
        }catch(NullPointerException e){
            AlertBox.createAlert(Alert.AlertType.ERROR, "Error", "Select a freelancer first !").show();
        }catch(Exception e){
            AlertBox.createAlert(Alert.AlertType.ERROR, "Error", e.getMessage()).show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Method that starts the Scene.
     */
    public void start(){
        controller =new SendEmailHighDelaysContoller();
        lstViewFreelancer.getItems().setAll(controller.getFreelancersWithMeanTaskDelay());
    }

    /**
     * Method that set-up in the parent Scene the current Scene.
     * @param adminMenuUI UI class of the parent Scene.
     */
    public void openWindow(AdminMenuUI adminMenuUI){
        try{
            this.adminMenuUI=adminMenuUI;
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/AdminSendEmailToFreelancer.fxml"));
            loader.setController(this);
            this.adminMenuUI.setBorderCenter((Node)loader.load());
            start();

        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
