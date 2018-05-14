package presentationcontrol;

import domaincontrol.DomainCtrl;
import javafx.beans.binding.IntegerExpression;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class PresentationCtrl {
    //CONTROLLERS
    private DomainCtrl dc;

    private Vector<Vector<String>>  hidato;
    private Character celltype;
    private String adjacencytype;
    private int rows;
    private int columns;

    //GAME PARAMETERS
    private String usern;
    private Stage primaryStage;


    //NEEDED FOR GENERATOR
    private int holes;
    private int predefined;

    PresentationCtrl() {
        hidato = new Vector<>();
        dc = new DomainCtrl();
    }

    public void matrix_generator_GUI() {
        for(int i = 0; i < rows; ++i) {
            String auxiliar[] = new String[columns];
            Arrays.fill(auxiliar,"?");
            hidato.add(new Vector<String>(Arrays.asList(auxiliar)));
        }
    }

    public void reset_pc() {
        hidato = new Vector<>();
        celltype = null;
        adjacencytype = null;
        rows = 0;
        columns = 0;
        holes = 0;
        predefined = 0;
    }

    public Vector<Vector<String>> getHidato() {
        return hidato;
    }

    public void setHidato(Vector<Vector<String>> hidato) {
        this.hidato = hidato;
    }

    public String getUsern() {
        return usern;
    }

    public void setUsern(String usern) {
        this.usern = usern;
    }

    public Character getCelltype() {
        return celltype;
    }

    public void setCelltype(Character celltype) {
        this.celltype = celltype;
    }

    public String getAdjacencytype() {
        return adjacencytype;
    }

    public void setAdjacencytype(String adjacencytype) {
        this.adjacencytype = adjacencytype;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Integer getHoles() {
        return holes;
    }

    public void setHoles(int holes) {
        this.holes = holes;
    }

    public int getPredefined() {
        return predefined;
    }

    public void setPredefined(int predefined) {
        this.predefined = predefined;
    }

    public void generateHidato() {
        setHidato(dc.generateHidato(hidato,adjacencytype,celltype,holes,predefined));
    }

    /*private int matrix_generator(String input) {
        for(int i = 0; i < lines; ++i) {
            String auxiliar[] = new String[columns];
            Arrays.fill(auxiliar,"?");
            hidato.add(new Vector<String>(Arrays.asList(auxiliar)));
        }
        if(input != "-1") {
            List<String> data = Arrays.asList(input.split(","));
            int hast = data.size();
            if (lines * columns < (hast + holes + predefined)) return 0;
            for (String actual : data) {
                int act = Integer.parseInt(actual);
                Vector<String> mod = hidato.get(act / lines);
                mod.set(act % lines, "#");
                hidato.set(act / lines, mod);
            }
        }
        else if (lines * columns < (holes + predefined)) return 0;
        return 1;
    }
*/

}
