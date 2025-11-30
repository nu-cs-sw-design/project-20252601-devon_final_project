public class BothEqualsAndHashCode {

    private int id;
    private String label;

    public BothEqualsAndHashCode(int id, String label) {
        this.id = id;
        this.label = label;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BothEqualsAndHashCode)) return false;
        BothEqualsAndHashCode other = (BothEqualsAndHashCode) obj;
        if (id != other.id) return false;
        if (label == null) return other.label == null;
        return label.equals(other.label);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(id);
        result = 31 * result + (label == null ? 0 : label.hashCode());
        return result;
    }
}
