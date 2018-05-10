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
    private Stage second;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        this.second = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/forms/Index.fxml"));
        second.setTitle("Hidato Game");
        second.setScene(new Scene(root, 960, 540));
        second.setResizable(false);
        //primaryStage.setFullScreen(true);
        second.show();

        //References
        avatar = (ImageView) second.getScene().lookup("#avatar");
        username = (TextField) second.getScene().lookup("#usernamefield");
        enter = (Button) second.getScene().lookup("#enterbutton");

        //Actions
        setAvatar();
        enter.setOnMouseClicked(e-> {
            try {
                enter_system();
            } catch (IOException e1) {
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
    private void enter_system() throws IOException {
        usern = username.getText();
        if(usern.length() == 0) {
            username.setPromptText("Error, l'usuari no pot ser buit");
        }
        else {
            PresentationCtrl pc = new PresentationCtrl();
            pc.setUsername(usern);
            pc.setStage(second);
            pc.gui();
        }
    }


}
