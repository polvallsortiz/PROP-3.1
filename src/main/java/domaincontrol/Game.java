package domaincontrol;

public class Game {
    Player player;
    Double score;
    Integer difficulty;
    Board board;

    public Game(String username) {
        player = new Player(username);
        board = null;
    }

    public Player getPlayer() {
        return player;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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
}
