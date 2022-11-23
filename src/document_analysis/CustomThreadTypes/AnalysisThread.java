package document_analysis.CustomThreadTypes;

import document_analysis.DocOperations;

import java.util.concurrent.Semaphore;

public class AnalysisThread extends Thread{

    private final DocOperations docOperations;
    private final int operationNum;
    Semaphore sem;

    public AnalysisThread(String name, DocOperations docOperations,int operationNum,Semaphore sem){
        this.docOperations = docOperations;
        setName(name);
        this.operationNum = operationNum;
        this.sem = sem;
    }

    public void run(){
        try {
            sleep(200);
            sem.acquire();
            switch (operationNum) {
                case 0 -> docOperations.document.writeResult(this.getName() + ": linecount = " + docOperations.getLineCount());
                case 1 -> docOperations.document.writeResult(this.getName() + ": wordcount = " + docOperations.getWordCount());
                case 2 -> docOperations.document.writeResult(this.getName() + ": charcount with spaces = " + docOperations.getCharacterCountWithSpaces());
                default -> docOperations.document.writeResult(this.getName() + ": charcount without spaces = " + docOperations.getCharacterCountNoSpaces());
            }
        } catch (InterruptedException e) {
            System.out.println("Modification exception");
        }
        finally {
            sem.release();
        }
    }

}
