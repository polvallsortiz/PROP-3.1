import domaincontrol.DomainCtrl;
import presentationcontrol.Index;
import presentationcontrol.ThreadBridge;
import presentationcontrol.Working;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws Exception {
        DomainCtrl dc = new DomainCtrl();
        Semaphore semwork = new Semaphore(0);
        Semaphore semfinished = new Semaphore(0);
        //Thread info = new Thread(new Working(semwork,semfinished));
        //Thread tb = new Thread(new ThreadBridge(args,semwork,semfinished));
        //info.start();
        //tb.start();
        Index i = new Index();
        i.main(args);
    }
}


