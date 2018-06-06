package presentationcontrol;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class WorkingPropose implements Runnable {
    private Stage workingStage;
    int i;
    String value;
    PresentationCtrl pc;

    @Override
    public void run() {
        //System.out.println("THREAD GESTOR");

        //System.out.println("A PROPOSE");
        Platform.runLater(() -> {
            workingStage.show();
        });
        pc.proposeHidato();
        //System.out.println("FINISHED");
        Platform.runLater(() -> {
            workingStage.close();
            if(pc.getHidato() != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Felicitats, té solució! ", ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    pc.setFirst(true);
                    try {
                        PrinterHidatoGenerator phg = new PrinterHidatoGenerator(pc);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //ProposarHidato ph = new ProposarHidato(pc);
                    //prova
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No ha sigut possible generar un \nhidato amb els parametres seleccionats ", ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    pc.setFirst(true);
                    try {
                        ProposarHidato ph = new ProposarHidato(pc);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //prova
                }
            }
        });
    }

    public WorkingPropose(PresentationCtrl pc, Stage stage2) throws IOException {
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
