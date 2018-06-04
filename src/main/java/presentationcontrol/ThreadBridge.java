package presentationcontrol;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class ThreadBridge extends Thread {

    String[] args;
    PresentationCtrl pc;
    Semaphore semwork,semfinished;

    @Override
    public void run(){
        System.out.print("THREAD JAVAFX");
        Index i = new Index();
        i.setsemaphores(semwork,semfinished);
        i.main(args);
    }

    public ThreadBridge(String[] args, Semaphore semwork, Semaphore semfinished) {
        this.args = args;
        this.semfinished = semfinished;
        this.semwork = semwork;
    }
}
