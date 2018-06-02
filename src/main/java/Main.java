import datacontrol.DataCtrl;
import domaincontrol.DomainCtrl;
import domaincontrol.Hidato;
import javafx.application.Application;
import javafx.stage.Stage;
import presentationcontrol.Index;
import presentationcontrol.PresentationCtrl;
import presentationcontrol.ThreadBridge;
import presentationcontrol.Working;

import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws Exception {
        DomainCtrl dc = new DomainCtrl();
        Semaphore semwork = new Semaphore(0);
        Semaphore semfinished = new Semaphore(0);
        Thread info = new Thread(new Working(semwork,semfinished));
        Thread tb = new Thread(new ThreadBridge(args,semwork,semfinished));
        info.start();
        tb.start();
    }
}


