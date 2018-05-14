package datacontrol;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import domaincontrol.Ranking;

import java.io.*;

public class RankingDataCtrl {

    public RankingDataCtrl() {

    }

    public Ranking createRanking(String dificultat) {

        Ranking result = new Ranking(dificultat);
        File file = new File("resources/files/ranking/" + dificultat + ".json");
        try {
            if (!file.exists()) {
                file.createNewFile();
                String json = "{}";
                try (FileWriter files = new FileWriter(file)) {
                    files.write(json);
                    files.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new FileReader(file));
                result = gson.fromJson(reader, Ranking.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void writeRanking(String dificultat, Ranking tipusranking) {
        Gson gson = new Gson();
        String json = gson.toJson(tipusranking);
        File file = new File("resources/files/ranking/" + dificultat + ".json");
        try (FileWriter files = new FileWriter(file)) {
            files.write(json);
            files.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
