package presentationcontrol;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Index extends Application {

    private ImageView avatar;
    private TextField username;
    private Button enter;
    private String usern;
    private Stage primaryStage;
    private PresentationCtrl pc;

    public Index() {
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/forms/Index.fxml"));
        primaryStage.setTitle("Hidato Game");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();

        //References
        avatar = (ImageView) primaryStage.getScene().lookup("#avatar");
        username = (TextField) primaryStage.getScene().lookup("#usernamefield");
        enter = (Button) primaryStage.getScene().lookup("#enterbutton");

        //Actions
        setAvatar();
        enter.setOnMouseClicked(e-> {
            try {
                enter_system();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public Index(PresentationCtrl pc) throws IOException {
        this.pc = pc;
        primaryStage = pc.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/forms/Index.fxml"));
        primaryStage.setTitle("Hidato Game");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setResizable(false);
        primaryStage.show();

        //References
        avatar = (ImageView) primaryStage.getScene().lookup("#avatar");
        username = (TextField) primaryStage.getScene().lookup("#usernamefield");
        enter = (Button) primaryStage.getScene().lookup("#enterbutton");

        //Actions
        setAvatar();
        enter.setOnMouseClicked(e-> {
            try {
                enter_system();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    //INITIALIZERS OF SCENE
    private void setAvatar() {
        Image image = new Image("/images/avatar.png");
        avatar.setImage(image);
    }

    //FUNCTIONS
    private void enter_system() throws Exception {
        usern = username.getText();
        if(usern.length() == 0) {
            username.setPromptText("Error, l'usuari no pot ser buit");
        }
        else {
            PresentationCtrl pc = new PresentationCtrl();
            pc.setUsern(usern);
            pc.setPrimaryStage(primaryStage);
            Menu menu = new Menu(pc);
        }
    }

    @Override
    public void stop(){
        System.out.println("Stage is closing");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Estàs tancant el programa...");
        alert.setHeaderText("Vols guardar els progresos?");
        alert.setContentText("Si no, perdràs tot l'acumulat");

        ButtonType buttonTypeOne = new ButtonType("Guardar");
        ButtonType buttonTypeCancel = new ButtonType("No guardar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            //SAVES
            System.out.println("SAVE ALL DATA");
        }
        // Save file
    }


}
