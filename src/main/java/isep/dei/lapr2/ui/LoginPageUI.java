package isep.dei.lapr2.ui;

import isep.dei.lapr2.controller.MainController;
import isep.dei.lapr2.login.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * UI class for LoginPage Scene.
 */
public class LoginPageUI implements Initializable {

    private LoginSystem loginSystem;
    private AdminMenuUI adminMenuUI;
    private CollaboratorMenuUI collaboratorMenuUI;
    private ManagerMenuUI managerMenuUI;
    private Stage loginPageStage;
    private Stage adminMenuStage;
    private Stage managerMenuStage;
    private Stage collaboratorMenuStage;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPwd;
    @FXML
    private Label lblError;

    /**
     * Method that initializes the Interface of the Organization.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminMenuScene.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            adminMenuStage = new Stage();
            adminMenuStage.initStyle(StageStyle.UNDECORATED);
            adminMenuStage.initModality(Modality.APPLICATION_MODAL);
            adminMenuStage.setTitle("Administrator - T4J Payments");
            adminMenuStage.setResizable(false);
            adminMenuStage.getIcons().add(new Image("/logos/admin.png"));
            adminMenuStage.setScene(scene);
            adminMenuUI = loader.getController();
            adminMenuUI.associateParentUI(this);
            adminMenuStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    logout();
                }
            });
            String css = this.getClass().getResource("/styles/Styles.css").toExternalForm();
            scene.getStylesheets().add(css);
        } catch(IOException e) {
            AlertBox.createAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
        // Manager Menu initialization
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ManagerMenuScene.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            managerMenuStage = new Stage();
            managerMenuStage.initStyle(StageStyle.UNDECORATED);
            managerMenuStage.initModality(Modality.APPLICATION_MODAL);
            managerMenuStage.setTitle("Manager - T4J Payments");
            managerMenuStage.setResizable(false);
            managerMenuStage.getIcons().add(new Image("/logos/manager.png"));
            managerMenuStage.setScene(scene);
            managerMenuUI = loader.getController();
            managerMenuUI.associateParentUI(this);
            managerMenuStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    logout();
                }
            });
            String css = this.getClass().getResource("/styles/Styles.css").toExternalForm();
            scene.getStylesheets().add(css);
        } catch(IOException e) {
            AlertBox.createAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
        // Collaborator Menu initialization
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CollaboratorMenuScene.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            collaboratorMenuStage = new Stage();
            collaboratorMenuStage.initStyle(StageStyle.UNDECORATED);
            collaboratorMenuStage.initModality(Modality.APPLICATION_MODAL);
            collaboratorMenuStage.setTitle("Collaborator - T4J Payments");
            collaboratorMenuStage.setResizable(false);
            collaboratorMenuStage.getIcons().add(new Image("/logos/collaborator.png"));
            collaboratorMenuStage.setScene(scene);
            collaboratorMenuUI = loader.getController();
            collaboratorMenuUI.associateParentUI(this);
            collaboratorMenuStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    logout();
                }
            });
            String css = this.getClass().getResource("/styles/Styles.css").toExternalForm();
            scene.getStylesheets().add(css);
        } catch(IOException e) {
            AlertBox.createAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }finally {
        }
    }

    /**
     * Method that sets the controller-
     * @param stage
     */
    public void associateStage(Stage stage) {
        this.loginPageStage = stage;
        loginSystem = MainController.getLoginSystem();
    }

    /**
     * Method that clears the current Data of the Interface.
     */
    public void cleanData() {
        this.txtEmail.clear();
        this.txtPwd.clear();
        lblError.setVisible(false);
        this.lblError.setTextFill(Paint.valueOf("WHITE"));
        txtEmail.requestFocus();
        txtPwd.setStyle("-fx-border-color: white;");
    }

    /**
     * Method that logouts the current session.
     */
    public void logout() {
        loginSystem.doLogout();
        loginPageStage.show();
    }

    /**
     * Method that activates on Exit of the Application
     * @param event Clicking the Exit Button.
     */
    @FXML
    private void actionExit(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("T4J Payments");
        alert.setHeaderText("Exit confirmation");
        alert.setContentText("Do you really want to exit the app?");

        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");

        if (alert.showAndWait().get() == ButtonType.CANCEL) {
            event.consume();
        } else {
            saveData();
            System.exit(0);
        }
    }

    /**
     * Method that saves the Data for the binary Files.
     */
    public void saveData() {
        try {
            ObjectOutputStream outPlat = new ObjectOutputStream(new FileOutputStream("platform.bin"));
            outPlat.writeObject(MainController.getPlatform());
            ObjectOutputStream outLogin = new ObjectOutputStream(new FileOutputStream("login.bin"));
            outLogin.writeObject(MainController.getLoginSystem());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that decides the SubMenu Scene depending on the User Login.
     * @param event
     */
    @FXML
    private void actionLogin(ActionEvent event) {
        String email = txtEmail.getText();
        String pwd = txtPwd.getText();
        if (MainController.getLoginSystem().doLogin(email, pwd)) {
            switch (MainController.getLoginSystem().getLoggedUserType()) {
                case ADMINISTRATOR:
                    adminMenuStage.show();
                    adminMenuUI.setLabel();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                    break;
                case MANAGER:
                    managerMenuStage.show();
                    managerMenuUI.setLabelNome();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                    break;
                case COLLABORATOR:
                    collaboratorMenuStage.show();
                    collaboratorMenuUI.setLabelNome();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                    break;
            }
            cleanData();
        } else {
            lblError.setVisible(true);
            txtPwd.setStyle("-fx-border-color: red;");
            lblError.setText("Your email or password is incorrect!");
            lblError.setTextFill(Paint.valueOf("RED"));
        }
    }
}
