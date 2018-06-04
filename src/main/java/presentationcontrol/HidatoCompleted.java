package presentationcontrol;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class HidatoCompleted {
    private Label username;
    private Button logoutbutton;
    private Button menubutton;

    private Label timelabel;


    private Stage primaryStage;
    private PresentationCtrl pc;


    public HidatoCompleted(PresentationCtrl pc) throws IOException {
        this.pc = pc;
        primaryStage = pc.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/forms/HidatoCompleted.fxml"));
        primaryStage.setTitle("Felicitats! - Hidato Game");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();


        //REFERENCES
        username = (Label) primaryStage.getScene().lookup("#usernamelabel");
        logoutbutton = (Button) primaryStage.getScene().lookup("#logoutbutton");
        menubutton = (Button) primaryStage.getScene().lookup("#menubutton");
        timelabel = (Label) primaryStage.getScene().lookup("#timelabel");

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
        int seconds = pc.finalTime();
        System.out.println(seconds);
        int h,m,s;
        h = seconds/60/60;
        m = (seconds - h*60*60) / 60;
        s = (seconds - m*60 - h*60*60);
        String result = String.valueOf(h) + " hores : " + String.valueOf(m) + " minuts : " + String.valueOf(s) + " segons";
        timelabel.setText(result);
        pc.addToRanking();
        //pc.saveRanking();
    }

    private void logout() throws IOException {
        Index i = new Index(pc);;
    }

    private void returnmenu() throws IOException {
        Menu m = new Menu(pc);
    }
}
