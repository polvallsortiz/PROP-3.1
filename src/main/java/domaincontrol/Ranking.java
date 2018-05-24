package domaincontrol;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public ArrayList<Pair<String, Integer>> getResults() {
        return results;
    }

    public String getDifficulty() {
        return difficulty;
    }
    public void addToRanking(String username, Integer seconds) {
        Pair<String,Integer> player = new Pair<String,Integer>(username, seconds);
        results.add(player);
        Collections.sort(results, new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(final Pair<String, Integer> o1, final Pair<String, Integer> o2) {
                return o1.getValue()- o2.getValue();
            }
        });
    }
}
