package datacontrol;

import domaincontrol.Hidato;
import domaincontrol.Ranking;

import java.io.IOException;
import java.sql.Time;

public class DataCtrl {
    RankingDataCtrl rankingDataCtrl;
    BoardDataCtrl boardDataCtrl;

    public DataCtrl() {
        rankingDataCtrl = new RankingDataCtrl();
        boardDataCtrl = new BoardDataCtrl();
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
}
