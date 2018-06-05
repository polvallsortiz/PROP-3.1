package presentationcontrol;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import static javax.swing.JOptionPane.showMessageDialog;

public class Credits {
    private Label username;
    private Button logoutbutton;
    private Button menubutton;

    //PRIVATE OBJECTS
    private ImageView paulaphoto;
    private ImageView joanphoto;
    private ImageView polphoto;
    private Hyperlink mail1;
    private Hyperlink mail2;
    private Hyperlink mail3;

    private Stage primaryStage;
    private PresentationCtrl pc;

    public Credits(PresentationCtrl pc) throws IOException {
        this.pc = pc;
        primaryStage = pc.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/forms/Credits.fxml"));
        primaryStage.setTitle("Crèdits - Hidato Game");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();


        //REFERENCES
        username = (Label) primaryStage.getScene().lookup("#usernamelabel");
        logoutbutton = (Button) primaryStage.getScene().lookup("#logoutbutton");
        menubutton = (Button) primaryStage.getScene().lookup("#menubutton");
        paulaphoto = (ImageView) primaryStage.getScene().lookup("#paulaphoto");
        joanphoto = (ImageView) primaryStage.getScene().lookup("#joanphoto");
        polphoto = (ImageView) primaryStage.getScene().lookup("#polphoto");
        mail1 = (Hyperlink) primaryStage.getScene().lookup("#mail1");
        mail2 = (Hyperlink) primaryStage.getScene().lookup("#mail2");
        mail3 = (Hyperlink) primaryStage.getScene().lookup("#mail3");

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
        mail1.setOnMouseClicked(e->mail01());
        mail2.setOnMouseClicked(e->mail02());
        mail3.setOnMouseClicked(e->mail03());

        //INITIALIZE GUI
        username.setText(pc.getUsern());
        paulaphoto.setImage(new Image("/images/paula.pallares.png"));
        joanphoto.setImage(new Image("/images/joan.sanchez.garcia.png"));
        polphoto.setImage(new Image("/images/pol.valls.ortiz.png"));
    }

    private void logout() throws IOException {
        pc.setPrimaryStage(primaryStage);
        Index i = new Index(pc);;
    }

    private void returnmenu() throws IOException {
        Menu m = new Menu(pc);
    }

    private void mail01() {
        String myString = "paula.pallares@est.fib.upc.edu   ";
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        showMessageDialog(null, "Correu copiat al clipboard!", "",JOptionPane.INFORMATION_MESSAGE);
    }

    private void mail02() {
        String myString = "joan.sanchez.garcia@est.fib.upc.edu   ";
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        showMessageDialog(null, "Correu copiat al clipboard!", "",JOptionPane.INFORMATION_MESSAGE);
    }

    private void mail03() {
        String myString = "pol.valls.ortiz@est.fib.upc.edu   ";
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        showMessageDialog(null, "Correu copiat al clipboard!", "",JOptionPane.INFORMATION_MESSAGE);
    }
}
