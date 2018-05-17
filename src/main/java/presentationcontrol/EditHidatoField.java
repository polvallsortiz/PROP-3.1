package presentationcontrol;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditHidatoField {
    private PresentationCtrl pc;
    private TextField tf;
    private Integer i;


    //flag == 0 if in propose
    //flag == 1 if in play
    EditHidatoField(PresentationCtrl pc, int i, int flag) throws IOException {
        this.i = i;
        this.pc = pc;
        Stage sta = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/forms/EditHidatoField.fxml"));
        sta.setTitle("Editar Cel·la - Hidato Game");
        sta.setScene(new Scene(root, 500, 300));
        sta.setResizable(false);
        sta.show();
        tf = (JFXTextField) sta.getScene().lookup("#textfield");
        Button bt = (Button) sta.getScene().lookup("#acceptbutton");

        bt.setOnMouseClicked(e->{
            sta.close();
            try {
                if(flag == 0) accepted();
                 else play();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void accepted() throws IOException {
        pc.getHidato().get(i/pc.getColumns()).set(i%pc.getColumns(),tf.getText());
        pc.getPrimaryStage().close();
        pc.setPrimaryStage(new Stage());
        PrinterHidatoProposar php = new PrinterHidatoProposar(pc);
    }

    private void play() throws IOException {
        //pc.getHidato().get(i/pc.getColumns()).set(i%pc.getColumns(),tf.getText());
        Character result = pc.nextMovement(i,tf.getText());
        switch (result) {
            case 'C':
                //CRIDA A COMPLETED
                pc.getPrimaryStage().close();
                pc.setPrimaryStage(new Stage());
                HidatoCompleted hc = new HidatoCompleted(pc);
                break;

            case 'O':
                pc.getHidato().get(i/pc.getColumns()).set(i%pc.getColumns(),tf.getText());
                pc.getPrimaryStage().close();
                pc.setPrimaryStage(new Stage());
                PrinterHidatoPlayer php = new PrinterHidatoPlayer(pc);
                break;

            case 'W':
                Alert alert = new Alert(Alert.AlertType.ERROR, "Moviment Erroni!", ButtonType.OK);
                alert.setHeaderText("ERROR MOVIMENT");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    alert.close();
                }
                break;
        }
    }
}
