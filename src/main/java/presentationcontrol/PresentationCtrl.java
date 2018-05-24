package presentationcontrol;

import domaincontrol.DomainCtrl;
import domaincontrol.Hidato;
import domaincontrol.Ranking;
import javafx.stage.Stage;

import java.util.Arrays;
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
    private  String difficulty;
    private Integer actualnum;

    //NEEDED FOR GENERATOR
    private int holes;
    private int predefined;

    //NEEDED FOR PROPOSE
    private boolean first;

    PresentationCtrl() {
        hidato = new Vector<>();
        dc = new DomainCtrl();
        dc.newGame(usern);
        dc.addToRanking();
        first = true;
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
        Hidato newHidato = new Hidato();
        newHidato.setHidato(hidato);
        newHidato.setLines(hidato.size());
        newHidato.setColumns(hidato.get(0).size());
        newHidato.setAdjacencytype(adjacencytype);
        newHidato.setCelltype(celltype);
        newHidato.setHidato(dc.generateHidato(newHidato,holes,predefined));
        hidato = newHidato.getHidato();
    }

    public void proposeHidato() {
        dc.newGame(usern);
        Hidato newHidato = new Hidato();
        newHidato.setHidato(hidato);
        newHidato.setLines(hidato.size());
        newHidato.setColumns(hidato.get(0).size());
        newHidato.setAdjacencytype(adjacencytype);
        newHidato.setCelltype(celltype);
        setHidato(dc.defineHidato(newHidato));
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public Ranking loadRanking(String difficulty) {
        return dc.loadRanking(difficulty);
    }

    public Character nextMovement(int idCell, String nextValue){ return dc.nextMovement(idCell,nextValue);}

    public void playHidato() { difficulty = dc.playHidato(); }

    public void newGame(String username) { dc.newGame(username);}

    public void solveHidato() { hidato = dc.solveHidato(); }

    public void addToRanking() { dc.addToRanking(); }

    public void saveRanking() { dc.saveRanking(); }

    public Integer getActualnum() {
        return actualnum;
    }

    public void setActualnum(Integer actualnum) {
        this.actualnum = actualnum;
    }

    public int saveGame(String path) { return dc.saveGame(path);}

    public Vector<Vector<String>> loadGame (String path) { return dc.loadGame(path); }

}
