package presentationcontrol;

import domaincontrol.Hidato;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class GameCreator extends Component {
    private Label username;
    private Button logoutbutton;
    private Button menubutton;

    //PRIVATE OBJECTS
    private Button generatehidatobutton;
    private Button proposehidatobutton;
    private Button loadhidatobutton;
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
        loadhidatobutton = (Button) primaryStage.getScene().lookup("#loadhidatobutton");

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
        loadhidatobutton.setOnMouseClicked(e-> {
            try {
                loadhidato();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        //INITIALIZE GUI
        username.setText(pc.getUsern());
    }

    private void logout() throws IOException {
        Index i = new Index(pc);;
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

    private void loadhidato() throws IOException {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc.setFileFilter(new FileNameExtensionFilter("Fitxers d'Hidato (.hidato)","hidato"));
        int result = fc.showOpenDialog(this);
        File selectedFile = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fc.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            Hidato hida = pc.loadHidato(selectedFile.getAbsolutePath());
            if (hida == null) System.out.println("ERROR LOAD");
            else {
                pc.setClassHidato(hida);
                Vector<Vector<String>> hid = pc.getHidato();
                for (int x = 0; x < hid.size(); ++x) {
                    for (int y = 0; y < hid.get(x).size(); ++y) {
                        System.out.print(hid.get(x).get(y) + " ");
                    }
                    System.out.print("\n");
                }
                PrinterHidatoGenerator phg = new PrinterHidatoGenerator(pc);
            }
        }
    }
}
