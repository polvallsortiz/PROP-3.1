package presentationcontrol;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.util.Vector;

public class EditHidatoField {
    private PresentationCtrl pc;
    private TextField tf;
    private Integer i;

    //PROPOSE STRUCTURES
    private Integer propose; //0 IF NUMBER     1 IF HOLE     2 IF INACCESSIBLE
    JFXRadioButton numberradio;
    JFXRadioButton holeradio;
    JFXRadioButton inaccessibleradio;

    Stage sta;


    //flag == 0 if in propose
    //flag == 1 if in play
    //flag == 2 if left click
    EditHidatoField(PresentationCtrl pc, int i, int flag) throws IOException {
        this.i = i;
        this.pc = pc;
        if(pc.getHidato().get(i/pc.getColumns()).get(i%pc.getColumns()).equals("?")) {
            switch (flag) {
                case 0: //PROPOSE
                    sta = new Stage();
                    Parent root0 = FXMLLoader.load(getClass().getResource("/forms/EditHidatoFieldProposar.fxml"));
                    sta.setTitle("Editar Cel·la - Hidato Game - Proposar Hidato");
                    sta.setScene(new Scene(root0,500,300));
                    sta.setResizable(false);
                    sta.show();
                    tf = (JFXTextField) sta.getScene().lookup("#textfield");
                    Button bt0 = (Button) sta.getScene().lookup("#acceptbutton");
                    numberradio = (JFXRadioButton) sta.getScene().lookup("#numberradio");
                    holeradio = (JFXRadioButton) sta.getScene().lookup("#holeradio");
                    inaccessibleradio = (JFXRadioButton) sta.getScene().lookup("#inaccessibleradio");
                    bt0.setOnMouseClicked(e->{
                        try {
                            accepted();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    });
                    this.propose = 0;
                    numberradio.setSelected(true);
                    numberradio.setOnMouseClicked(e->numberclicked());
                    holeradio.setOnMouseClicked(e->holeclicked());
                    inaccessibleradio.setOnMouseClicked(e->inaccessibleclicked());
                    break;

                case 1: //PLAY
                    sta = new Stage();
                    sta.setOnHiding(e->noevent());
                    Parent root = FXMLLoader.load(getClass().getResource("/forms/EditHidatoField.fxml"));
                    sta.setTitle("Editar Cel·la - Hidato Game - Partida en Curs");
                    sta.setScene(new Scene(root,500,300));
                    sta.setResizable(false);
                    sta.show();
                    tf = (JFXTextField) sta.getScene().lookup("#textfield");
                    Button bt = (Button) sta.getScene().lookup("#acceptbutton");
                    bt.setOnMouseClicked(e->{
                        try {
                            play();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    });
                    break;

                case 2: //LEFT CLICK
                    playnext();
                    break;
            }
        }
    }

    private void noevent() {
    }

    private void accepted() throws IOException {
        Vector<Vector<String>> temp = pc.getHidato();
        Vector<String> temp2 = temp.get(i/pc.getColumns());
        switch (propose) {
            case 0: //NUMBER
                if(checkrange()) {
                    sta.close();
                    temp2.set(i%pc.getColumns(),tf.getText());
                }
                else {
                    tf.setText("");
                    tf.setPromptText("VALOR FORA DE RANG!");
                    return;
                }
                break;

            case 1: //HOLE
                sta.close();
                temp2.set(i%pc.getColumns(),"#");
                break;

            case 2: //INACCESSIBLE
                sta.close();
                temp2.set(i%pc.getColumns(),"*");
                break;
        }
        temp.set(i/pc.getColumns(),temp2);
        pc.setHidato(temp);
        PrinterHidatoProposar php = new PrinterHidatoProposar(pc);
    }


    //LEFT CLICK
    private void playnext() throws IOException {
        int movement = pc.firstEmptyNumber();
        Character result = pc.nextMovement(i, String.valueOf(movement));
        switch (result) {
            case 'C':
                //CRIDA A COMPLETED
                pc.getPrimaryStage().setOnHiding(e->noevent());
                HidatoCompleted hc = new HidatoCompleted(pc);
                break;

            case 'O':
                pc.getHidato().get(i / pc.getColumns()).set(i % pc.getColumns(), String.valueOf(movement));
                //pc.setActualnum(Integer.valueOf(next));
                pc.refreshData();
                PrinterHidatoPlayer php = new PrinterHidatoPlayer(pc);
                break;

            case 'W':
                if (pc.getDifficulty().equals("Easy")) {
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

    //RIGHT CLICK
    private void play() throws IOException {
        Character result;
        if(checkrange()) {
            sta.close();
            result = pc.nextMovement(i, tf.getText());
        }
        else {
            tf.setText("");
            tf.setPromptText("VALOR FORA DE RANG!");
            return;
        }
        switch (result) {
            case 'C':
                //CRIDA A COMPLETED
                pc.getPrimaryStage().setOnHiding(e->noevent());
                HidatoCompleted hc = new HidatoCompleted(pc);
                break;

            case 'O':
                pc.getHidato().get(i/pc.getColumns()).set(i%pc.getColumns(),tf.getText());
                pc.refreshData();
                PrinterHidatoPlayer php = new PrinterHidatoPlayer(pc);
                break;

            case 'W':
                if (pc.getDifficulty().equals("Easy")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Moviment Erroni!", ButtonType.OK);
                    alert.setHeaderText("ERROR MOVIMENT");
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                }
                else {
                    Vector<Vector<String>> temp = pc.getHidato();
                    Vector<String> temp2 = temp.get(i / pc.getColumns());
                    temp2.set(i % pc.getColumns(), String.valueOf(tf.getText()));
                    temp.set(i / pc.getColumns(), temp2);
                    pc.setHidato(temp);
                    PrinterHidatoPlayer php2 = new PrinterHidatoPlayer(pc);
                }
                break;
        }
    }

    private void numberclicked() {
        numberradio.setSelected(true);
        holeradio.setSelected(false);
        inaccessibleradio.setSelected(false);
        tf.setDisable(false);
        propose = 0;
    }

    private void holeclicked() {
        holeradio.setSelected(true);
        numberradio.setSelected(false);
        inaccessibleradio.setSelected(false);
        tf.setDisable(true);
        propose = 1;
    }

    private void inaccessibleclicked() {
        inaccessibleradio.setSelected(true);
        numberradio.setSelected(false);
        holeradio.setSelected(false);
        tf.setDisable(true);
        propose = 2;
    }

    private boolean checkrange() {
        int actual = Integer.parseInt(tf.getText());
        int size = pc.getRows() * pc.getColumns();
        if(actual <= size && actual > 0) return true;
        else return false;
    }

}
