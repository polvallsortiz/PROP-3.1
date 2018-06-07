package presentationcontrol;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private Button helpbutton;


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
        helpbutton = (Button) primaryStage.getScene().lookup("#helpbutton");

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
        helpbutton.setOnMouseClicked(e->help());


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

    private void help() {
        JFXDialogLayout content= new JFXDialogLayout();
        content.setPrefSize(1500,300);
        content.setHeading(new Text("Ajuda"));
        content.setBody(new Text("Mogui els sliders per definir les característiques desitjades pel seu tauler.Tingui en compte que el nombre real és el que apareix a la dreta quan mou la barra(no ha de clicar en el punt que vol de la barra, ha d’arrossegar la boleta fins a ell).\n" +
                        "Un cop té la configuració desitjada, premi “Continuar”.\n" +
                        "S’obre el taulell buit per a què vostè insereixi els nombres, forats i cel·les inaccessibles que desitgi. Recordi que és condició indispensable col·locar l’1.\n" +
                        "\n" +
                        "Si prem una cel·la qualsevol, s’obre el menú següent on pot introduir el nombre o la tipologia de la cel·la en qüestió.\n" +
                        "\n" +
                        "Un cop tingui la configuració llesta, premi “Proposar”.\n"));
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
