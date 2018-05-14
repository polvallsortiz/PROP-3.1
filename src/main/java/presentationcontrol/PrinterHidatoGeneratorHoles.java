package presentationcontrol;

import domaincontrol.DomainCtrl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Vector;

public class PrinterHidatoGeneratorHoles extends PrinterHidato{
    private Label username;
    private Button logoutbutton;
    private Button menubutton;
    private Parent root2;
    //PRIVATE OBJECTS
    private Button generatebutton;


    public PrinterHidatoGeneratorHoles(PresentationCtrl pc) throws IOException {
        this.pc = pc;
        this.hidato = pc.getHidato();
        this.celltype = pc.getCelltype();
        this.adjacency = pc.getAdjacencytype();
        this.holes = pc.getHoles();
        this.predefined = pc.getPredefined();
        rows = hidato.size();
        columns = hidato.get(0).size();
        points = new Vector<>();
        primaryStage = pc.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/forms/PrinterHidatoGeneratorHoles.fxml"));
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
        generatebutton = (Button) primaryStage.getScene().lookup("#generatebutton");

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
        boardpane.setOnMouseClicked(e-> {
            try {
                boardclicked(e.getX(),e.getY());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        generatebutton.setOnMouseClicked(e-> {
            try {
                generatehidato();
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

    private void boardclicked(Double x, Double y) throws IOException {
        Point p = new Point(x,y);
        if(celltype.equals('H')|| celltype.equals('Q')) {
            for(int i = 0; i < points.size(); ++i) {    // FOR EACH SQUARE
                Point sq0,sq1,sq3;
                sq0 = points.get(i).get(0);
                sq1 = points.get(i).get(1);
                sq3 = points.get(i).get(3);
                if(p.pointInSquare(sq0,sq1,sq3)) {
                    System.out.println("CLICKAT A " + i);
                    Vector<String> aux = hidato.get(i/columns);
                    aux.set(i%columns,"#");
                    primaryStage.close();
                    primaryStage = new Stage();
                    pc.setPrimaryStage(primaryStage);
                    PrinterHidatoGeneratorHoles ph = new PrinterHidatoGeneratorHoles(pc);
                }
            }
        }
        else {
            for(int i = 0; i < points.size(); ++i) {    // FOR EACH TRIANGLE
                Point t0,t1,t2;
                t0 = points.get(i).get(0);
                t1 = points.get(i).get(1);
                t2 = points.get(i).get(2);
                if(p.pointInTriangle(t0,t1,t2)) {
                    System.out.println("CLICKAT A " + i);
                    Vector<String> aux = hidato.get(i/columns);
                    aux.set(i%columns,"#");
                    primaryStage.close();
                    primaryStage = new Stage();
                    pc.setPrimaryStage(primaryStage);
                    PrinterHidatoGeneratorHoles ph = new PrinterHidatoGeneratorHoles(pc);
                }
            }
        }

    }



    private void generatehidato() throws IOException {
        pc.generateHidato();
        hidato = pc.getHidato();
        if(hidato != null) {
            primaryStage.close();
            primaryStage = new Stage();
            pc.setPrimaryStage(primaryStage);
            PrinterHidatoGenerator phg = new PrinterHidatoGenerator(pc);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No ha sigut possible generar un \nhidato amb els parametres seleccionats ", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                primaryStage.close();
                primaryStage = new Stage();
                pc.setPrimaryStage(primaryStage);
                GenerarHidato gh = new GenerarHidato(pc);
            }
        }
    }
}
