public class HashCodeOnly {

    private String name;

    public HashCodeOnly(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name == null ? 0 : name.hashCode();
    }
}
