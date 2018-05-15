package presentationcontrol;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Vector;

public class PrinterHidatoGenerator extends PrinterHidato {
    //PRIVATE OBJECTS
    private Button savebutton;
    private Button playhidatobutton;

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
        savebutton = (Button) primaryStage.getScene().lookup("#savebutton");
        playhidatobutton = (Button) primaryStage.getScene().lookup("#playhidatobutton");

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
        //INITIALIZE GUI
        username.setText(pc.getUsern());
        if(celltype.equals('H')) createboardhexagon();
        else if(celltype.equals('Q')) createboardsquare();
        else createboardtriangle();
    }

    private void playhidato() throws IOException {
        primaryStage.close();
        primaryStage = new Stage();
        pc.setPrimaryStage(primaryStage);
        PrinterHidatoPlayer php = new PrinterHidatoPlayer(pc);
    }
}
