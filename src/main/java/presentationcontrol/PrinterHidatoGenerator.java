package presentationcontrol;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class PrinterHidatoGenerator extends PrinterHidato {
    //PRIVATE OBJECTS
    private Button savehidatobutton;
    private Button playhidatobutton;
    private Button solvehidatobutton;
    private Button helpbutton;

    PrinterHidatoGenerator(PresentationCtrl pc) throws IOException {
        this.pc = pc;
        this.primaryStage = pc.getPrimaryStage();
        this.hidato = pc.getHidato();
        this.celltype = pc.getCelltype();
        this.adjacency = pc.getAdjacencytype();
        this.holes = pc.getHoles();
        this.predefined = pc.getPredefined();
        rows = hidato.size();
        columns = hidato.get(0).size();
        points = new Vector<>();
        primaryStage = pc.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/forms/PrinterHidatoGenerator.fxml"));
        root2 = root;
        primaryStage.setTitle("Generar Hidato - Hidato Game");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();


        //REFERENCES
        username = (Label) primaryStage.getScene().lookup("#usernamelabel");
        logoutbutton = (Button) primaryStage.getScene().lookup("#logoutbutton");
        menubutton = (Button) primaryStage.getScene().lookup("#menubutton");
        savehidatobutton = (Button) primaryStage.getScene().lookup("#savehidatobutton");
        playhidatobutton = (Button) primaryStage.getScene().lookup("#playhidatobutton");
        solvehidatobutton = (Button) primaryStage.getScene().lookup("#solvehidatobutton");
        helpbutton = (Button) primaryStage.getScene().lookup("#helpbutton");

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
        playhidatobutton.setOnMouseClicked(e-> {
            try {
                playhidato();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        solvehidatobutton.setOnMouseClicked(e-> {
            try {
                solvehidato();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        savehidatobutton.setOnMouseClicked(e->savehidato());
        helpbutton.setOnMouseClicked(e->help());


        //INITIALIZE GUI
        username.setText(pc.getUsern());
        if(celltype.equals('H')) createboardhexagon();
        else if(celltype.equals('Q')) createboardsquare();
        else createboardtriangle();
    }

    private void playhidato() throws IOException {
        pc.playHidato();
        PrinterHidatoPlayer php = new PrinterHidatoPlayer(pc);
    }

    private void solvehidato() throws IOException {
        pc.solveHidato();
        PrinterHidatoResolution phr = new PrinterHidatoResolution(pc);
    }

    private void savehidato() {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        Component c = new Component() {
        };
        int result = fc.showSaveDialog(c);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            int res = pc.saveHidato(selectedFile.getAbsolutePath());
            /*if(res == 1) System.out.println("SAVE OK");
            else System.out.println("SAVE FAILED");*/
        }
    }

    private void help() {
        JFXDialogLayout content= new JFXDialogLayout();
        content.setPrefSize(1500,300);
        content.setHeading(new Text("Ajuda"));
        content.setBody(new Text("1. JUGAR HIDATO: Permet jugar el tauler que es mostra en pantalla-\n" +
                "2. RESOLDRE HIDATO: Demana al sistema que resolgui el tauler i el mostri per pantalla.\n" +
                "3. GUARDAR FITXER: Permet guardar la configuració del tauler per a poder ser carregada posteriorment.\n"));
        StackPane stackpane = new StackPane();
        JFXDialog dialog =new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button=new JFXButton("D'acord");
        Stage stage = new Stage();
        button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                dialog.close();
                stage.close();
            }
        });
        content.setActions(button);
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(stackpane, 1500, 300);
        stage.setScene(scene);
        dialog.show();
        stage.show();
    }
}

