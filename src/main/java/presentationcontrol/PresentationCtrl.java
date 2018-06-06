package presentationcontrol;

import domaincontrol.DomainCtrl;
import domaincontrol.Hidato;
import domaincontrol.Ranking;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class PresentationCtrl {
    //CONTROLLERS
    private DomainCtrl dc;

    Hidato hidato;

    //GAME PARAMETERS
    private String usern;
    private Stage primaryStage;
    private Stage workingStage;
    private  String difficulty;

    //THREADS IMPLEMENTATION
    public Character result;
    public int i;
    public int value;

    //NEEDED FOR GENERATOR
    private int holes;
    private int predefined;

    //NEEDED FOR PROPOSE
    private boolean first;

    public PresentationCtrl() {
        hidato = new Hidato();
        dc = new DomainCtrl();
        dc.newGame(usern);
//        dc.addToRanking();
        first = true;
    }

    public void refreshData() {
        hidato = dc.getCurrentHidato();
    }

    public void matrix_generator_GUI() {
        Vector<Vector<String>> temp = new Vector<>();
        for(int i = 0; i < hidato.getLines(); ++i) {
            String auxiliar[] = new String[hidato.getColumns()];
            Arrays.fill(auxiliar,"?");
            temp.add(new Vector<String>(Arrays.asList(auxiliar)));
        }
        hidato.setHidato(temp);
    }

    public void reset_pc() {
        hidato = new Hidato();
        holes = 0;
        predefined = 0;
    }

    public Vector<Vector<String>> getHidato() {
        return hidato.getHidato();
    }

    public void setHidato(Vector<Vector<String>> hidato) {
        this.hidato.setHidato(hidato);
    }

    public String getUsern() {
        return usern;
    }

    public void setUsern(String usern) {
        this.usern = usern;
    }

    public Character getCelltype() {
        return hidato.getCelltype();
    }

    public void setCelltype(Character celltype) {
        this.hidato.setCelltype(celltype);
    }

    public String getAdjacencytype() {
        return this.hidato.getAdjacencytype();
    }

    public void setAdjacencytype(String adjacencytype) {
        this.hidato.setAdjacencytype(adjacencytype);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Integer getRows() {
        return hidato.getLines();
    }

    public void setRows(int rows) {
        hidato.setLines(rows);
    }

    public Integer getColumns() {
        return hidato.getColumns();
    }

    public void setColumns(int columns) {
        hidato.setColumns(columns);
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
        hidato.setHidato(dc.generateHidato(hidato,holes,predefined));
    }

    public void proposeHidato() {
        dc.newGame(usern);
        hidato.setHidato(dc.defineHidato(hidato));
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

    public Character nextMovement(int idCell, String nextValue) { return dc.nextMovement(idCell,nextValue);}

    public void playHidato() { difficulty = dc.playHidato(); }

    public void newGame(String username) { dc.newGame(username);}

    public void solveHidato() {hidato.setHidato(dc.solveHidato()); }

    public void addToRanking() { dc.addToRanking(); }

    public void saveRanking() { dc.saveRanking(); }

    public int saveGame(String path) { return dc.saveGame(path);}

    public Hidato loadGame (String path) { return dc.loadGame(path); }

    public Vector<Vector<String>> rebootGame() {return dc.rebootGame();}

    public int firstEmptyNumber() { return dc.firstEmptyNumber(); }

    public void setClassHidato(Hidato hidato) { this.hidato = hidato; }

    public String getDifficulty() { return dc.getDifficult(); }

    public Hidato loadHidato(String path) { return dc.loadHidato(path); }

    public int saveHidato(String path) { return dc.saveHidato(path);}

    public int finalTime() { return dc.finalTime(); }

    public Hidato rollbackMovement() { return dc.rollbackMovement(); }

    public Pair<Integer, String> Hint() { return dc.Hint(); }

    public String getDifficult() { return dc.getDifficult(); }

    public Stage getWorkingStage() {
        return workingStage;
    }

    public void setWorkingStage(Stage workingStage) {
        this.workingStage = workingStage;
    }

    public int getCurrentTime () { return dc.getCurrentTime(); }

}
