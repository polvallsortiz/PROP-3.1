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
        hexagon.setFill(Color.valueOf("#000"));
        hexagon.setStroke(Color.valueOf("#0F0"));
        this.label.setTextFill(Color.web("#FFF"));
        stackPane.getChildren().addAll(hexagon,this.label);
    }

    public StackPane getStackPane() {
        return stackPane;
    }
}
