package testData;

import data.VotingOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestVotingOption {
    VotingOption votingOption1;
    VotingOption votingOption2;
    VotingOption votingOption3;
    VotingOption votingOption4;
    VotingOption votingOption5;

    @BeforeEach
    void createVO(){
        votingOption1 = new VotingOption("Op1");
        votingOption2 = new VotingOption("Op2");
        votingOption3 = new VotingOption("Op1");
        votingOption4 = new VotingOption("");
        votingOption5 = new VotingOption(null);
    }
    @Test
    void testGetParty() {
        assertEquals("Op1", votingOption1.getParty());
        assertNotEquals("Op3", votingOption3.getParty());
        assertEquals("", votingOption4.getParty());
        assertNull(votingOption5.getParty());
    }
    @Test
    void testEquals() {
        //warnings due to java wanting to simplify, this equals is a function inside the class tested
        assertTrue(votingOption1.equals(votingOption1));
        assertTrue(votingOption1.equals(votingOption3));
        assertFalse(votingOption1.equals(votingOption2));
        assertFalse(votingOption4.equals(votingOption5));
    }
    @Test
    void testHash() {
        assertEquals(votingOption1.hashCode(), votingOption3.hashCode());
        assertEquals(votingOption2.hashCode(), votingOption2.hashCode());
        assertNotEquals(votingOption1.hashCode(), votingOption4.hashCode());
        assertNotEquals(votingOption4.hashCode(), votingOption5.hashCode());
    }
    @Test
    void testToString() {
        assertEquals("Vote option {" + "party='" + "Op1" + '\'' + '}', votingOption1.toString());
        assertEquals("Vote option {" + "party='" + "empty" + '\'' + '}', votingOption4.toString());
        assertEquals("Vote option {" + "party='" + "null" + '\'' + '}', votingOption5.toString());
    }
}
