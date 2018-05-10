package presentationcontrol;

import com.jfoenix.controls.JFXHamburger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class GenerarHidato {
    private Label username;
    private JFXHamburger menuhamburger;
    private Pane menupane;


    private String usern;
    Stage primaryStage;

    public GenerarHidato(String usern, Stage origin) throws IOException {
        primaryStage = origin;
        this.usern = usern;
        Parent root = FXMLLoader.load(getClass().getResource("/forms/GenerarHidato.fxml"));
        primaryStage.setTitle("Hidato Game");
        primaryStage.setScene(new Scene(root, 960, 540));
        primaryStage.setResizable(false);
        //primaryStage.setFullScreen(true);
        primaryStage.show();


        //REFERENCES
        username = (Label) primaryStage.getScene().lookup("#usernamelabel");
        menuhamburger = (JFXHamburger) primaryStage.getScene().lookup("#menuhamburger");
        menupane = (Pane) primaryStage.getScene().lookup("#menupane");

        //ACTIONS
        menuhamburger.setOnMouseClicked(e->change_menu());

        //INITIALIZE GUI
        username.setText(usern);
    }

    private void change_menu() {
        double actual = menupane.getOpacity();
        if(actual == 0) {
            menupane.setOpacity(1.0);
            username.setTextFill(Color.web("#FFF"));
        }
        else{
            menupane.setOpacity(0);
            username.setTextFill(Color.web("#000"));
        }
    }
}
