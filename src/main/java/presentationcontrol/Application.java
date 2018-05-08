package presentationcontrol;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    private ImageView avatar;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/forms/Application.fxml"));
        primaryStage.setTitle("Hidato Game");
        primaryStage.setScene(new Scene(root, 960, 540));
        //primaryStage.setFullScreen(true);
        primaryStage.show();

        //References
        avatar = (ImageView) primaryStage.getScene().lookup("#avatar");

        //Actions
        setAvatar();
    }

    //INITIALIZERS OF SCENE
    private void setAvatar() {
        Image image = new Image("/images/avatar.png");
        avatar.setImage(image);

    }

}
