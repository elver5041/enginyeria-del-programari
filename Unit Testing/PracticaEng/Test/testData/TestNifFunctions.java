package testData;

import data.Nif;
import exceptions.NotValidNifException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestNifFunctions {
    Nif nif1;
    Nif nif2;
    Nif nif3;

    @BeforeEach
    void createNif() throws NotValidNifException {
        nif1 = new Nif("12345678Z");
        nif2 = new Nif("65899632D");
        nif3 = new Nif("12345678Z");
    }
    @Test
    void testEqualsSame() {
        //warnings due to java wanting to simplify, this equals is a function inside the class tested
        assertTrue(nif1.equals(nif1));
        assertTrue(nif2.equals(nif2));
    }
    @Test
    void testEqualsNull() {
        //warnings due to java wanting to simplify, this equals is a function inside the class tested
        assertFalse(nif1.equals(null));
        assertFalse(nif2.equals(null));
    }
    @Test
    void testEquals() {
        //warnings due to java wanting to simplify, this equals is a function inside the class tested
        assertTrue(nif1.equals(nif3));
    }
    @Test
    void testNotEquals() {
        //warnings due to java wanting to simplify, this equals is a function inside the class tested
        assertFalse(nif1.equals(nif2));
        assertFalse(nif2.equals(nif3));
    }
    @Test
    void testHashCode() {
        assertEquals(nif1.hashCode(), nif3.hashCode());
        assertNotEquals(nif1.hashCode(), nif2.hashCode());
    }
    @Test
    void testToString() {
        assertEquals("Nif {nif='12345678Z'}", nif1.toString());
        assertEquals("Nif {nif='65899632D'}", nif2.toString());
    }
}
