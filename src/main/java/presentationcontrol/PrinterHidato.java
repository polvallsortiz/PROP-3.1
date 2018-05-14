package presentationcontrol;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Vector;

public class PrinterHidato {
    protected Label username;
    protected Button logoutbutton;
    protected Button menubutton;
    protected Parent root2;


    Vector<Vector<String>> hidato;
    Vector<Vector<Point>> points; //FOR TRIANGLES WILL BE Vector.size == 3 and other .size == 4
    Stage primaryStage;
    protected Pane boardpane;
    protected Character celltype;
    protected Integer rows;
    protected Integer columns;
    protected String adjacency;
    protected Integer holes;
    protected Integer predefined;
    PresentationCtrl pc;

    public void createboardsquare() {
        Double x;
        Double y = 0.0;
        for(int i = 0; i < hidato.size(); ++i) {
            x = 0.0;
            for (int j = 0; j < hidato.get(0).size(); ++j) {
                Vector<Point> aux = new Vector<>();
                SquareUI tu = new SquareUI(i, j, hidato.get(i).get(j));
                Group g = new Group();
                StackPane actual = tu.getStackPane();
                g.getChildren().add(actual);
                g.setTranslateX(x);
                g.setTranslateY(y);
                aux.add(new Point(x,y));
                aux.add(new Point(x+90,y));
                aux.add(new Point(x+90,y+90));
                aux.add(new Point(x,y+90));
                boardpane.getChildren().add(g);
                x += 90;
                points.add(aux);
            }
            y+=90;
        }
    }

    public void createboardtriangle() {
        Double x;
        Double y = 0.0;
        for(int i = 0; i < hidato.size(); ++i) {
            x = 0.0;
            for (int j = 0; j < hidato.get(0).size(); ++j) {
                Vector<Point> aux = new Vector<>();
                TriangleUI tu = new TriangleUI(i, j, hidato.get(i).get(j));
                Group g = new Group();
                StackPane actual = tu.getStackPane();
                g.getChildren().add(actual);
                g.setTranslateX(x);
                g.setTranslateY(y);
                if((i%2 == 0 && j%2 ==0) || (i%2 != 0 && j%2 != 0)) {
                    aux.add(new Point(x+45,y));
                    aux.add(new Point(x+90,y+90));
                    aux.add(new Point(x,y+90));
                }
                else {
                    aux.add(new Point(x,y));
                    aux.add(new Point(x+90,y));
                    aux.add(new Point(x+45,y+90));
                }

                boardpane.getChildren().add(g);
                x += 45;
                points.add(aux);
            }
            y+=90;
        }
    }

    public void createboardhexagon() {
        //TODO : Dinamically set width and height
        Double x;
        Double y = 0.0;
        Double y2 = 60.0;
        for(int i = 0; i < hidato.size(); ++i) {
            if(i % 2 == 0) x = 0.0;
            else x= 45.0;
            for (int j = 0; j < hidato.get(0).size(); ++j) {
                Vector<Point> aux = new Vector<>();
                HexagonUI tu = new HexagonUI(i, j, hidato.get(i).get(j));
                Group g = new Group();
                StackPane actual = tu.getStackPane();
                g.getChildren().add(actual);
                g.setTranslateX(x);
                if(i % 2 == 0) {
                    g.setTranslateY(y);
                    aux.add(new Point(x,y+30));
                    aux.add(new Point(x+90,y+30));
                    aux.add(new Point(x+90,y+60));
                    aux.add(new Point(x,y+60));
                }
                else {
                    g.setTranslateY(y2);
                    aux.add(new Point(x,y2+30));
                    aux.add(new Point(x+90,y2+30));
                    aux.add(new Point(x+90,y2+60));
                    aux.add(new Point(x,y+60));
                }
                boardpane.getChildren().add(g);
                x += 90;
                points.add(aux);
            }
            if(i % 2 == 0) y+=120;
            else y2+=120;
        }
    }

    protected void logout() throws IOException {
        primaryStage.close();
        primaryStage = new Stage();
        pc.setPrimaryStage(primaryStage);
        Index i = new Index(pc);
    }

    protected void returnmenu() throws IOException {
        primaryStage.close();
        primaryStage = new Stage();
        pc.setPrimaryStage(primaryStage);
        Menu m = new Menu(pc);
    }
}
