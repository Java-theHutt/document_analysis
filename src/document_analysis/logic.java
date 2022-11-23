package document_analysis;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class logic {
    //private final CustomThread[] threads = new CustomThread[20];
    private final ExecutorService executor = Executors.newFixedThreadPool(20);
    public DocOperations docOperations = null;
    private ReadThread[] readThreads = new ReadThread[4];

    public logic(){
        this.docOperations = new DocOperations();
        Semaphore sem = new Semaphore(1);
        startThreads(sem);
        //setReadThreads();
        executor.shutdown();

        while(!executor.isTerminated()){
            setReadThreads();
            joinReadThreads();
            sleepMainThread();
        }

        docOperations.document.printContent();
        setReadThreads();
    }

    public void setReadThreads(){
        for (int i = 0; i < readThreads.length; i++) {
            readThreads[i] = new ReadThread("read-thread" + (i+1),docOperations,i);
        }
        for (ReadThread rt : readThreads){
            rt.start();
        }
    }

    public void joinReadThreads(){
        try{
            readThreads[0].join();
            readThreads[1].join();
            readThreads[2].join();
            readThreads[3].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     Customthread now takes an argument for running with or without concurrency
     All threads are started without creating individual objects of each, with the help of executor service.
     */
    public void startThreads(Semaphore sem){
        for (int i = 0; i < 20; i++) {
            WriteThread thread = new WriteThread("write-thread" + (i+1), docOperations,sem,false);
            executor.execute(thread);
        }
        /*for (Thread t : threads){
            executor.execute(t);
        }*/
    }

    private void sleepMainThread() {
        Random rand = new Random();
        try {
            Thread.sleep(rand.nextInt(6) * 500);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
