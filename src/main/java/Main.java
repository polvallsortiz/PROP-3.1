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
        Index i = new Index();
        i.main(args);

    }
}


