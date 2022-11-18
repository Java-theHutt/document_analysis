package document_analysis;

public class CustomThread extends Thread{

    //Each thread will stop when stopFlag is set to false.
    private volatile boolean stopFlag = true;
    private Document document;

    public CustomThread(String name, Document document){
        this.document = document;
        setName(name);
    }


    public void run(){
        while(stopFlag) {
            editDocument();
        }
    }

    public void stopThread(){
        this.stopFlag = false;
    }

    private void editDocument() {
        document.editDocument();
    }

}
