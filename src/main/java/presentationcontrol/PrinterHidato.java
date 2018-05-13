package presentationcontrol;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Vector;

public class PrinterHidato {
    private Label username;
    private Button logoutbutton;
    private Button menubutton;
    private Parent root2;
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
        root2 = root;
        primaryStage.setTitle("Generar Hidato - Hidato Game");
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
                e1.printStackTrace();board
            }
        });
        boardpane.setOnMouseClicked(e->boardclicked());


        //INITIALIZE GUI
        username.setText(usern);
    }

    private void boardclicked() {
        System.out.println("Board Pane");
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

    public void createboardsquare() {
        Double x;
        Double y = 100.0;
        for(int i = 0; i < hidato.size(); ++i) {
            Vector<Polygon> aux = new Vector<>();
            x = 100.0;
            for (int j = 0; j < hidato.get(0).size(); ++j) {
                SquareUI tu = new SquareUI(i, j, hidato.get(i).get(j));
                Group g = new Group();
                StackPane actual = tu.getStackPane();
                g.getChildren().add(actual);
                g.setTranslateX(x);
                g.setTranslateY(y);
                boardpane.getChildren().add(g);
                x += 100;
            }
            y+=100;
            board.add(aux);
        }
    }

    public void createboardtriangle() {
        Double x;
        Double y = 100.0;
        for(int i = 0; i < hidato.size(); ++i) {
            Vector<Polygon> aux = new Vector<>();
            x = 100.0;
            for (int j = 0; j < hidato.get(0).size(); ++j) {
                TriangleUI tu = new TriangleUI(i, j, hidato.get(i).get(j));
                Group g = new Group();
                StackPane actual = tu.getStackPane();
                g.getChildren().add(actual);
                g.setTranslateX(x);
                g.setTranslateY(y);
                boardpane.getChildren().add(g);
                x += 50;
            }
            y+=50;
            board.add(aux);
        }
    }

    public void createboardhexagon() {
        //TODO : Dinamically set width and height
        Double x;
        Double y = 100.0;
        Double y2 = 160.0;
        for(int i = 0; i < hidato.size(); ++i) {
            Vector<Polygon> aux = new Vector<>();
            if(i % 2 == 0) x = 100.0;
            else x= 145.0;
            for (int j = 0; j < hidato.get(0).size(); ++j) {
                HexagonUI tu = new HexagonUI(i, j, hidato.get(i).get(j));
                Group g = new Group();
                StackPane actual = tu.getStackPane();
                g.getChildren().add(actual);
                g.setTranslateX(x);
                if(i % 2 == 0) g.setTranslateY(y);
                else g.setTranslateY(y2);
                boardpane.getChildren().add(g);
                x += 90;
            }
            if(i % 2 == 0) y+=120;
            else y2+=120;
            board.add(aux);
        }
    }
}
