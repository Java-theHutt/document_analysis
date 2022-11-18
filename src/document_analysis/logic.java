package document_analysis;

import java.util.Objects;

public class logic {

    private final ClassLoader classLoader = getClass().getClassLoader();

    private CustomThread[] threads = new CustomThread[20];
    private Document document = null;

    public logic(){
        this.document = new Document(setFile());
    }

    public void setThreads(){
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new CustomThread("thread" + (i+1),document);
        }
    }

    public void startThreads(){
        for (Thread t : threads){
            t.start();
        }
    }

    public String setFile(){
        return classLoader.getResource("res") + "/" + "docx.txt";
    }

}
