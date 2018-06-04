package presentationcontrol;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

public class PrinterHidatoProposar extends PrinterHidato {
    private Label username;
    private Button logoutbutton;
    private Button menubutton;
    private Parent root2;
    //PRIVATE OBJECTS
    private Button proposebutton;
    private Button resethidatobutton;


    public PrinterHidatoProposar(PresentationCtrl pc) throws IOException {
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
        Parent root = FXMLLoader.load(getClass().getResource("/forms/PrinterHidatoProposar.fxml"));
        root2 = root;
        primaryStage.setTitle("Generar Hidato - Hidato Game");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();


        //REFERENCES
        username = (Label) primaryStage.getScene().lookup("#usernamelabel");
        logoutbutton = (Button) primaryStage.getScene().lookup("#logoutbutton");
        menubutton = (Button) primaryStage.getScene().lookup("#menubutton");
        resethidatobutton = (Button) primaryStage.getScene().lookup("#resethidatobutton");

        //PRIVATE REFERENCES
        boardpane = (Pane) primaryStage.getScene().lookup("#boardpane");
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
        boardpane.setOnMouseClicked(e-> {
            try {
                boardclicked(e.getX(),e.getY());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        proposebutton.setOnMouseClicked(e-> {
            try {
                proposehidato();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
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
        if(pc.isFirst()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Recorda posar sempre el número 1", ButtonType.YES);
            alert.setHeaderText("Informació Proposar Hidato");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                alert.close();
                pc.setFirst(false);
            }
        }
    }

    private void proposehidato() throws IOException {
        Parent root0 = FXMLLoader.load(getClass().getResource("/forms/Working.fxml"));
        Stage stage2 = new Stage();
        stage2.setTitle("Treballant");
        stage2.setScene(new Scene(root0,700,100));
        pc.setWorkingStage(stage2);
        Thread t = new Thread(new WorkingPropose(pc,stage2));
        t.start();

    }

    private void resethidato() throws IOException {
        pc.matrix_generator_GUI();
        PrinterHidatoProposar pp = new PrinterHidatoProposar(pc);
    }
}
