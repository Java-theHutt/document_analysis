package document_analysis;

import java.util.concurrent.Semaphore;

public class WriteThread extends Thread{

    //Each thread will stop when stopFlag is set to false.
    public volatile boolean stopFlag = true;
    private final DocOperations docOperations;
    Semaphore sem;
    boolean withConcurrency;

    public WriteThread(String name, DocOperations docOperations, Semaphore sem, boolean withConcurrency){
        this.docOperations = docOperations;
        setName(name);
        this.sem = sem;
        this.withConcurrency = withConcurrency;
    }

    public void run(){
        if(withConcurrency){
        while(stopFlag) {
            try{
                sleep(20);
                sem.acquire(); //A thread must acuirre the semaphore / permit before it continues
                docOperations.document.editDocument(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                sem.release(); //After completing or not completing its task, a thread releases its permit.
            }
        }}
        else
            withoutConcurrencyRun();
    }

    private void withoutConcurrencyRun() {
        while(stopFlag) {
            try{
                sleep(10);
                docOperations.document.editDocument(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopThread(){
        this.stopFlag = false;
    }

}
