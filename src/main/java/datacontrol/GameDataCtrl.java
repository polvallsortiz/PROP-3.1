package datacontrol;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import domaincontrol.Game;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;

public class GameDataCtrl {
    public GameDataCtrl() {

    }

    public void createGame(String username) {
        Game result = new Game(username);
        Time time = result.getTempsinici();
        File file = new File("resources/files/Games/" + time + ".json");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Game getGame(String path) {
        Game result = new Game();
        File file = new File(path);
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(file));
            result = gson.fromJson(reader, Game.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public void writeGame(String path, Game currentGame) {
        Gson gson = new Gson();
        String json = gson.toJson(currentGame);
        File file = new File(path);
        try (FileWriter files = new FileWriter(file)) {
            files.write(json);
            files.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
