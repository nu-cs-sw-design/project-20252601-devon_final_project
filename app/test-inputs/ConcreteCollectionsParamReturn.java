import java.util.ArrayList;
import java.util.HashMap;

public class ConcreteCollectionsParamReturn {

    public ArrayList<String> getNames() {
        return new ArrayList<>();
    }

    public void addAll(HashMap<String, Integer> map) {
        // no-op
    }
}
