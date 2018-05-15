package domaincontrol;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private Player player;
    private Integer difficulty;
    private Board board;
    private Map<Integer,Hidato> movements;
    private int move;
    private Time tempsinici;
    private double score;

    public Game(){
    }

    public Game(String username) {
        tempsinici = new Time(System.currentTimeMillis());
        player = new Player(username);
        board = null;
        score = 0;
        movements = new HashMap<>();
        move = 0;
    }
    public Time getTempsinici(){
        return tempsinici;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public int getMove() {
        return move;
    }

    public Player getPlayer() {
        return player;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer defineGame() {
        //Calcular dificultat a partir dels parametres que determinem
        return difficulty;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public Map<Integer,Hidato> getMovements() {
        return movements;
    }
     public void setMovements(Map<Integer,Hidato> movements) {
        this.movements = movements;
     }

    public void addMovement(Hidato currentHidato) {
        Map<Integer,Hidato> previousmovements = getMovements();
        move = move+1;

        if (previousmovements != null) {
            previousmovements.put(move, currentHidato);
            setMovements(previousmovements);
        }
        else {
            Map<Integer,Hidato> res = new HashMap<>();
            res.put(move,currentHidato);
            setMovements(res);
        }
    }
}
