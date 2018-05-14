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

    //NEEDED FOR GENERATOR
    private int holes;
    private int predefined;

    private String username;

    public PresentationCtrl(Integer lines, Integer columns, Integer holes, Integer predefined, String adjacencytype, Character celltype) {
        this.lines = lines;
        this.columns = columns;
        this.holes = holes;
        this.predefined = predefined;
        this.adjacencytype = adjacencytype;
        this.celltype = celltype;
    }

    public Vector<Vector<String>> matrix_generator_GUI() {
        for(int i = 0; i < lines; ++i) {
            String auxiliar[] = new String[columns];
            Arrays.fill(auxiliar,"?");
            hidato.add(new Vector<String>(Arrays.asList(auxiliar)));
        }
        return hidato;
    }

    /*private int matrix_generator(String input) {
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
*/

}
