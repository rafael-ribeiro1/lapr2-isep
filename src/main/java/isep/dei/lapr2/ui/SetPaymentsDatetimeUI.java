
package isep.dei.lapr2.ui;

import isep.dei.lapr2.controller.SetPaymentsDatetimeController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * UI Class of the Set Payments DateTime Scene
 */
public class SetPaymentsDatetimeUI implements Initializable {

    private ManagerMenuUI managerMenuUI;
    private SetPaymentsDatetimeController controller;
    
    @FXML
    private TextField txtDay;
    @FXML
    private TextField txtHour;
    @FXML
    private TextField txtMin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    /**
     * Method that sets up the current Scene in the parent Scene.
     * @param managerMenuUI
     */
    public void openWindow(ManagerMenuUI managerMenuUI) {
        try{
            this.managerMenuUI=managerMenuUI;
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/SetPaymentsDatetimeScene.fxml"));
            loader.setController(this);
            this.managerMenuUI.setBorderCenter((Node)loader.load());
            initWindow();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Method that initiates the UI of the Scene.
     */
    public void initWindow() {
        this.controller = new SetPaymentsDatetimeController();
        controller.getOrganization();
    }

    /**
     * Method that sets the DateTime for the payments.
     * @param event Clicking the button.
     */
    @FXML
    private void actionSetDatetime(ActionEvent event) {
        try {
            if (txtDay.getText().equals("") || txtHour.getText().equals("") ||
                    txtMin.getText().equals("")) throw new IllegalArgumentException("Empty field(s)");
            int day = Integer.parseInt(txtDay.getText());
            int hour = Integer.parseInt(txtHour.getText());
            int min = Integer.parseInt(txtMin.getText());
            if (!controller.registerPaymentsDatetime(day, hour, min)) {
                throw new IllegalArgumentException("Invalid Data.");
            } else {
                AlertBox.createAlert(Alert.AlertType.INFORMATION, "Set Payments Datetime",
                        "Success operation").showAndWait();
                openWindow(managerMenuUI);
            }
        } catch (NumberFormatException e) {
            AlertBox.createAlert(Alert.AlertType.ERROR, "Error setting payments datetime",
                    "Invalid number format.").showAndWait();
        } catch (IllegalArgumentException e) {
            AlertBox.createAlert(Alert.AlertType.ERROR, "Error setting payments datetime",
                    e.getMessage()).showAndWait();
        }
    }
    
}
