package document_analysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.jar.JarOutputStream;

public class Document {

    private FileReader reader;
    private BufferedReader bufferedReader;

    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;

    private ArrayList<String> document = new ArrayList<String>();
    private ArrayList<String> list = new ArrayList<>();

    private int wordCount;
    private int lineCount;
    private int charCount;
    private int listLineCount;

    public Document(){
        setupReaderAndWriter();
        fillDocumentArr();
        printContent();
        listLineCount = 1;
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

    public void printList(){
        for (String string : list) {
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

    public void editDocument(CustomThread thread){
        if (listLineCount <= 2000) {
            list.add(thread.getName() + " added line number: " + listLineCount);
            listLineCount++;
        }
        else
            thread.stopThread();
    }

    public ArrayList<String> getDocument() {
        return document;
    }
        
}
