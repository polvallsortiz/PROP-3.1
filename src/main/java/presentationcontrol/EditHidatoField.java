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
import java.util.Vector;

public class EditHidatoField {
    private PresentationCtrl pc;
    private TextField tf;
    private Integer i;

    Stage sta;


    //flag == 0 if in propose
    //flag == 1 if in play
    //flag == 2 if left click
    EditHidatoField(PresentationCtrl pc, int i, int flag) throws IOException {
        this.i = i;
        this.pc = pc;
        if(flag != 2) {
            sta = new Stage();
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
        else playnext();


    }



    //LEFT CLICK
    private void playnext() throws IOException {
        int movement = pc.firstEmptyNumber();
        Character result = pc.nextMovement(i, String.valueOf(movement));
        switch (result) {
            case 'C':
                //CRIDA A COMPLETED
                HidatoCompleted hc = new HidatoCompleted(pc);
                break;

            case 'O':
                pc.getHidato().get(i / pc.getColumns()).set(i % pc.getColumns(), String.valueOf(movement));
                //pc.setActualnum(Integer.valueOf(next));
                pc.refreshData();
                PrinterHidatoPlayer php = new PrinterHidatoPlayer(pc);
                break;

            case 'W':
                if (pc.getDifficulty() == "Easy") {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Moviment Erroni!", ButtonType.OK);
                    alert.setHeaderText("ERROR MOVIMENT");
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                } else {
                    Vector<Vector<String>> temp = pc.getHidato();
                    Vector<String> temp2 = temp.get(i / pc.getColumns());
                    temp2.set(i % pc.getColumns(), String.valueOf(movement));
                    temp.set(i / pc.getColumns(), temp2);
                    pc.setHidato(temp);
                    PrinterHidatoPlayer php2 = new PrinterHidatoPlayer(pc);
                }
                break;
        }
    }


    private void accepted() throws IOException {
        pc.getHidato().get(i/pc.getColumns()).set(i%pc.getColumns(),tf.getText());
        sta.close();
        PrinterHidatoProposar php = new PrinterHidatoProposar(pc);
    }


    //RIGHT CLICK
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
                pc.refreshData();
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
