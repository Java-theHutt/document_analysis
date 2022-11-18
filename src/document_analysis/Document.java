package document_analysis;

import javax.print.Doc;
import java.io.*;

public class Document {

    private FileReader reader;
    private BufferedReader bufferedReader;

    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;

    private int wordCount;
    private int lineCount;
    private int charCount;

    public Document(){
        setupReaderAndWriter();
        printContent();
    }

    private void setupReaderAndWriter() {
        try{
            this.reader = new FileReader("res/docx.txt");
            this.bufferedReader = new BufferedReader(reader);

            this.fileWriter = new FileWriter("res/docx.txt",true);
            this.bufferedWriter = new BufferedWriter(fileWriter);
            this.printWriter = new PrintWriter(bufferedWriter);

        }catch(IOException e){
            System.out.println("File does not exist");
        }
    }

    public void printContent(){
        String line;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public synchronized void editDocument(){

    }

}
