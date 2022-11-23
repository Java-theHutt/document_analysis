package document_analysis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class run {
    public static void main(String[] args) {

        //using the AnalysisResult thread this runs our main program
        //that is supposed to be a document analyser.
        logic logic = new logic("Analyse");

        //to further illustrate raceConditions we have created an excessive amount of threads
        //that each write to the same ArrayList. While the write is happening a read is also happening.
        //this happens using the write and read thread.
        //In the case with semaphores the threads will be printed out in order.
        //logic logicTestWithConcurrency = new logic(true);
        //in the case without the threads will be updated randomly
        //logic logicTestWithoutConcurrency = new logic(false);
    }
}
