package presentationcontrol;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Vector;

public class GenerarHidato {
    private Label username;
    private Button logoutbutton;
    private Button menubutton;

    //PRIVATE OBJECTS
    private Slider rowsslider;
    private Label rowslabel;
    private Slider columnslider;
    private Label columnslabel;
    private Rectangle square;
    private Polygon triangle;
    private Polygon hexagon;
    private JFXComboBox adjacencycombobox;
    private Slider holeslider;
    private Label holeslabel;
    private Slider predefinedslider;
    private Label predefinedlabel;
    private Button generatebutton;

    Stage primaryStage;
    PresentationCtrl pc;

    public GenerarHidato(PresentationCtrl pc) throws IOException {
        this.pc = pc;
        pc.reset_pc();
        primaryStage = pc.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/forms/GenerarHidato.fxml"));
        primaryStage.setTitle("Generar Hidato - Hidato Game");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();


        //REFERENCES
        username = (Label) primaryStage.getScene().lookup("#usernamelabel");
        logoutbutton = (Button) primaryStage.getScene().lookup("#logoutbutton");
        menubutton = (Button) primaryStage.getScene().lookup("#menubutton");

        //PRIVATE REFERENCES
        rowsslider = (Slider) primaryStage.getScene().lookup("#rowsslider");
        rowslabel = (Label) primaryStage.getScene().lookup("#rowslabel");
        columnslider = (Slider) primaryStage.getScene().lookup("#columnslider");
        columnslabel = (Label) primaryStage.getScene().lookup("#columnslabel");
        square = (Rectangle) primaryStage.getScene().lookup("#square");
        triangle = (Polygon) primaryStage.getScene().lookup("#triangle");
        hexagon = (Polygon) primaryStage.getScene().lookup("#hexagon");
        adjacencycombobox = (JFXComboBox) primaryStage.getScene().lookup("#adjacencycombobox");
        holeslider = (Slider) primaryStage.getScene().lookup("#holeslider");
        holeslabel = (Label) primaryStage.getScene().lookup("#holeslabel");
        predefinedslider = (Slider) primaryStage.getScene().lookup("#predefinedslider");
        predefinedlabel = (Label) primaryStage.getScene().lookup("#predefinedlabel");
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
        rowsslider.setOnMouseDragged(e->updaterows());
        columnslider.setOnMouseDragged(e->updatecolumns());
        square.setOnMouseClicked(e->squareselected());
        square.setOnMouseEntered(e->squareenter());
        square.setOnMouseExited(e->squareexit());
        triangle.setOnMouseClicked(e->triangleselected());
        triangle.setOnMouseEntered(e->triangleenter());
        triangle.setOnMouseExited(e->triangleexit());
        hexagon.setOnMouseClicked(e->hexagonselected());
        hexagon.setOnMouseEntered(e->hexagonenter());
        hexagon.setOnMouseExited(e->hexagonexit());
        holeslider.setOnMouseDragged(e->updateholes());
        predefinedslider.setOnMouseDragged(e->updatepredefined());
        generatebutton.setOnMouseClicked(e-> {
            try {
                generatehidato();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        //INITIALIZE GUI
        username.setText(pc.getUsern());
        adjacencycombobox.setItems(FXCollections.observableArrayList(
           new String("Costat"),
           new String("Costat i Angle")
        ));
        columnslider.setDisable(true);
        holeslider.setDisable(true);
        predefinedslider.setDisable(true);

    }

    private void logout() throws IOException {
        Index i = new Index(pc);
    }

    private void returnmenu() throws IOException {
        Menu m = new Menu(pc);
    }

    private void updaterows() {
        double actual = rowsslider.getValue();
        pc.setRows((int) actual);
        rowslabel.setText(String.valueOf(pc.getRows()));
        updateboundholes();
        updateboundpredefined();
        columnslider.setDisable(false);
    }

    private void updatecolumns() {
        double actual = columnslider.getValue();
        pc.setColumns((int) actual);
        columnslabel.setText(String.valueOf(pc.getColumns()));
        updateboundholes();
        updateboundpredefined();
        holeslider.setDisable(false);
    }

    private void squareselected() {
        pc.setCelltype('Q');
        square.setFill(javafx.scene.paint.Color.valueOf("#0f9d58"));
        triangle.setFill(javafx.scene.paint.Color.valueOf("#757de8"));
        hexagon.setFill(javafx.scene.paint.Color.valueOf("#757de8"));
        adjacencycombobox.setItems(FXCollections.observableArrayList(
                new String("Costat"),
                new String("Costat i Angle")
        ));

    }

    private void squareenter() {
        Character celltype = pc.getCelltype();
        if(celltype == null || celltype != 'Q') square.setFill(javafx.scene.paint.Color.valueOf("#0f9d58"));
    }

    private void squareexit() {
        Character celltype = pc.getCelltype();
        if(celltype == null || celltype != 'Q') square.setFill(javafx.scene.paint.Color.valueOf("#757de8"));
    }

    private void triangleselected() {
        pc.setCelltype('T');
        triangle.setFill(javafx.scene.paint.Color.valueOf("#0f9d58"));
        square.setFill(javafx.scene.paint.Color.valueOf("#757de8"));
        hexagon.setFill(javafx.scene.paint.Color.valueOf("#757de8"));
        adjacencycombobox.setItems(FXCollections.observableArrayList(
                new String("Costat")
        ));
    }

    private void triangleenter() {
        Character celltype = pc.getCelltype();
        if(celltype == null || celltype != 'T') triangle.setFill(javafx.scene.paint.Color.valueOf("#0f9d58"));
    }

    private void triangleexit() {
        Character celltype = pc.getCelltype();
        if(celltype == null || celltype != 'T') triangle.setFill(javafx.scene.paint.Color.valueOf("#757de8"));
    }

    private void hexagonselected() {
        pc.setCelltype('H');
        hexagon.setFill(javafx.scene.paint.Color.valueOf("#0f9d58"));
        triangle.setFill(javafx.scene.paint.Color.valueOf("#757de8"));
        square.setFill(javafx.scene.paint.Color.valueOf("#757de8"));
        adjacencycombobox.setItems(FXCollections.observableArrayList(
                new String("Costat")
        ));
    }

    private void hexagonenter() {
        Character celltype = pc.getCelltype();
        if(celltype == null || celltype != 'H') hexagon.setFill(javafx.scene.paint.Color.valueOf("#0f9d58"));
    }

    private void hexagonexit() {
        Character celltype = pc.getCelltype();
        if(celltype == null || celltype != 'H') hexagon.setFill(javafx.scene.paint.Color.valueOf("#757de8"));
    }

    private void updateholes() {
        double actual = holeslider.getValue();
        pc.setHoles((int) actual);
        holeslabel.setText(String.valueOf(pc.getHoles()));
        updateboundpredefined();
        predefinedslider.setDisable(false);
    }

    private void updatepredefined() {
        double actual = predefinedslider.getValue();
        pc.setPredefined((int) actual);
        predefinedlabel.setText(String.valueOf(pc.getPredefined()));
    }

    private void updateboundholes() {
        /*if(pc.getRows() != null && pc.getColumns() != null) {
            int total = pc.getRows() * pc.getColumns();
            holeslider.setMajorTickUnit(total-1);

            //TODO:The bounds are not set correctly for the holes and predefined sliders

            System.out.println(holeslider.getMax());
            holeslider.setMax(total-1);
            System.out.println(holeslider.getMax());
        }*/
    }

    private void updateboundpredefined() {/*
        if(pc.getRows() != null && pc.getColumns() != null && pc.getHoles() != null) {
            int total = pc.getRows() * pc.getColumns() + pc.getHoles();
            holeslider.setMax(total-1);
        }*/
    }

    private String getadjacency() {
        String selected = adjacencycombobox.getSelectionModel().getSelectedItem().toString();
        if(selected.equals("Costat")) {
            return "C";
        }
        else {
            return "CA";
        }
    }

    private void generatehidato() throws IOException {
        pc.setAdjacencytype(getadjacency());
        pc.matrix_generator_GUI();
        PrinterHidatoGeneratorHoles ph = new PrinterHidatoGeneratorHoles(pc);
    }
}