package presentationcontrol;

import com.jfoenix.controls.JFXHamburger;
import domaincontrol.Hidato;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.util.Vector;

public class Menu extends Component {
    private Label username;
    private Button logoutbutton;
    private Button menubutton;

    //PRIVATE OBJECTS
    private Button newgamebutton;
    private Button loadgamebutton;
    private Button seerankingsbutton;
    private Button creditsbutton;

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
        loadgamebutton = (Button) primaryStage.getScene().lookup("#loadgamebutton");
        seerankingsbutton = (Button) primaryStage.getScene().lookup("#seerankingsbutton");
        creditsbutton = (Button) primaryStage.getScene().lookup("#creditsbutton");

        //PRIVATE REFERENCES
        newgamebutton = (Button) primaryStage.getScene().lookup("#newgamebutton");

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

        newgamebutton.setOnMouseClicked(e-> {
            try {
                newgame();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        loadgamebutton.setOnMouseClicked(e-> {
            try {
                loadgame();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        seerankingsbutton.setOnMouseClicked(e-> {
            try {
                seerankings();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        creditsbutton.setOnMouseClicked(e-> {
            try {
                credits();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        //INITIALIZE GUI
        username.setText(pc.getUsern());
    }

    private void logout() throws IOException {
        pc.setPrimaryStage(primaryStage);
        Index i = new Index(pc);;
    }

    private void returnmenu() throws IOException {
        Menu m = new Menu(pc);
    }

    private void newgame() throws IOException {
        pc.newGame(pc.getUsern());
        GameCreator gc = new GameCreator(pc);
         //GenerarHidato gh = new GenerarHidato(pc);
    }

    private void loadgame() throws IOException {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc.setFileFilter(new FileNameExtensionFilter("Fitxers de Partida (.partida)","partida"));
        int result = fc.showOpenDialog(this);
        File selectedFile = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fc.getSelectedFile();
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            Hidato hida = pc.loadGame(selectedFile.getAbsolutePath());
            if (hida == null) {}//System.out.println("ERROR LOAD");
            else {
                pc.setClassHidato(hida);
                Vector<Vector<String>> hid = pc.getHidato();
                for (int x = 0; x < hid.size(); ++x) {
                    for (int y = 0; y < hid.get(x).size(); ++y) {
                        System.out.print(hid.get(x).get(y) + " ");
                    }
                    System.out.print("\n");
                }
                PrinterHidatoPlayer php = new PrinterHidatoPlayer(pc);
            }
        }
    }

    private void seerankings() throws IOException {
        RankingMenu rm = new RankingMenu(pc);
    }

    private void credits() throws IOException {
        Credits c = new Credits(pc);
    }
}