package presentationcontrol;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class ProposarHidato {
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
    private Button proposebutton;

    Stage primaryStage;
    PresentationCtrl pc;

    public ProposarHidato(PresentationCtrl pc) throws IOException {
        this.pc = pc;
        pc.reset_pc();
        primaryStage = pc.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/forms/ProposarHidato.fxml"));
        primaryStage.setTitle("Proposar Hidato - Hidato Game");
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
        proposebutton = (Button) primaryStage.getScene().lookup("#proposebutton");

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
        proposebutton.setOnMouseClicked(e-> {
            try {
                proposehidato();
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
    }

    private void logout() throws IOException {
        primaryStage.close();
        primaryStage = new Stage();
        pc.setPrimaryStage(primaryStage);
        Index i = new Index(pc);
    }

    private void returnmenu() throws IOException {
        primaryStage.close();
        primaryStage = new Stage();
        pc.setPrimaryStage(primaryStage);
        Menu m = new Menu(pc);
    }

    private void updaterows() {
        double actual = rowsslider.getValue();
        pc.setRows((int) actual);
        rowslabel.setText(String.valueOf(pc.getRows()));
        columnslider.setDisable(false);
    }

    private void updatecolumns() {
        double actual = columnslider.getValue();
        pc.setColumns((int) actual);
        columnslabel.setText(String.valueOf(pc.getColumns()));
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

    private String getadjacency() {
        String selected = adjacencycombobox.getSelectionModel().getSelectedItem().toString();
        if(selected.equals("Costat")) {
            return "C";
        }
        else {
            return "CA";
        }
    }

    private void proposehidato() throws IOException {
        pc.setAdjacencytype(getadjacency());
        pc.matrix_generator_GUI();
        primaryStage.close();
        primaryStage = new Stage();
        pc.setPrimaryStage(primaryStage);
        PrinterHidatoProposar pp = new PrinterHidatoProposar(pc);
    }
}
