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
    private Button logoutbutton;
    private Button menubutton;

    //PRIVATE OBJECTS
    private Button generarhidatobutton;


    private String usern;
    Stage primaryStage;

    public Menu(String usern, Stage origin) throws IOException {
        primaryStage = origin;
        this.usern = usern;
        Parent root = FXMLLoader.load(getClass().getResource("/forms/Menu.fxml"));
        primaryStage.setTitle("Menú Principal - Hidato Game");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();


        //REFERENCES
        username = (Label) primaryStage.getScene().lookup("#usernamelabel");
        logoutbutton = (Button) primaryStage.getScene().lookup("#logoutbutton");
        menubutton = (Button) primaryStage.getScene().lookup("#menubutton");

        //PRIVATE REFERENCES
        generarhidatobutton = (Button) primaryStage.getScene().lookup("#generarhidatobutton");

        //ACTIONS
        logoutbutton.setOnMouseClicked(e -> {
            try {
                logout();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        menubutton.setOnMouseClicked(e-> {
            try {
                returnmenu();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        generarhidatobutton.setOnMouseClicked(e-> {
            try {
                generarhidato();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        //INITIALIZE GUI
        username.setText(usern);
    }

    private void logout() throws IOException {
        primaryStage.close();
        primaryStage = new Stage();
        Index i = new Index(primaryStage);
    }

    private void returnmenu() throws IOException {
        primaryStage.close();
        primaryStage = new Stage();
        Menu m = new Menu(usern,primaryStage);
    }

    private void generarhidato() throws IOException {
        primaryStage.close();
        primaryStage = new Stage();
        GenerarHidato gh = new GenerarHidato(usern,primaryStage);
    }
}