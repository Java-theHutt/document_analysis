package document_analysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Document {

    private FileReader reader;
    private BufferedReader bufferedReader;

    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;

    private final ArrayList<String> document = new ArrayList<>();

    private int wordCount;
    private int charCount;
    private int lineCount;

    public Document(){
        setupReaderAndWriter();
        fillDocumentArr();
        lineCount = 0;
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
        for (String string : document) {
            System.out.println(string);
        }
        System.out.println("Linecount is " + lineCount);
    }


    public void fillDocumentArr() {
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {   
                document.add(line);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void editDocument(WriteThread thread){
        if (lineCount < 2000) {
            document.add(thread.getName() + " added line number: " + (lineCount +1));
            lineCount++;
        }
        else
            thread.stopThread();
    }

    public ArrayList<String> getDocument() {
        return document;
    }
        
}
