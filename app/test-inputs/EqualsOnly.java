public class EqualsOnly {

    private int value;

    public EqualsOnly(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof EqualsOnly)) return false;
        EqualsOnly other = (EqualsOnly) obj;
        return this.value == other.value;
    }
}
