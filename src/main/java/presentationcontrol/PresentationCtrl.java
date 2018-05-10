package presentationcontrol;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class PresentationCtrl {
    private Vector<Vector<String>>  hidato= new Vector<>();
    private Character celltype;
    private String adjacencytype;
    private int lines;
    private int columns;

    //NEEDED FOR GUI
    private Stage stage;

    //NEEDED FOR GENERATOR
    private int holes;
    private int predefined;

    private String username;


    private void extract_data(String input) {
        List<String> data = Arrays.asList(input.split(","));
        celltype = data.get(0).charAt(0);
        adjacencytype = data.get(1);
        lines = Integer.parseInt(data.get(2));
        columns = Integer.parseInt(data.get(3));
    }

    private Vector<String> extract_line(String input) {
        Vector<String> aux = new Vector<>();
        List<String> data = Arrays.asList(input.split(","));
        for(int i = 0; i < columns; ++i) {
            aux.add(data.get(i));
        }
        return aux;
    }

    private void extract_data_generator(String input) {
        List<String> data = Arrays.asList(input.split(","));
        celltype = data.get(0).charAt(0);
        adjacencytype = data.get(1);
        lines = Integer.parseInt(data.get(2));
        columns = Integer.parseInt(data.get(3));
        holes = Integer.parseInt(data.get(4));
        predefined = Integer.parseInt(data.get(5));
    }

    private int matrix_generator(String input) {
        for(int i = 0; i < lines; ++i) {
            String auxiliar[] = new String[columns];
            Arrays.fill(auxiliar,"?");
            hidato.add(new Vector<String>(Arrays.asList(auxiliar)));
        }
        if(input != "-1") {
            List<String> data = Arrays.asList(input.split(","));
            int hast = data.size();
            if (lines * columns < (hast + holes + predefined)) return 0;
            for (String actual : data) {
                int act = Integer.parseInt(actual);
                Vector<String> mod = hidato.get(act / lines);
                mod.set(act % lines, "#");
                hidato.set(act / lines, mod);
            }
        }
        else if (lines * columns < (holes + predefined)) return 0;
        return 1;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void gui(String args[]) throws Exception {
        stage.close();
        System.out.println(username);
        stage = new Stage();

        Menu menu = new Menu(username,stage);
    }

}
