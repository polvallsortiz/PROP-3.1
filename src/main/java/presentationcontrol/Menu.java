package presentationcontrol;

import com.jfoenix.controls.JFXHamburger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {
    private Label username;
    private JFXHamburger menuhamburger;
    private Pane menupane;
    private Button generarhidatobutton;
    private Button logoutbutton;


    private String usern;
    Stage primaryStage;

    public Menu(String usern, Stage origin) throws IOException {
        primaryStage = origin;
        this.usern = usern;
        Parent root = FXMLLoader.load(getClass().getResource("/forms/Menu.fxml"));
        primaryStage.setTitle("Hidato Game");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        //primaryStage.setFullScreen(true);
        primaryStage.show();


        //REFERENCES
        username = (Label) primaryStage.getScene().lookup("#usernamelabel");
        menuhamburger = (JFXHamburger) primaryStage.getScene().lookup("#menuhamburger");
        menupane = (Pane) primaryStage.getScene().lookup("#menupane");
        generarhidatobutton = (Button) primaryStage.getScene().lookup("#generarhidatobutton");
        logoutbutton = (Button) primaryStage.getScene().lookup("#logoutbutton");

        //ACTIONS
        menuhamburger.setOnMouseClicked(e -> change_menu());
        generarhidatobutton.setOnMouseClicked(e -> {
            try {
                generarhidato();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        logoutbutton.setOnMouseClicked(e -> {
            try {
                logout();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        //INITIALIZE GUI
        username.setText(usern);
    }

    private void change_menu() {
        double actual = menupane.getOpacity();
        if (actual == 0) {
            menupane.setOpacity(1.0);
            menupane.setDisable(false);
            username.setTextFill(Color.web("#FFF"));
        } else {
            menupane.setOpacity(0);
            menupane.setDisable(true);
            username.setTextFill(Color.web("#000"));
        }
    }

    private void generarhidato() throws IOException {
        primaryStage.close();
        primaryStage = new Stage();
        GenerarHidato gh = new GenerarHidato(usern, primaryStage);
    }

    private void logout() throws IOException {
        primaryStage.close();
        primaryStage = new Stage();
        Index i = new Index(primaryStage);
    }
}