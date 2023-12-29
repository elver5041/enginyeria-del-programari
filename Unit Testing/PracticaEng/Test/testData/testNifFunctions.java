package testData;

import data.Nif;
import exceptions.NotValidNifException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testNifFunctions {
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
        assertTrue(nif1.equals(nif1));
        assertTrue(nif2.equals(nif2));
    }
    @Test
    void testEqualsNull() {
        assertFalse(nif1.equals(null));
        assertFalse(nif2.equals(null));
    }
    @Test
    void testEquals() {
        assertTrue(nif1.equals(nif3));
    }
    @Test
    void testNotEquals() {
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
