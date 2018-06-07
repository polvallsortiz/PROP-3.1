package presentationcontrol;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class PrinterHidatoGeneratorHoles extends PrinterHidato{
    private Label username;
    private Button logoutbutton;
    private Button menubutton;
    private Parent root2;
    private Button helpbutton;

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
        helpbutton = (Button) primaryStage.getScene().lookup("#helpbutton");

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
                boardclickedholes(e.getX(),e.getY());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        generatebutton.setOnMouseClicked(e-> {
            try {
                generateHidato();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        helpbutton.setOnMouseClicked(e->help());

        //INITIALIZE GUI
        username.setText(pc.getUsern());
        if(celltype.equals('H')) createboardhexagon();
        else if(celltype.equals('Q')) createboardsquare();
        else createboardtriangle();
    }

    private void boardclickedholes(Double x, Double y) throws IOException {
        Point p = new Point(x,y);
        if(celltype.equals('H')|| celltype.equals('Q')) {
            for(int i = 0; i < points.size(); ++i) {    // FOR EACH SQUARE
                Point sq0,sq1,sq3;
                sq0 = points.get(i).get(0);
                sq1 = points.get(i).get(1);
                sq3 = points.get(i).get(3);
                if(p.pointInSquare(sq0,sq1,sq3)) {
                    //System.out.println("CLICKAT A " + i);
                    Vector<String> aux = hidato.get(i/columns);
                    if(aux.get(i%columns).equals("?")) aux.set(i%columns,"#");
                    else if(aux.get(i%columns).equals("#")) aux.set(i%columns,"?");
                    hidato.set(i/columns,aux);
                    pc.setHidato(hidato);
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
                    //System.out.println("CLICKAT A " + i);
                    Vector<String> aux = hidato.get(i/columns);
                    aux.set(i%columns,"#");
                    hidato.set(i/columns,aux);
                    pc.setHidato(hidato);
                    PrinterHidatoGeneratorHoles ph = new PrinterHidatoGeneratorHoles(pc);
                }
            }
        }

    }



    private void generateHidato() throws IOException, InterruptedException {
        Scene back = pc.getPrimaryStage().getScene();
        Parent root0 = FXMLLoader.load(getClass().getResource("/forms/Working.fxml"));
        Stage stage2 = new Stage();
        stage2.setTitle("Treballant");
        stage2.setScene(new Scene(root0,700,100));
        Thread t = new Thread(new Working(pc,stage2));
        t.start();

    }

    private void help() {
        JFXDialogLayout content= new JFXDialogLayout();
        content.setPrefSize(1500,300);
        content.setHeading(new Text("Ajuda"));
        content.setBody(new Text("Marqui les caselles que vulgui que siguin forats. \n" +
                "Un cop té la configuració desitjada, premi “Generar”.\n"));
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
