package mt.singleton;

public class Pugh {
    private Pugh() {
        // private constructor prevents direct instantiation
    }

    private static class Holder {
        private static final Pugh INSTANCE = new Pugh();
    }

    public static Pugh getInstance() {
        return Holder.INSTANCE;
    }
}
