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

    private Stage primaryStage;
    private PresentationCtrl pc;


    public Menu(PresentationCtrl pc) throws IOException {
        this.pc = pc;
        primaryStage = pc.getPrimaryStage();
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
        username.setText(pc.getUsern());
    }

    private void logout() throws IOException {
        primaryStage.close();
        primaryStage = new Stage();
        pc.setPrimaryStage(primaryStage);
        Index i = new Index(pc);
    }

    private void returnmenu() throws IOException {
        primaryStage.close();
        primaryStage = new Stage();
        pc.setPrimaryStage(primaryStage);
        Menu m = new Menu(pc);
    }

    private void generarhidato() throws IOException {
        primaryStage.close();
        primaryStage = new Stage();
        pc.setPrimaryStage(primaryStage);
        GenerarHidato gh = new GenerarHidato(pc);
    }
}