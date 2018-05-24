package presentationcontrol;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class PrinterHidatoPlayer extends PrinterHidato {

    private Button savegamebutton;
    private Button resethidatobutton;

    public PrinterHidatoPlayer(PresentationCtrl pc) throws IOException {
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
        Parent root = FXMLLoader.load(getClass().getResource("/forms/PrinterHidatoPlayer.fxml"));
        root2 = root;
        primaryStage.setTitle("Jugar Hidato - Hidato Game");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();


        //REFERENCES
        username = (Label) primaryStage.getScene().lookup("#usernamelabel");
        logoutbutton = (Button) primaryStage.getScene().lookup("#logoutbutton");
        menubutton = (Button) primaryStage.getScene().lookup("#menubutton");
        savegamebutton = (Button) primaryStage.getScene().lookup("#savegamebutton");
        resethidatobutton = (Button) primaryStage.getScene().lookup("#resethidatobutton");

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
        boardpane.setOnMouseClicked(e-> {
            try {
                boardclicked(e.getX(),e.getY());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        boardpane.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                MouseButton button = mouseEvent.getButton();
                if(button==MouseButton.PRIMARY){
                    //CLICK LEFT
                    try {
                        boardclicked_left(mouseEvent.getX(),mouseEvent.getY());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(button==MouseButton.SECONDARY){    //CLICK RIGHT
                    try {
                        boardclicked(mouseEvent.getX(),mouseEvent.getY());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        savegamebutton.setOnMouseClicked(e->savegame());
        resethidatobutton.setOnMouseClicked(e-> {
            try {
                resethidato();
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

    private void boardclicked_left(double x, double y) throws IOException {
        Point p = new Point(x,y);
        if(celltype.equals('H')|| celltype.equals('Q')) {
            for(int i = 0; i < points.size(); ++i) {    // FOR EACH SQUARE
                Point sq0,sq1,sq3;
                sq0 = points.get(i).get(0);
                sq1 = points.get(i).get(1);
                sq3 = points.get(i).get(3);
                if(p.pointInSquare(sq0,sq1,sq3)) {
                    System.out.println("CLICKAT A " + i);
                    pc.setFirst(false);
                    EditHidatoField ehf = new EditHidatoField(pc,i,2);
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
                    pc.setFirst(false);
                    EditHidatoField ehf = new EditHidatoField(pc,i,2);
                }
            }
        }
    }


    @Override
    protected void boardclicked(Double x, Double y) throws IOException {
        Point p = new Point(x,y);
        if(celltype.equals('H')|| celltype.equals('Q')) {
            for(int i = 0; i < points.size(); ++i) {    // FOR EACH SQUARE
                Point sq0,sq1,sq3;
                sq0 = points.get(i).get(0);
                sq1 = points.get(i).get(1);
                sq3 = points.get(i).get(3);
                if(p.pointInSquare(sq0,sq1,sq3)) {
                    System.out.println("CLICKAT A " + i);
                    pc.setFirst(false);
                    EditHidatoField ehf = new EditHidatoField(pc,i,1);
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
                    pc.setFirst(false);
                    EditHidatoField ehf = new EditHidatoField(pc,i,1);
                }
            }
        }

    }

    private void savegame() {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        Component c = new Component() {
        };
        int result = fc.showSaveDialog(c);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            int res = pc.saveGame(selectedFile.getAbsolutePath());
            if(res == 1) System.out.println("SAVE OK");
            else System.out.println("SAVE FAILED");
        }
    }

    private void resethidato() throws IOException {
        pc.setHidato(pc.rebootGame());
        PrinterHidatoPlayer php = new PrinterHidatoPlayer(pc);
    }
}
