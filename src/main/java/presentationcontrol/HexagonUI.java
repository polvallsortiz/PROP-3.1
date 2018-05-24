package presentationcontrol;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

public class HexagonUI {
    private StackPane stackPane;
    private Polygon hexagon;
    private Label label;

    HexagonUI(Double size, String label) {
        this.label = new Label(label);
        hexagon = new Polygon();
        stackPane = new StackPane();
        hexagon.getPoints().addAll(new Double[]{
                size/2, 0.0,
                size, size/3,
                size, 2*(size/3),
                size/2, size,
                0.0, 2*(size/3),
                0.0, size/3
        });
        if(label.equals("*")) {
            hexagon.setFill(Color.valueOf("#3f51b5"));
            hexagon.setStroke(Color.valueOf("#3f51b5"));
            this.label.setText("");
        }
        else if(label.equals("#")) {
            hexagon.setFill(Color.valueOf("#f8fdff"));
            hexagon.setStroke(Color.valueOf("#f8fdff"));
            this.label.setText("");
        }
        else if(label.equals("?")) {
            this.label.setText("");
            hexagon.setFill(Color.valueOf("#9499b7"));
            hexagon.setStroke(Color.valueOf("#002984"));
        }
        else {
            hexagon.setFill(Color.valueOf("#757de8"));
            hexagon.setStroke(Color.valueOf("#002984"));
        }
        this.label.setTextFill(Color.valueOf("#000"));
        this.label.setFont(new Font(40));
        hexagon.setStrokeWidth(3.0);
        stackPane.getChildren().addAll(hexagon,this.label);
    }

    public StackPane getStackPane() {
        return stackPane;
    }
}
