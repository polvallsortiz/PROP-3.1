package domaincontrol;

import javafx.util.Pair;

import java.sql.Time;
import java.util.ArrayList;

public class Ranking {
    private String difficulty;
    private ArrayList<Pair<String,Time>> results;

    public Ranking() {
        results = new ArrayList<>();
    }

    public Ranking(String difficulty) {
        this.difficulty = difficulty;
        results = new ArrayList<>();
    }

    public ArrayList<Pair<String, Time>> getResults(String difficulty) {
        return results;
    }

    public String getDifficulty() {
        return difficulty;
    }
    public void addToRanking(String username, Time time) {
        Pair<String,Time> player = new Pair<String,Time>(username, time);
        results.add(player);
    }
}
