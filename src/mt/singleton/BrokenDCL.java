package mt.singleton;

// Don't do this - double-checked locking
public class BrokenDCL {
    private static BrokenDCL instance;

    private BrokenDCL() {
        // private constructor prevents direct instantiation
    }

    public static BrokenDCL getInstance() {
        if (instance == null) {
            synchronized (BrokenDCL.class) {
                if (instance == null) {
                    instance = new BrokenDCL();
                }
            }
        }
        return instance;
    }
}
