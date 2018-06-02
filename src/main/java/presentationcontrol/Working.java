package presentationcontrol;

import com.jfoenix.controls.JFXSpinner;
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
    Semaphore semwork,semfinished;

    @Override
    public void run() {
        System.out.println("THREAD GESTOR");
        while(true) {
            try {
                semwork.acquire();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Treballant per a tu!");
                alert.setHeaderText("Working");
                alert.showAndWait();
                semfinished.acquire();
                alert.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Working(Semaphore semwork, Semaphore semfinished) throws IOException {
        this.semwork = semwork;
        this.semfinished = semfinished;
        //workingStage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("/forms/Working.fxml"));
        /*workingStage.setScene(new Scene(root,700,100));
        workingStage.setTitle("");
        workingStage.setResizable(false);*/
    }
}
