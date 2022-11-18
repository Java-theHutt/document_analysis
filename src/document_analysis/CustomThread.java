package document_analysis;

public class CustomThread extends Thread{

    private Document document;

    public CustomThread(String name, Document document){
        this.document = document;
        setName(name);
    }

    public void run(){
        editDocument();
    }

    private void editDocument() {

    }

}
