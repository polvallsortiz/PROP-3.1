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
    protected Double size;
    protected Double panex = 600.0;
    protected Double paney = 600.0;
    PresentationCtrl pc;


    public void createboardsquare() {
        Double x;
        Double y = 0.0;
        size = ((panex/columns < paney/rows) ? panex/columns : paney /rows);
        for(int i = 0; i < hidato.size(); ++i) {
            x = 0.0;
            for (int j = 0; j < hidato.get(0).size(); ++j) {
                Vector<Point> aux = new Vector<>();
                SquareUI tu = new SquareUI(size,hidato.get(i).get(j));
                Group g = new Group();
                StackPane actual = tu.getStackPane();
                g.getChildren().add(actual);
                g.setTranslateX(x);
                g.setTranslateY(y);
                aux.add(new Point(x,y));
                aux.add(new Point(x+size,y));
                aux.add(new Point(x+size,y+size));
                aux.add(new Point(x,y+size));
                boardpane.getChildren().add(g);
                x += size;
                points.add(aux);
            }
            y+=size;
        }
    }

    public void createboardtriangle() {
        Double x;
        Double y = 0.0;
        size = ((panex/columns < paney/rows) ? panex/columns : paney /rows);
        for(int i = 0; i < hidato.size(); ++i) {
            x = 0.0;
            for (int j = 0; j < hidato.get(0).size(); ++j) {
                Vector<Point> aux = new Vector<>();
                TriangleUI tu = new TriangleUI(i, j,size,hidato.get(i).get(j));
                Group g = new Group();
                StackPane actual = tu.getStackPane();
                g.getChildren().add(actual);
                g.setTranslateX(x);
                g.setTranslateY(y);
                if((i%2 == 0 && j%2 ==0) || (i%2 != 0 && j%2 != 0)) {
                    aux.add(new Point(x+size/2,y));
                    aux.add(new Point(x+size,y+size));
                    aux.add(new Point(x,y+size));
                }
                else {
                    aux.add(new Point(x,y));
                    aux.add(new Point(x+size,y));
                    aux.add(new Point(x+size/2,y+size));
                }

                boardpane.getChildren().add(g);
                x += size/2;
                points.add(aux);
            }
            y+=size;
        }
    }

    public void createboardhexagon() {
        size = ((panex/columns < paney/rows) ? panex/columns : paney /rows);
        Double x;
        Double y = 0.0;
        Double y2 = 2*(size/3);
        for(int i = 0; i < hidato.size(); ++i) {
            if(i % 2 == 0) x = 0.0;
            else x= size/2;
            for (int j = 0; j < hidato.get(0).size(); ++j) {
                Vector<Point> aux = new Vector<>();
                HexagonUI tu = new HexagonUI(size, hidato.get(i).get(j));
                Group g = new Group();
                StackPane actual = tu.getStackPane();
                g.getChildren().add(actual);
                g.setTranslateX(x);
                if(i % 2 == 0) {
                    g.setTranslateY(y);
                    aux.add(new Point(x,y+size/3));
                    aux.add(new Point(x+size,y+size/3));
                    aux.add(new Point(x+size,y+2*(size/3)));
                    aux.add(new Point(x,y+2*(size/3)));
                }
                else {
                    g.setTranslateY(y2);
                    aux.add(new Point(x,y2+size/3));
                    aux.add(new Point(x+size,y2+size/3));
                    aux.add(new Point(x+size,y2+2*(size/3)));
                    aux.add(new Point(x,y+2*(size/3)));
                }
                boardpane.getChildren().add(g);
                x += size;
                points.add(aux);
            }
            if(i % 2 == 0) y+=(size*120)/90;
            else y2+=(size*120)/90;
        }
    }

    protected void logout() throws IOException {
        this.pc.inPlay = false;
        primaryStage.setOnHiding(e->exitWithoutSaving());
        Index i = new Index(pc);
    }

    protected void returnmenu() throws IOException {
        this.pc.inPlay = false;
        primaryStage.setOnHiding(e->exitWithoutSaving());
        Menu m = new Menu(pc);
    }

    //ONLY FOR PORPOSE HIDATO
    protected void boardclicked(Double x, Double y) throws IOException {
        Point p = new Point(x,y);
        if(celltype.equals('H')|| celltype.equals('Q')) {
            for(int i = 0; i < points.size(); ++i) {    // FOR EACH SQUARE
                Point sq0,sq1,sq3;
                sq0 = points.get(i).get(0);
                sq1 = points.get(i).get(1);
                sq3 = points.get(i).get(3);
                if(p.pointInSquare(sq0,sq1,sq3)) {
                    System.out.println("CLICKAT A " + i);
                    pc.setFirst(false);
                    EditHidatoField ehf = new EditHidatoField(pc,i,0);
                }
            }
        }
        else {
            for(int i = 0; i < points.size(); ++i) {    // FOR EACH TRIANGLE
                Point t0,t1,t2;
                t0 = points.get(i).get(0);
                t1 = points.get(i).get(1);
                t2 = points.get(i).get(2);
                if(p.pointInTriangle(t0,t1,t2)) {
                    System.out.println("CLICKAT A " + i);
                    pc.setFirst(false);
                    EditHidatoField ehf = new EditHidatoField(pc,i,0);
                }
            }
        }

    }

    void exitWithoutSaving() {}
}
