package document_analysis;

import document_analysis.CustomThreadTypes.WriteThread;

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

    private String path;
    private int lineCount;

    public Document(){
        setupReader();
        fillDocumentArr();
        lineCount = 0;
    }

    public Document(String path){
        setupReader();
        fillDocumentArr();
        setupWriter(path);
        this.path = path;
    }

    private void setupWriter(String path) {
        try{
            this.fileWriter = new FileWriter(path);
            this.bufferedWriter = new BufferedWriter(fileWriter);

        }catch(IOException e){
            System.out.println("File does not exist");
        }
    }

    private void setupReader() {
        try{
            this.reader = new FileReader("res/docx.txt");
            this.bufferedReader = new BufferedReader(reader);

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

    public void writeResult(String result){
        try {
            this.fileWriter = new FileWriter(path,true);
            fileWriter.append(result+"\n");
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("no file");
        }
    }

    public void editDocument(WriteThread thread){
        if (lineCount < 100) {
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
