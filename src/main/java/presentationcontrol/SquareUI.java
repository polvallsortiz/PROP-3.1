package presentationcontrol;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquareUI {
    private StackPane stackPane;
    private Rectangle square;
    private Label label;

    SquareUI(int i, int j, String label) {
        this.label = new Label(label);
        square = new Rectangle(100,100);
        stackPane = new StackPane();
        square.setFill(javafx.scene.paint.Color.valueOf("#000"));
        square.setStroke(Color.web("#0F0"));
        this.label.setTextFill(Color.valueOf("#FFF"));
        stackPane.getChildren().addAll(square,this.label);
    }

    public StackPane getStackPane() {
        return stackPane;
    }
}
