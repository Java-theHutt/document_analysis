package document_analysis;

import document_analysis.CustomThreadTypes.AnalysisThread;
import document_analysis.CustomThreadTypes.ReadThread;
import document_analysis.CustomThreadTypes.WriteThread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class logic {

    private final ExecutorService executor = Executors.newFixedThreadPool(20);
    public DocOperations docOperations = null;
    private final ReadThread[] readThreads = new ReadThread[4];
    private final AnalysisThread[] analysisThread = new AnalysisThread[4];

    //For our document analysis program
    public logic(String analysis){
        Semaphore sem = new Semaphore(1);
        this.docOperations = new DocOperations("res/AnalysisResult.txt");
        createAnalysisThreads(sem);
        joinThreads(analysisThread);
        System.out.println("Analysis complete.. see AnalysisResult.txt");
    }

    //for general testing
    public logic(boolean withConcurrency){
        this.docOperations = new DocOperations();
        Semaphore sem = new Semaphore(1);
        startThreads(sem,withConcurrency);
        setReadThreads();
        executor.shutdown();

        while(!executor.isTerminated()){
            setReadThreads();
            joinThreads(readThreads);
            sleepMainThread();
        }

        docOperations.document.printContent();
        setReadThreads();
    }

    private void createAnalysisThreads(Semaphore sem) {
        for (int i = 0; i < analysisThread.length; i++) {
            analysisThread[i] = new AnalysisThread("read-thread" + (i+1),docOperations,i,sem);
        }
        for (AnalysisThread at : analysisThread){
            at.start();
        }
    }

    public void setReadThreads(){
        for (int i = 0; i < readThreads.length; i++) {
            readThreads[i] = new ReadThread("read-thread" + (i+1),docOperations,i);
        }
        for (ReadThread rt : readThreads){
            rt.start();
        }
    }

    public void joinThreads(Thread[] threads){
        try{
            threads[0].join();
            threads[1].join();
            threads[2].join();
            threads[3].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     Customthread now takes an argument for running with or without concurrency
     All threads are started without creating individual objects of each, with the help of executor service.
     */
    public void startThreads(Semaphore sem,boolean withConcurrency){
        for (int i = 0; i < 20; i++) {
            WriteThread thread = new WriteThread("write-thread" + (i+1), docOperations,sem,withConcurrency);
            executor.execute(thread);
        }
    }

    private void sleepMainThread() {
        Random rand = new Random();
        try {
            Thread.sleep(rand.nextInt(6) * 1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
