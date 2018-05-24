package presentationcontrol;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class GameCreator {
    private Label username;
    private Button logoutbutton;
    private Button menubutton;

    //PRIVATE OBJECTS
    private Button generatehidatobutton;
    private Button proposehidatobutton;
    private Stage primaryStage;
    private PresentationCtrl pc;


    public GameCreator(PresentationCtrl pc) throws IOException {
        this.pc = pc;
        primaryStage = pc.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/forms/GameCreator.fxml"));
        primaryStage.setTitle("Nova Partida - Hidato Game");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();


        //REFERENCES
        username = (Label) primaryStage.getScene().lookup("#usernamelabel");
        logoutbutton = (Button) primaryStage.getScene().lookup("#logoutbutton");
        menubutton = (Button) primaryStage.getScene().lookup("#menubutton");
        generatehidatobutton = (Button) primaryStage.getScene().lookup("#generatehidatobutton");
        proposehidatobutton = (Button) primaryStage.getScene().lookup("#proposehidatobutton");

        //PRIVATE REFERENCES

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
        generatehidatobutton.setOnMouseClicked(e-> {
            try {
                newgenerator();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        proposehidatobutton.setOnMouseClicked(e-> {
            try {
                proposehidato();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        //INITIALIZE GUI
        username.setText(pc.getUsern());
    }

    private void logout() throws IOException {
        Index i = new Index(pc);
    }

    private void returnmenu() throws IOException {
        Menu m = new Menu(pc);
    }

    private void newgenerator() throws IOException {
        GenerarHidato gh = new GenerarHidato(pc);
    }

    private void proposehidato() throws IOException {
        ProposarHidato ph = new ProposarHidato(pc);
    }
}
