import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class CollectionsInterfacesOnly {

    private List<String> names;
    private Map<String, Integer> counts;

    public CollectionsInterfacesOnly() {
        this.names = new ArrayList<>();
        this.counts = new HashMap<>();
    }

    public List<String> getNames() {
        return names;
    }

    public void putCount(String key, int value) {
        counts.put(key, value);
    }
}
