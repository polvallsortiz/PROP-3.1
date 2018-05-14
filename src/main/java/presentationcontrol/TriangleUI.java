package presentationcontrol;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public class TriangleUI {
    private StackPane stackPane;
    private Polygon triangle;
    private Label label;

    TriangleUI(int i, int j, String label) {
        this.label = new Label(label);
        triangle = new Polygon();
        stackPane = new StackPane();
        if(i%2 == 0) {
            if (j % 2 == 0) {
                triangle.getPoints().addAll(new Double[]{
                        50.0, 0.0,
                        0.0, 50.0,
                        100.0, 50.0
                });
            } else {
                triangle.getPoints().addAll(new Double[]{
                        0.0, 0.0,
                        50.0, 50.0,
                        100.0, 0.0

                });
            }
        }
        else {
            if (j % 2 != 0) {
                triangle.getPoints().addAll(new Double[]{
                        50.0, 0.0,
                        0.0, 50.0,
                        100.0, 50.0
                });
            } else {
                triangle.getPoints().addAll(new Double[]{
                        0.0, 0.0,
                        50.0, 50.0,
                        100.0, 0.0

                });
            }

        }
        triangle.setFill(Color.valueOf("#000"));
        triangle.setStroke(Color.valueOf("#0F0"));
        this.label.setTextFill(Color.web("#FFF"));
        stackPane.getChildren().addAll(triangle,this.label);
    }

    public StackPane getStackPane() {
        return stackPane;
    }
}
