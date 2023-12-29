package testData;

import data.Nif;
import exceptions.NotValidNifException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testNif {
    @Test
    void nifNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Nif(null);
        });

        String expectedMsg = "nif null";
        String actualMsg = exception.getMessage();

        assertTrue(actualMsg.contains(expectedMsg));
    }
    @Test
    void nifLength() {
        Exception exception0 = assertThrows(NullPointerException.class, () -> {
            new Nif("");
        });
        Exception exception1 = assertThrows(NotValidNifException.class, () -> {
            new Nif("123456");
        });
        Exception exception2 = assertThrows(NotValidNifException.class, () -> {
            new Nif("1234567890");
        });

        String expectedMsg = "no te suficients caràcters";
        String actualMsg0 = exception0.getMessage();
        String actualMsg1 = exception1.getMessage();
        String actualMsg2 = exception2.getMessage();

        assertTrue(actualMsg0.contains(expectedMsg));
        assertTrue(actualMsg1.contains(expectedMsg));
        assertTrue(actualMsg2.contains(expectedMsg));
    }
    @Test
    void nifNumberFormat() {
        Exception exception1 = assertThrows(NotValidNifException.class, () -> {
           new Nif("123abc78A");
        });
        Exception exception2 = assertThrows(NotValidNifException.class, () -> {
            new Nif("S12345678");
        });
        String expectedMsg = "8 primers caracters no son nombres";
        String actualMsg1 = exception1.getMessage();
        String actualMsg2 = exception2.getMessage();
        assertTrue(actualMsg1.contains(expectedMsg));
        assertTrue(actualMsg2.contains(expectedMsg));
    }
    @Test
    void nifCorrectLetter() {
        Exception exception1 = assertThrows(NotValidNifException.class, () -> {
           new Nif("12312323A");
        });
    }
    @Test
    void nifGood() {

    }
}
