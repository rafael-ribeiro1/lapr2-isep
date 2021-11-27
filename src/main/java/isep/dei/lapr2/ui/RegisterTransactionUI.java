package isep.dei.lapr2.ui;

import isep.dei.lapr2.controller.RegisterTransactionController;
import isep.dei.lapr2.model.Freelancer;
import isep.dei.lapr2.model.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * UI Class for the Register Transaction UI.
 */
public class RegisterTransactionUI implements Initializable {
    private CollaboratorMenuUI collaboratorMenuUI;
    private RegisterTransactionController controller;
    @FXML
    private ComboBox<Freelancer> cmbFreelancer;

    @FXML
    private TextField txtData;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtDelay;

    @FXML
    private ComboBox<Task> cmbTask;
    @FXML
    private TextField txtID;
    @FXML
    private DatePicker datePicker;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Method that initiates the Scene.
     */
    public void start() {
        this.controller = new RegisterTransactionController();
        for (Freelancer p : controller.getListFreelancers()) {
            cmbFreelancer.getItems().add(p);
        }
        for (Task p : controller.getListTaskWithNoTransaction()) {
            cmbTask.getItems().add(p);
        }
    }

    /**
     * Method that register the Transaction.
     * @param event Clicking the button.
     */
    public void actionTransaction(ActionEvent event) {
        try {
            Date date = new Date(datePicker.getValue().getYear(), datePicker.getValue().getMonthValue(), datePicker.getValue().getDayOfMonth());
            controller.newTransaction(txtID.getText(),date,Integer.parseInt(txtDelay.getText()),txtDescription.getText(),cmbTask.getSelectionModel().getSelectedItem(),cmbFreelancer.getSelectionModel().getSelectedItem());
                if(controller.validate()){
                    Alert alert=defAlert();
                    if (alert.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    } else {
                        if (controller.registerTransation()){
                            controller.setTaskHasTransactionTrue();
                            AlertBox.createAlert(Alert.AlertType.INFORMATION, "Transaction Creation Successful", "The transaction was registered on the system").show();
                            openWindow(collaboratorMenuUI);
                        }
                }
                }

        }catch(NullPointerException e){
            AlertBox.createAlert(Alert.AlertType.ERROR, "Empty Date", "Please insert a Date !").showAndWait();
        } catch (NumberFormatException e) {
            AlertBox.createAlert(Alert.AlertType.ERROR, "Invalid Format for Number !", e.getMessage()).showAndWait();
        } catch (IllegalArgumentException e) {
            AlertBox.createAlert(Alert.AlertType.ERROR, "Error !", e.getMessage()).showAndWait();
        }
    }

    /**
     * Method that sets the current Scene in the parent Scene.
     * @param collaboratorMenuUI Parent Scene
     */
    public void openWindow(CollaboratorMenuUI collaboratorMenuUI) {
        try{
            this.collaboratorMenuUI=collaboratorMenuUI;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TransactionCreationScene.fxml"));
            loader.setController(this);
            this.collaboratorMenuUI.setBorderCenter((Node)loader.load());
            start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Method that shows a pop-up about the information about the Transaction.
     * @return
     */
    public Alert defAlert (){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(String.format("The Payment for the task %s is %.2f", controller.getTransaction().toString(), controller.getAmountToPay()));
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");
        return alert;
    }
}
