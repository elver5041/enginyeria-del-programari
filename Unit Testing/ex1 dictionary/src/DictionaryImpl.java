import exceptions.NotDefinedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DictionaryImpl implements Dictionary {
    private final HashMap<String,List<String>> dictionary = new HashMap<>();

    @Override
    public void defineWord(String word, String definition) {
        List<String> entry = dictionary.get(word);
        if (entry==null) {
            entry = new ArrayList<>();
        }
        entry.add(definition);
        dictionary.put(word, entry);
    }

    @Override
    public List<String> getDefinitions(String word) throws NotDefinedException {
        List<String> entry = dictionary.get(word);
        if (entry==null) throw new NotDefinedException(word + " is not in the dictionary.");
        return entry;
    }
}
