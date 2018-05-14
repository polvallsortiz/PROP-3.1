package presentationcontrol;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.awt.*;


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
                        45.0, 0.0,
                        90.0, 90.0,
                        0.0, 90.0
                });
            } else {
                triangle.getPoints().addAll(new Double[]{
                        0.0, 0.0,
                        90.0, 0.0,
                        45.0, 90.0

                });
            }
        }
        else {
            if (j % 2 != 0) {
                triangle.getPoints().addAll(new Double[]{
                        45.0, 0.0,
                        90.0, 90.0,
                        0.0, 90.0
                });
            } else {
                triangle.getPoints().addAll(new Double[]{
                        0.0, 0.0,
                        90.0, 0.0,
                        45.0, 90.0

                });

            }

        }
        if(label.equals("*")) {
            triangle.setFill(Color.valueOf("#3f51b5"));
            triangle.setStroke(Color.valueOf("#3f51b5"));
            this.label.setText("");
        }
        else if(label.equals("#")) {
            triangle.setFill(Color.valueOf("#f8fdff"));
            triangle.setStroke(Color.valueOf("#f8fdff"));
            this.label.setText("");
        }
        else {
            triangle.setFill(Color.valueOf("#9499b7"));
            triangle.setStroke(Color.valueOf("#002984"));
        }
        this.label.setTextFill(Color.valueOf("#000"));
        triangle.setStrokeWidth(3.0);
        stackPane.getChildren().addAll(triangle,this.label);
    }

    public StackPane getStackPane() {
        return stackPane;
    }
}