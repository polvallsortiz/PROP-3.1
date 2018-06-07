package presentationcontrol;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXHamburger;
import domaincontrol.Hidato;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.util.Vector;

public class Menu extends Component {
    private Label username;
    private Button logoutbutton;
    private Button menubutton;

    //PRIVATE OBJECTS
    private Button newgamebutton;
    private Button loadgamebutton;
    private Button seerankingsbutton;
    private Button creditsbutton;
    private Button helpbutton;

    private Stage primaryStage;
    private PresentationCtrl pc;


    public Menu(PresentationCtrl pc) throws IOException {
        this.pc = pc;
        primaryStage = pc.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/forms/Menu.fxml"));
        primaryStage.setTitle("Menú Principal - Hidato Game");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();


        //REFERENCES
        username = (Label) primaryStage.getScene().lookup("#usernamelabel");
        logoutbutton = (Button) primaryStage.getScene().lookup("#logoutbutton");
        menubutton = (Button) primaryStage.getScene().lookup("#menubutton");
        loadgamebutton = (Button) primaryStage.getScene().lookup("#loadgamebutton");
        seerankingsbutton = (Button) primaryStage.getScene().lookup("#seerankingsbutton");
        creditsbutton = (Button) primaryStage.getScene().lookup("#creditsbutton");
        helpbutton = (Button) primaryStage.getScene().lookup("#helpbutton");

        //PRIVATE REFERENCES
        newgamebutton = (Button) primaryStage.getScene().lookup("#newgamebutton");

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

        newgamebutton.setOnMouseClicked(e-> {
            try {
                newgame();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        loadgamebutton.setOnMouseClicked(e-> {
            try {
                loadgame();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        seerankingsbutton.setOnMouseClicked(e-> {
            try {
                seerankings();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        creditsbutton.setOnMouseClicked(e-> {
            try {
                credits();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        helpbutton.setOnMouseClicked(e->help());

        //INITIALIZE GUI
        username.setText(pc.getUsern());
    }

    private void logout() throws IOException {
        pc.setPrimaryStage(primaryStage);
        Index i = new Index(pc);;
    }

    private void returnmenu() throws IOException {
        Menu m = new Menu(pc);
    }

    private void newgame() throws IOException {
        pc.newGame(pc.getUsern());
        GameCreator gc = new GameCreator(pc);
         //GenerarHidato gh = new GenerarHidato(pc);
    }

    private void loadgame() throws IOException {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        fc.setFileFilter(new FileNameExtensionFilter("Fitxers de Partida (.partida)","partida"));
        int result = fc.showOpenDialog(this);
        File selectedFile = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fc.getSelectedFile();
            //System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            Hidato hida = pc.loadGame(selectedFile.getAbsolutePath());
            if (hida == null) {}//System.out.println("ERROR LOAD");
            else {
                pc.setClassHidato(hida);
                Vector<Vector<String>> hid = pc.getHidato();
                for (int x = 0; x < hid.size(); ++x) {
                    for (int y = 0; y < hid.get(x).size(); ++y) {
                        System.out.print(hid.get(x).get(y) + " ");
                    }
                    System.out.print("\n");
                }
                PrinterHidatoPlayer php = new PrinterHidatoPlayer(pc);
            }
        }
    }

    private void seerankings() throws IOException {
        RankingMenu rm = new RankingMenu(pc);
    }

    private void credits() throws IOException {
        Credits c = new Credits(pc);
    }

    private void help() {
        JFXDialogLayout content= new JFXDialogLayout();
        content.setPrefSize(800,300);
        content.setHeading(new Text("Ajuda"));
        content.setBody(new Text("1. NOVA PARTIDA: Permet definir una nova partida, ja sigui generant-la, definint-la, o carregant una partida ja existent.\n" +
                "2. CARREGAR PARTIDA: Permet carregar una partida començada anteriorment al mateix sistema a partir d’un fitxer guardat al nostre ordinador.\n" +
                "3. VEURE RÀNKINGS: Permet visualitzar les millors puntuacions dels usuaris que han fet partides al sistema (en local).\n" +
                "La barra lateral està present en gairebé totes les finestres:\n" +
                "4. TORNAR AL MENÚ PRINCIPAL: Permet tornar a la finestra actual.\n" +
                "5. TANCAR SESSIÓ: Permet tancar la sessió.\n"));
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
        Scene scene = new Scene(stackpane, 800, 230);
        stage.setScene(scene);
        dialog.show();
        stage.show();
    }
}