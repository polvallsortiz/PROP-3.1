package presentationcontrol;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class HexagonUI {
    private StackPane stackPane;
    private Polygon hexagon;
    private Label label;

    HexagonUI(int i, int j, String label) {
        this.label = new Label(label);
        hexagon = new Polygon();
        stackPane = new StackPane();
        hexagon.getPoints().addAll(new Double[]{
                45.0, 0.0,
                90.0, 30.0,
                90.0, 60.0,
                45.0, 90.0,
                0.0, 60.0,
                0.0, 30.0
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
        else {
            hexagon.setFill(Color.valueOf("#9499b7"));
            hexagon.setStroke(Color.valueOf("#002984"));
        }
        this.label.setTextFill(Color.valueOf("#000"));
        hexagon.setStrokeWidth(3.0);
        stackPane.getChildren().addAll(hexagon,this.label);
    }

    public StackPane getStackPane() {
        return stackPane;
    }
}
