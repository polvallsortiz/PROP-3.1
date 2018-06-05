package datacontrol;

import domaincontrol.Game;
import domaincontrol.Hidato;
import domaincontrol.Ranking;

import java.io.File;
import java.io.IOException;
import java.sql.Time;

public class DataCtrl {
    RankingDataCtrl rankingDataCtrl;
    BoardDataCtrl boardDataCtrl;
    GameDataCtrl gameDataCtrl;

    public DataCtrl() {
        String home = new File(System.getProperty("user.home")).getAbsolutePath();
        File dir1 = new File(home + "/HidatoPlayer");
        if (!dir1.exists()) {
            dir1.mkdir();
        }
        rankingDataCtrl = new RankingDataCtrl();
        boardDataCtrl = new BoardDataCtrl();
        gameDataCtrl = new GameDataCtrl();
    }
    public void createHidato () {
        boardDataCtrl.createBoard();
    }
    public Hidato getBoard (String pathname) {
        return boardDataCtrl.getBoard(pathname);
    }
    public Ranking getRanking(String difficulty) {
        return rankingDataCtrl.createRanking(difficulty);
    }
    public void setRanking(String difficulty, Ranking ran) {
        rankingDataCtrl.writeRanking(difficulty,ran);
    }

    public Game getGame(String pathname) {
        return gameDataCtrl.getGame(pathname);
    }

    public void createGame() {
        gameDataCtrl.createGame();
    }

    public int writeGame(String pathname, Game currentGame) {
        return gameDataCtrl.writeGame(pathname, currentGame);
    }
    public int writeHidato(String pathname, Hidato currentHidato) {
        return boardDataCtrl.writeBoard(pathname, currentHidato);
    }
}
