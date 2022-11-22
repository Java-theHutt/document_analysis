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

    private ArrayList<String> document = new ArrayList<String>();

    private int wordCount;
    private int lineCount;
    private int charCount;

    public Document(){
        setupReaderAndWriter();
        fillDocumentArr();
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
        for (String string : document) {
            System.out.println(string);
        }
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

    public void editDocument(){
        //appends text in doc
        //printWriter.println("something something")
    }

    public ArrayList<String> getDocument() {
        return document;
    }
        
}
