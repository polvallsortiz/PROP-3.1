package presentationcontrol;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Vector;

public class PrinterHidato {
    private Label username;
    private Button logoutbutton;
    private Button menubutton;

    //PRIVATE OBJECTS
    private Pane boardpane;


    Vector<Vector<String>> hidato;
    Vector<Vector<Polygon>> board;
    private String usern;
    Stage primaryStage;

    public PrinterHidato(String usern, Stage origin,Vector<Vector<String>> hidato) throws IOException {
        this.hidato = hidato;
        board = new Vector<>();
        primaryStage = origin;
        this.usern = usern;
        Parent root = FXMLLoader.load(getClass().getResource("/forms/PrinterHidato.fxml"));
        primaryStage.setTitle("Hidato Game");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();


        //REFERENCES
        username = (Label) primaryStage.getScene().lookup("#usernamelabel");
        logoutbutton = (Button) primaryStage.getScene().lookup("#logoutbutton");
        menubutton = (Button) primaryStage.getScene().lookup("#menubutton");

        //PRIVATE REFERENCES
        boardpane = (Pane) primaryStage.getScene().lookup("#boardpane");

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

        //INITIALIZE GUI
        username.setText(usern);
        createboard();
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

    private void createboard() {
        for(int i = 0; i < hidato.size(); ++i) {
            Vector<Polygon> aux = new Vector<>();
            for(int j = 0; j < hidato.get(0).size(); ++j) {
                Polygon p = new Polygon();
                p.setFill(javafx.scene.paint.Color.valueOf("#FFFFFF"));
                p.getPoints().addAll(new Double[]{
                        0.0, 0.0,
                        20.0, 10.0,
                        10.0, 20.0 });
                aux.add(p);
                boardpane.getChildren().add(p);
            }
            board.add(aux);
        }
    }
}
