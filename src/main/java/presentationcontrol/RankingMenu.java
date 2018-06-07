package presentationcontrol;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import domaincontrol.Ranking;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.awt.*;
import java.io.File;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class RankingMenu {
    private Label username;
    private Button logoutbutton;
    private Button menubutton;

    //PRIVATE OBJECTS

    private Stage primaryStage;
    private JFXTabPane tabpane;
    private PresentationCtrl pc;

    private Ranking hard;
    private Ranking medium;
    private Ranking easy;


    public RankingMenu(PresentationCtrl pc) throws IOException {
        this.pc = pc;
        primaryStage = pc.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/forms/RankingMenu.fxml"));
        primaryStage.setTitle("Ranking - Hidato Game");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();


        //REFERENCES
        username = (Label) primaryStage.getScene().lookup("#usernamelabel");
        logoutbutton = (Button) primaryStage.getScene().lookup("#logoutbutton");
        menubutton = (Button) primaryStage.getScene().lookup("#menubutton");
        tabpane = (JFXTabPane) primaryStage.getScene().lookup("#tabpane");

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

        //INITIALIZE GUI
        username.setText(pc.getUsern());
        easy = pc.loadRanking("Easy");
        medium = pc.loadRanking("Medium");
        hard = pc.loadRanking("Hard");

        Tab tabhard = tabFiller(hard);
        Tab tabmedium = tabFiller(medium);
        Tab tabeasy = tabFiller(easy);

        tabhard.setText("Difícil");
        tabpane.getTabs().add(tabhard);
        tabmedium.setText("Mitjà");
        tabpane.getTabs().add(tabmedium);
        tabeasy.setText("Fàcil");
        tabpane.getTabs().add(tabeasy);

    }

    private Tab tabFiller(Ranking ranking) {
        TableView table = new TableView();
        table.setEditable(false);
        TableColumn name = new TableColumn("Nom d'usuari");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setPrefWidth(493);
        TableColumn time = new TableColumn("Temps");
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        time.setPrefWidth(493);
        table.getColumns().addAll(name,time);
        Tab t = new Tab();
        ArrayList<Pair<String, Integer>> players = ranking.getResults();
        //Pane pane = new Pane();
        for (Pair str : players) {
            int seconds = ((Integer) str.getValue());
            int minutes = 0;
            int hours = 0;
            while(seconds > 60) {
                ++minutes;
                seconds -= 60;
            }
            while(minutes > 60) {
                ++hours;
                minutes -= 60;
            }
            String compose =String.valueOf(hours) + " h " + String.valueOf(minutes) + " m " + String.valueOf(seconds) + " s";
            RankingRow rr = new RankingRow((String) str.getKey(),compose);
            table.getItems().add(rr);
        }
        t.setContent(table);
        return t;
    }

    private void logout() throws IOException {
        Index i = new Index(pc);;
    }

    private void returnmenu() throws IOException {
        Menu m = new Menu(pc);
    }
}