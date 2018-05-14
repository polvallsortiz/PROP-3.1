package domaincontrol;

import javafx.util.Pair;

import java.util.ArrayList;

public class Ranking {
    private String difficulty;
    private ArrayList<Pair<String,Integer>> results;

    public Ranking() {
        results = new ArrayList<>();
    }

    public Ranking(String difficulty) {
        this.difficulty = difficulty;
        results = new ArrayList<>();
    }

    public ArrayList<Pair<String, Integer>> getResults(String difficulty) {
        return results;
    }

    public String getDifficulty() {
        return difficulty;
    }
    public void addToRanking(String username, Integer seconds) {
        Pair<String,Integer> player = new Pair<String,Integer>(username, seconds);
        results.add(player);
    }
}
