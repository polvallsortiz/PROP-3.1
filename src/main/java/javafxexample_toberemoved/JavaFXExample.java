package javafxexample_toberemoved;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;

public class JavaFXExample extends Application {

    private Label label;
    private Slider slider;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/forms/FormPresentationControl.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1920, 1080));
        //primaryStage.setFullScreen(true);//
        primaryStage.show();

        //References
        label = (Label) primaryStage.getScene().lookup("#dades");
        slider = (Slider) primaryStage.getScene().lookup("#slider");
        slider.setMin(0);
        slider.setMax(10);

        //Actions
        label.setOnMouseClicked(e->settext2());
        slider.setOnMouseDragged(e->changedata());

        settext();
    }

    private void settext() {
        label.setText("Hola");
    }

    private void settext2() {
        label.setText("Clicat!");
    }

    private void changedata() {
        Integer d =  (int) slider.getValue();
        label.setText(d.toString());
    }
}
