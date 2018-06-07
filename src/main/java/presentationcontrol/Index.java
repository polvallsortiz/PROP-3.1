package presentationcontrol;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import com.sun.javafx.application.LauncherImpl;

public class Index extends Application {

    private ImageView avatar;
    private TextField username;
    private Button enter;
    private String usern;
    private Stage primaryStage;
    private PresentationCtrl pc;
    private Semaphore semwork;
    private Semaphore semfinished;

    public Index() {
    }

    public void main(String[] args) {
        //System.out.println("A MAIN");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //System.out.println("A START");
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/forms/Index.fxml"));
        primaryStage.setTitle("Hidato Game");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();

        //References
        avatar = (ImageView) primaryStage.getScene().lookup("#avatar");
        username = (TextField) primaryStage.getScene().lookup("#usernamefield");
        enter = (Button) primaryStage.getScene().lookup("#enterbutton");

        //Actions
        setAvatar();
        enter.setOnMouseClicked(e -> {
            try {
                enter_system();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }


    public Index(PresentationCtrl pc) throws IOException {
        this.pc = pc;
        primaryStage = pc.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/forms/Index.fxml"));
        primaryStage.setTitle("Hidato Game");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();

        //References
        avatar = (ImageView) primaryStage.getScene().lookup("#avatar");
        username = ((TextField) primaryStage.getScene().lookup("#usernamefield"));
        enter = (Button) primaryStage.getScene().lookup("#enterbutton");

        //Actions
        setAvatar();
        enter.setOnMouseClicked(e -> {
            try {
                enter_system();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    //INITIALIZERS OF SCENE
    private void setAvatar() {
        Image image = new Image("/images/avatar.png");
        avatar.setImage(image);
        Image icon = new Image("/images/icon.png");
        primaryStage.getIcons().add(icon);
    }

    //FUNCTIONS
    private void enter_system() throws Exception {
        usern = username.getText();
        if (usern.length() == 0) {
            username.setPromptText("Error, l'usuari no pot ser buit");
        } else {
            pc = new PresentationCtrl();
            pc.setUsern(usern);
            pc.setPrimaryStage(primaryStage);
            Menu menu = new Menu(pc);
        }
    }

    public void setsemaphores(Semaphore semwork, Semaphore semfinished) {
        this.semfinished = semfinished;
        this.semwork = semwork;
    }

    @Override
    public void stop() throws IOException {
        //System.out.println("A EXIT");

        // Save file
    }


}
