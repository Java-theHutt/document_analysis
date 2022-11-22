package document_analysis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class logic {
    //private final CustomThread[] threads = new CustomThread[20];
    private final ExecutorService executor = Executors.newFixedThreadPool(20);
    public Document document = null;

    public logic(){
        this.document = new Document();
        Semaphore sem = new Semaphore(1);
        //setThreads(sem);
        startThreads(sem);
        executor.shutdown();
        while(!executor.isTerminated()){

        }
        document.printList();
    }

    /*public void setThreads(Semaphore sem){
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new CustomThread("thread" + (i+1),document,sem);
        }
    }*/

    /**
    Customthread now takes an argument for running with or without concurrency
     All threads are started without creating individual objects of each, with the help of executor service.
     */
    public void startThreads(Semaphore sem){
        for (int i = 0; i < 20; i++) {
            CustomThread thread = new CustomThread("thread" + (i+1),document,sem,false);
            executor.execute(thread);
        }
        /*for (Thread t : threads){
            executor.execute(t);
        }*/
    }

}
