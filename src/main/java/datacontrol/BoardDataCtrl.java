package datacontrol;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import domaincontrol.Hidato;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BoardDataCtrl {
    public BoardDataCtrl() {

    }

    public void createBoard() {
        String home = new File(System.getProperty("user.home")).getAbsolutePath();
        File dir1 = new File(home + "/HidatoPlayer/Boards");
        if (!dir1.exists()) {
            dir1.mkdir();
        }
    }
    public Hidato getBoard(String path) {
        Hidato result = new Hidato();
        File file = new File(path);
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(file));
            result = gson.fromJson(reader, Hidato.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public int writeBoard(String path, Hidato currentHidato) {
        Gson gson = new Gson();
        String json = gson.toJson(currentHidato);
        File file = new File(path);
        try (FileWriter files = new FileWriter(file)) {
            files.write(json);
            files.close();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
