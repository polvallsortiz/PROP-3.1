package datacontrol;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import domaincontrol.Ranking;

import java.io.*;

public class RankingDataCtrl {
    private String home;

    public RankingDataCtrl() {
        home = new File(System.getProperty("user.home")).getAbsolutePath();
    }

    public Ranking createRanking(String dificultat) {

        Ranking result = new Ranking(dificultat);
        File dir1 = new File(home + "/HidatoPlayer/Rankings");
        if (!dir1.exists()) {
            dir1.mkdir();
        }
        File file = new File(home +"/HidatoPlayer/Rankings/" + dificultat + ".json");

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
        File file = new File(home + "/HidatoPlayer/Rankings/" + dificultat + ".json");
        try (FileWriter files = new FileWriter(file)) {
            files.write(json);
            files.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
