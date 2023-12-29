package testData;

import data.Nif;
import exceptions.NotValidNifException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testNifCreation {
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
    void nifEmpty() {
        Exception exception0 = assertThrows(NotValidNifException.class, () -> {
            new Nif("");
        });

        String expectedMsg = "no te suficients caràcters";
        String actualMsg0 = exception0.getMessage();

        assertTrue(actualMsg0.contains(expectedMsg));
    }
    @Test
    void nifWrongLength() {
                Exception exception1 = assertThrows(NotValidNifException.class, () -> {
            new Nif("123456");
        });
        Exception exception2 = assertThrows(NotValidNifException.class, () -> {
            new Nif("1234567890");
        });

        String expectedMsg = "no te suficients caràcters";
        String actualMsg1 = exception1.getMessage();
        String actualMsg2 = exception2.getMessage();

        assertTrue(actualMsg1.contains(expectedMsg));
        assertTrue(actualMsg2.contains(expectedMsg));
    }
    @Test
    void nifWrongNumberFormat() {
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
    void nifLetterInvalid() {
        Exception exception1 = assertThrows(NotValidNifException.class, () -> {
           new Nif("12312323I");
        });
        Exception exception2 = assertThrows(NotValidNifException.class, () -> {
            new Nif("12312323U");
        });
        Exception exception3 = assertThrows(NotValidNifException.class, () -> {
            new Nif("12312323O");
        });

        String expectedMsg = "nif invalid";
        String actualMsg1 = exception1.getMessage();
        String actualMsg2 = exception2.getMessage();
        String actualMsg3 = exception3.getMessage();

        assertTrue(actualMsg1.contains(expectedMsg));
        assertTrue(actualMsg2.contains(expectedMsg));
        assertTrue(actualMsg3.contains(expectedMsg));
    }
    @Test
    void nifLetterIncorrect() {
        Exception exception1 = assertThrows(NotValidNifException.class, () -> {
            new Nif("12345678A");
        });
        Exception exception2 = assertThrows(NotValidNifException.class, () -> {
            new Nif("56788765F");
        });

        String expectedMsg = "nif invalid";
        String actualMsg1 = exception1.getMessage();
        String actualMsg2 = exception2.getMessage();

        assertTrue(actualMsg1.contains(expectedMsg));
        assertTrue(actualMsg2.contains(expectedMsg));
    }
    @Test
    void nifWellDone() throws NotValidNifException{
        assertDoesNotThrow(()-> {
            new Nif("12345678Z");
        });
        assertDoesNotThrow(() -> {
            new Nif("65899632D");
        });
        assertDoesNotThrow(() -> {
            new Nif("48255629L");
        });
    }
}
