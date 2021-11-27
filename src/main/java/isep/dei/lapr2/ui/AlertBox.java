/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isep.dei.lapr2.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Controller of the Alert Scene
 */
public class AlertBox {
    /**
     * Constructor
     */
    private AlertBox() {}

    /**
     * Method that creates the Alert Box
     * @param alertType Type of Alert
     * @param header Message of the Header
     * @param message Main Message
     * @return Alert Scene
     */
    public static Alert createAlert(Alert.AlertType alertType, String header, String message) {
        Alert alert = new Alert(alertType);
        
        alert.setTitle("App");
        alert.setHeaderText(header);
        alert.setContentText(message);
        if (alertType == Alert.AlertType.CONFIRMATION) {
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/logos/confirmar.png"));
        }
        if (alertType == Alert.AlertType.WARNING) {
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/logos/warning.png"));
        }
        if (alertType == Alert.AlertType.INFORMATION) {
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/logos/informacao.png"));
        }
        if (alertType == Alert.AlertType.ERROR) {
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/logos/aviso.svg"));
        }
        return alert;
    }
    
}
