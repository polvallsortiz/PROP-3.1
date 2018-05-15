package datacontrol;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FileCreator {

    public FileCreator () {
        System.out.print("Creating Json File");
    }

/*    public void createJason() {
        JsonObject obj = new JsonObject();
        obj.put("Prova", "1");

        JsonArray array = new JsonArray(Arrays.asList("Pol", "Joan", "Paula"));
        obj.put("Creadors", array);
        File file = new File("resources/files/file.json");
        try(FileWriter files = new FileWriter(file)) {
            files.write(Jsoner.prettyPrint(obj.toJson()));
            files.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } */

    /*public void createRanking(String tipusranking) {
        JsonObject obj = new JsonObject();
        obj.put("Tipus ranking", tipusranking);

        File file = new File("resources/files/ranking/" + tipusranking + ".json");
        try(FileWriter files = new FileWriter(file)) {
            files.write(Jsoner.prettyPrint(obj.toJson()));
            files.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("File Created");
    } */
}
