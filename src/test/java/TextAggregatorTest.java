import com.textagg.controller.TextAggregator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.textagg.utility.CountUtility;
import com.textagg.utility.Counting;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TextAggregatorTest {

    public List<String> initTestList(){
        List<String> inputList = new ArrayList<>();
        inputList.add("Test");
        inputList.add("For");
        inputList.add("For");
        inputList.add("Duplicate");
        inputList.add("Duplicates");
        inputList.add("Test");
        inputList.add("For");
        inputList.add("For");
        inputList.add("500");
        return inputList;
    }

    @Test
    public void testIfFileRead() {
        CountUtility cntObj = new CountUtility();
        String[] inStr = {"" , "txtinput.txt"};
        assertTrue(cntObj.parseInput(inStr).size() > 0);
    }

    @Test
    public void testUniqueWords() {
        Counting cntObj = new Counting();
        assertEquals(4, cntObj.countUniqueWords(initTestList()).get("For"));
    }

    @Test
    public void testNumberCounts() {
        Counting cntObj = new Counting();
        assertEquals("0->2,5->1", cntObj.countUniqueNumbers(initTestList()).get("500"));
    }

    @Test
    public void testUniquePhrases() {
        Counting cntObj = new Counting();
        assertEquals(2, cntObj.countUniquePhrases(initTestList()).get("Test For For"));
    }


}
