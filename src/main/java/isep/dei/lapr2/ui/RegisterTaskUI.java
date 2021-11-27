package isep.dei.lapr2.ui;

import isep.dei.lapr2.controller.RegisterTaskController;
import isep.dei.lapr2.model.Task;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * UI class for the Register Task Scene.
 */
public class RegisterTaskUI implements Initializable {
    private CollaboratorMenuUI collaboratorMenuUI;
    private RegisterTaskController taskController;


    @FXML
    private TextField txtID;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtDuration;

    /**
     * Method that register the task
     * @param event Clicking the button.
     */
    @FXML
    void actionRegister(ActionEvent event) {
        try {
            taskController.newTask(txtID.getText(), Integer.parseInt(txtDuration.getText()), Double.parseDouble(txtCost.getText()), txtCategory.getText(), txtDescription.getText());
                taskController.registerTask();
                AlertBox.createAlert(Alert.AlertType.INFORMATION, "Success", "The task was registered on the system !").show();
                openWindow(collaboratorMenuUI);
        }catch(NumberFormatException e ){
            AlertBox.createAlert(Alert.AlertType.ERROR, "Error", "Invalid format for Duration or Cost Per Hour !").show();
        }catch (IllegalArgumentException e){
            AlertBox.createAlert(Alert.AlertType.ERROR, "Error", e.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Method that initiates the Scene.
     */
    public void start(){
        taskController=new RegisterTaskController();
    }

    /**
     * Method that sets up in the Parent Scene the current Scene.
     * @param collaboratorMenuUI Parent UI
     */
    public void openWindow(CollaboratorMenuUI collaboratorMenuUI){
        try{
            this.collaboratorMenuUI=collaboratorMenuUI;
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/RegisterTaskScene.fxml"));
            loader.setController(this);
             this.collaboratorMenuUI.setBorderCenter((Node)loader.load());
            start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
