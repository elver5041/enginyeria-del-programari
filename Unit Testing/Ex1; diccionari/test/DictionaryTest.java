import exceptions.NotDefinedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryTest {
    DictionaryImpl dict;

    @BeforeEach
    void start(){
        dict = new DictionaryImpl();
    }

    @Test
    void defineWordTest() throws NotDefinedException {
        List<String> a=new ArrayList<>();

        dict.defineWord("Banc", "Lloc on sentar-se");
        a.add("Lloc on sentar-se");
        assertEquals(a, dict.getDefinitions("Banc"));

        dict.defineWord("Banc", "Lloc per guardar diners");
        a.add("Lloc per guardar diners");
        assertEquals(a, dict.getDefinitions("Banc"));
    }

    @Test
    void getDefinitionsTest() throws NotDefinedException {
        List<String> a=new ArrayList<>();
        assertThrows(NotDefinedException.class, () -> dict.getDefinitions("Cogombre"));

        dict.defineWord("Banc", "Lloc on sentar-se");
        a.add("Lloc on sentar-se");
        assertEquals(a, dict.getDefinitions("Banc"));

        dict.defineWord("Banc", "Lloc per guardar diners");
        a.add("Lloc per guardar diners");
        assertEquals(a, dict.getDefinitions("Banc"));
    }
}
