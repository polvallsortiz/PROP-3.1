package presentationcontrol;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Index extends Application {

    private ImageView avatar;
    private TextField username;
    private Button enter;
    private String usern;
    private Stage primaryStage;

    public Index() {
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
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
        enter.setOnMouseClicked(e-> {
            try {
                enter_system();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public Index(Stage origin) throws IOException {
        primaryStage = origin;
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
        enter.setOnMouseClicked(e-> {
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
    }

    //FUNCTIONS
    private void enter_system() throws Exception {
        usern = username.getText();
        if(usern.length() == 0) {
            username.setPromptText("Error, l'usuari no pot ser buit");
        }
        else {
            primaryStage.close();
            primaryStage = new Stage();
            Menu menu = new Menu(usern,primaryStage);
        }
    }


}
