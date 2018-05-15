package datacontrol;

import domaincontrol.Game;
import domaincontrol.Hidato;
import domaincontrol.Ranking;

import java.io.IOException;
import java.sql.Time;

public class DataCtrl {
    RankingDataCtrl rankingDataCtrl;
    BoardDataCtrl boardDataCtrl;
    GameDataCtrl gameDataCtrl;

    public DataCtrl() {
        rankingDataCtrl = new RankingDataCtrl();
        boardDataCtrl = new BoardDataCtrl();
        gameDataCtrl = new GameDataCtrl();
    }
    public void guardarHidato (Hidato currentHidato) {
        boardDataCtrl.createBoard(currentHidato);
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

    public void createGame(String username) {
        gameDataCtrl.createGame(username);
    }

    public void writeGame(String pathname, Game currentGame) {
        gameDataCtrl.writeGame(pathname, currentGame);
    }
}
