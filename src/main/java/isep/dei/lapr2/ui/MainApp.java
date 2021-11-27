package isep.dei.lapr2.ui;

import isep.dei.lapr2.controller.MainController;
import isep.dei.lapr2.login.LoginSystem;
import isep.dei.lapr2.login.User;
import isep.dei.lapr2.model.Platform;
import isep.dei.lapr2.ui.LoginPageUI;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.*;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginPageScene.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        stage.setTitle("Login - T4J Payments");
        stage.getIcons().add(new Image("/logos/login.png"));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
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
                    }
                }
            });
        readData();
        LoginPageUI loginPageUI = loader.getController();
        loginPageUI.associateStage(stage);

        stage.show();
        scheduleTimers();
        String css = this.getClass().getResource("/styles/Styles.css").toExternalForm();
        scene.getStylesheets().add(css);
    }

    private void readData() {
        File plat = new File("platform.bin");
        if (plat.length() != 0) {
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("platform.bin"));
                Platform platform = (Platform) in.readObject();
                MainController.setPlatform(platform);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        File login = new File("login.bin");
        if (login.length() == 0) {
            MainController.getLoginSystem().newUser("Admininstrator", "admin@t4j.com", User.UserType.ADMINISTRATOR);
        } else {
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("login.bin"));
                LoginSystem loginSystem = (LoginSystem) in.readObject();
                MainController.setLoginSystem(loginSystem);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

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

    private void scheduleTimers() {
        MainController.getPlatform().getRegistryFreelancer().scheduleAutomaticEmail();
        MainController.getPlatform().getRegistryOrganizations().schedulePayments();

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
