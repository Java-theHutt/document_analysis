package document_analysis.CustomThreadTypes;

import document_analysis.DocOperations;

import java.util.concurrent.Semaphore;

public class ReadThread extends Thread{

    private final DocOperations docOperations;
    private final int operationNum;

    public ReadThread(String name, DocOperations docOperations,int operationNum){
        this.docOperations = docOperations;
        setName(name);
        this.operationNum = operationNum;
    }

    public void run(){
        try {
            sleep(200);
            switch (operationNum) {
                case 0 -> System.out.println(this.getName() + ": linecount = " + docOperations.getLineCount());
                case 1 -> System.out.println(this.getName() + ": wordcount = " + docOperations.getWordCount());
                case 2 -> System.out.println(this.getName() + ": charcount with spaces = " + docOperations.getCharacterCountWithSpaces());
                default -> System.out.println(this.getName() + ": charcount without spaces = " + docOperations.getCharacterCountNoSpaces());
            }
        } catch (InterruptedException e) {
            System.out.println("Modification exception");
        }
    }
}
