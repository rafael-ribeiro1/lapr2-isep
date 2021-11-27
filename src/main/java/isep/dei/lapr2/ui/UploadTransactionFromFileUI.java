/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isep.dei.lapr2.ui;

import isep.dei.lapr2.controller.UploadTransactionFromFileController;
import isep.dei.lapr2.model.Transaction;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

/**
 * UI Class for Uploading File Scene
 */
public class UploadTransactionFromFileUI implements Initializable{
    private CollaboratorMenuUI collaboratorMenuUI;
    private UploadTransactionFromFileController controller;
    Transaction trans;
    private File selectedFile;
    
    @FXML
    private Button btnFile;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     *  Method that sets up in the parent Scene the current Scene
     * @param collaboratorMenuUI UI Class of the parent Scene
     */
    void openWindow(CollaboratorMenuUI collaboratorMenuUI) {
        try{
            this.collaboratorMenuUI=collaboratorMenuUI;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UploadTransactionFromFileScene.fxml"));
            loader.setController(this);
            this.collaboratorMenuUI.setBorderCenter((Node)loader.load());
            start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void start() {
        
    }

    /**
     * Method that opens the File
     * @throws ParseException
     */
    @FXML
    private void OpenFileAction() throws ParseException{
        try {
            this.controller = new UploadTransactionFromFileController();
            selectedFile = controller.uploadTransactionfromFile();
            controller.savesDatafromFile(selectedFile);
            AlertBox.createAlert(Alert.AlertType.INFORMATION, "Set Historical Transactions Data",
                    "Success operation !").showAndWait();
            openWindow(collaboratorMenuUI);
        }catch(NullPointerException e){
            AlertBox.createAlert(Alert.AlertType.ERROR, "", "File Not Selected !").show();
        }catch (IllegalArgumentException d){
            AlertBox.createAlert(Alert.AlertType.ERROR, "Error", d.getMessage()).show();
        }
    }
}
