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
        square = new Rectangle(90,90);
        stackPane = new StackPane();
        if(label.equals("*")) {
            square.setFill(Color.valueOf("#3f51b5"));
            square.setStroke(Color.valueOf("#3f51b5"));
            this.label.setText("");
        }
        else if(label.equals("#")) {
            square.setFill(Color.valueOf("#f8fdff"));
            square.setStroke(Color.valueOf("#f8fdff"));
            this.label.setText("");
        }
        else {
            square.setFill(Color.valueOf("#9499b7"));
            square.setStroke(Color.valueOf("#002984"));
        }
        this.label.setTextFill(Color.valueOf("#000"));
        square.setStrokeWidth(3.0);
        stackPane.getChildren().addAll(square,this.label);
    }

    public StackPane getStackPane() {
        return stackPane;
    }
}
