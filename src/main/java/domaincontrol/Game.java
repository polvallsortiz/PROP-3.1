package domaincontrol;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Game {
    private Player player;
    private String difficulty;
    private float difficultyNumber;
    private Hidato board;
    private Map<Integer, Hidato> movements;
    private int move;
    private Time tempsinici;
    private double score;

    public Game() {
    }

    public Game(String username) {
        player = new Player(username);
        board = null;
        score = 0;
        movements = new HashMap<>();
        move = 0;
    }

    public Time getTempsinici() {
        return tempsinici;
    }

    public void startGame(){
        tempsinici = new Time(System.currentTimeMillis());
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getLastMove() {
        return move;
    }
    public void setLastMove(int move) {
        this.move = move;
    }
    public Player getPlayer() {
        return player;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String defineGame(Hidato hidato) {
        int freeCells = 0;
        int totallCells = 0;
        Vector<Vector<String>> mat = hidato.getHidato();
        int lines = hidato.getLines();
        int columns = hidato.getColumns();
        for (int i = 0; i < lines; ++i) { //LINIA
            Vector<String> aux = mat.elementAt(i);
            for (int j = 0; j < columns; ++j) {
                if (aux.elementAt(j) == "?") ++freeCells;
                ++totallCells;
            }
        }
        difficultyNumber = ((float)freeCells/(float)totallCells)+((float)totallCells/(float)64);
        if (difficultyNumber >= 1.9) difficulty = "Hard";
        else if (difficultyNumber < 1.9 && difficultyNumber > 1.45) difficulty = "Medium";
        else difficulty = "Easy";
        return difficulty;
    }

    public void setBoard(Hidato board) {
        this.board = board;
    }

    public Hidato getBoard() {
        return board;
    }

    public Map<Integer, Hidato> getMovements() {
        return movements;
    }

    public void setMovements(Map<Integer, Hidato> movements) {
        this.movements = movements;
    }

    public void addMovement(Hidato currentHidato) {
        Map<Integer, Hidato> previousmovements = getMovements();
        move = move + 1;

        if (previousmovements != null) {
            previousmovements.put(move, currentHidato);
            setMovements(previousmovements);
        } else {
            Map<Integer, Hidato> res = new HashMap<>();
            res.put(move, currentHidato);
            setMovements(res);
        }
    }
}
