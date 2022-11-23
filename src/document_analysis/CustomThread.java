package document_analysis;

import java.util.concurrent.Semaphore;

public class CustomThread extends Thread{

    //Each thread will stop when stopFlag is set to false.
    public volatile boolean stopFlag = true;
    private final Document document;
    Semaphore sem;
    boolean withConcurrency;

    public CustomThread(String name, Document document, Semaphore sem,boolean withConcurrency){
        this.document = document;
        setName(name);
        this.sem = sem;
        this.withConcurrency = withConcurrency;
    }

    public void run(){
        if(withConcurrency){
        while(stopFlag) {
            try{
                sem.acquire(); //A thread must acuirre the semaphore / permit before it continues
                document.editDocument(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                sem.release(); //After completing or not completing its task, a thread releases its permit.
            }

            try{
                sleep(50);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }}
        else
            withoutConcurrencyRun();
    }

    private void withoutConcurrencyRun() {
        while(stopFlag) {
            document.editDocument(this);
        }
    }

    public void stopThread(){
        this.stopFlag = false;
    }

}
