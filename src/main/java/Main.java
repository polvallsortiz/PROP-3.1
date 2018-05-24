import datacontrol.DataCtrl;
import domaincontrol.DomainCtrl;
import javafx.application.Application;
import javafx.stage.Stage;
import presentationcontrol.Index;
import presentationcontrol.PresentationCtrl;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Main {

    public static void main(String[] args) throws IOException {
        DomainCtrl dc = new DomainCtrl();
        String home = new File(System.getProperty("user.home")).getAbsolutePath();
        Vector<Vector<String>> sol = dc.loadGame(home+"/HidatoPlayer/Games/hidao1.json");
        for (int x = 0; x < sol.size(); ++x) {
            for (int y = 0; y < sol.get(x).size(); ++y) {
                System.out.print(sol.get(x).get(y)+ " ");
            }
            System.out.print("\n");
        }

        Index i = new Index();
        i.main(args);

    }
}


