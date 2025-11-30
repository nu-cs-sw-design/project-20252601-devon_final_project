import java.util.ArrayList;
import java.util.HashMap;

public class ConcreteCollectionsField {

    private ArrayList<String> names = new ArrayList<>();
    private HashMap<String, Integer> counts = new HashMap<>();

    public void addName(String name) {
        names.add(name);
    }

    public void putCount(String key, int value) {
        counts.put(key, value);
    }
}
