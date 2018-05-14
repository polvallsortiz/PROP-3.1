package datacontrol;

import domaincontrol.Ranking;

import java.io.IOException;
import java.sql.Time;

public class DataCtrl {
    RankingDataCtrl rankingDataCtrl;
    RankingBoardCtrl rankingBoardCtrl;

    public DataCtrl() {
        rankingDataCtrl = new RankingDataCtrl();
    }

        //ran.createRanking("Easy");
        //ran.createRanking("Medium");
        //ran.createRanking("Hard");
    public Ranking getRanking(String difficulty) {
        return rankingDataCtrl.createRanking(difficulty);
    }
    public void setRanking(String difficulty, Ranking ran) {
        rankingDataCtrl.writeRanking(difficulty,ran);
    }
}
