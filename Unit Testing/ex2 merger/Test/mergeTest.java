import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class mergeTest {

    merger Merge;
    List<Integer> emptyList;
    List<Integer> repeatedNum;
    List<Integer> decreasingNum;
    List<Integer> decreasingRep;
    List<Integer> correctList;


    @BeforeEach
    public void init(){
        List<Integer> emptyList = Collections.emptyList();
        List<Integer> repeatedNum = Arrays.asList(1,1,3,5,6);
        List<Integer> decreasingNum = Arrays.asList(4,3,1,2,3);
        List<Integer> decreasingRep = Arrays.asList(4,4,3,2,1);
        List<Integer> correctList = Arrays.asList(1,2,3,4,5);
    }

    @Test
    public void correctMerge(){
        assertEquals(Merge.mergeSorted(correctList, correctList), correctList);
    }

    @Test
    public void decreasingMerge(){
        assertThrows(IllegalArgumentException.class, () -> Merge.mergeSorted(correctList, decreasingRep));
    }
    @Test
    public void repeatedMerge(){
        assertThrows(IllegalArgumentException.class, () -> Merge.mergeSorted(correctList, repeatedNum));
    }
    @Test
    public void repeatedNDecreasing(){
        assertThrows(IllegalArgumentException.class, () -> Merge.mergeSorted(correctList, decreasingRep));
    }

}

































