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

import java.awt.*;
import java.io.File;

import javax.swing.*;
import java.io.IOException;

public class Menu extends Component {
    private Label username;
    private Button logoutbutton;
    private Button menubutton;

    //PRIVATE OBJECTS
    private Button newgamebutton;
    private Button loadgamebutton;
    private Button seerankingsbutton;

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

        //INITIALIZE GUI
        username.setText(pc.getUsern());
    }

    private void logout() throws IOException {
        pc.setPrimaryStage(primaryStage);
        Index i = new Index(pc);
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
        String osName = System.getProperty("os.name");
        String homeDir = System.getProperty("user.home");
        File selectedPath = null;
        if (osName.equals("Mac OS X")) {
            System.setProperty("apple.awt.fileDialogForDirectories", "true");
            FileDialog fd = new FileDialog(new Frame(), "Choose a file", FileDialog.LOAD);
            fd.setDirectory(homeDir);
            fd.setVisible(true);
            String filename = fd.getFile();
            selectedPath = new File(filename);
            if (filename == null) {
                System.out.println("You cancelled the choice");
            } else {
                System.out.println("You chose " + filename);
            }
            System.setProperty("apple.awt.fileDialogForDirectories", "true");
        } else {
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.setCurrentDirectory(new File(homeDir));
            fc.setAcceptAllFileFilterUsed(false);
            fc.showOpenDialog(null);
            selectedPath = fc.getSelectedFile();
        }
        pc.setHidato(pc.loadGame(selectedPath.getAbsolutePath()));
        PrinterHidatoPlayer php = new PrinterHidatoPlayer(pc);
    }

    private void seerankings() throws IOException {
        RankingMenu rm = new RankingMenu(pc);
    }
}