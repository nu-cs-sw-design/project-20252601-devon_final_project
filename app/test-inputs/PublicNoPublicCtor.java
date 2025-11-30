public class PublicNoPublicCtor {

    private PublicNoPublicCtor() {
        // private constructor only
    }

    static PublicNoPublicCtor create() {
        return new PublicNoPublicCtor();
    }
}
