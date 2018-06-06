package presentationcontrol;

import com.jfoenix.controls.JFXSpinner;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Working implements Runnable {
    private Stage workingStage;
    PresentationCtrl pc;

    @Override
    public void run() {
        //System.out.println("THREAD GESTOR");

        //System.out.println("A GENERATE HIDATO");
        Platform.runLater(() -> {
            workingStage.show();
        });
        pc.generateHidato();
        //System.out.println("FINISHED");
        Platform.runLater(() -> {
            workingStage.close();
            if(pc.getHidato() != null) {
                try {
                    PrinterHidatoGenerator phg = new PrinterHidatoGenerator(pc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR, "No ha sigut possible generar un \nhidato amb els parametres seleccionats ", ButtonType.OK);
                alert2.showAndWait();
                if (alert2.getResult() == ButtonType.OK) {
                    try {
                        GenerarHidato gh = new GenerarHidato(pc);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //prova
                }
            }
        });
    }

    public Working(PresentationCtrl pc, Stage stage2) throws IOException {
        this.pc = pc;
        this.workingStage = stage2;
        //this.semwork = semwork;
        //this.semfinished = semfinished;
        //workingStage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("/forms/Working.fxml"));
        /*workingStage.setScene(new Scene(root,700,100));
        workingStage.setTitle("");
        workingStage.setResizable(false);*/
    }
}
