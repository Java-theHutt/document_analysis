package document_analysis;

public class DocOperations {
    public Document document;

    public DocOperations() {
        this.document = new Document();
        /*System.out.println(getWordCount());
        System.out.println(getLineCount());
        System.out.println(getCharacterCountWithSpaces());
        System.out.println(getCharacterCountNoSpaces());
         */

    }

    public int getWordCount() {
        String[] words;
        int wordCount = 0;

        for (String string : document.getDocument()) {
            words = string.split(" ");
            wordCount += words.length;
        }

        return wordCount;
    }

    public int getLineCount() {
        return document.getDocument().size();
    }

    public int getCharacterCountWithSpaces() {
        int charCount = 0;

        for (String string: document.getDocument()) {
            charCount += string.chars().count();
        }
        
        return charCount;
    }

    public int getCharacterCountNoSpaces() {
        int charCount = 0;
        String[] words;

        for (String string: document.getDocument()) {
            words = string.split(" ");
            for (String word : words) {
                charCount += word.chars().count();   
            }
        }
        
        return charCount;
    }

}