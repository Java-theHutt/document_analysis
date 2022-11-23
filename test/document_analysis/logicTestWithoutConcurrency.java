package document_analysis;

import org.junit.Test;

import static org.junit.Assert.*;

public class logicTestWithoutConcurrency {

    @Test
    public void withoutConcurrency(){
        logic logic = new logic(false);
    }

}