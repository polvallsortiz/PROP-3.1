package presentationcontrol;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditHidatoField {
    private PresentationCtrl pc;
    private TextField tf;
    private Integer i;

    EditHidatoField(PresentationCtrl pc, int i) throws IOException {
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
                accepted();
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
}
