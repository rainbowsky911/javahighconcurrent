package javahighconcurrent.ch5.singleton;

/**
 * @author zdw
 * 一种支持延迟加载的策略,它只会在instance第一次使用时创建，
 */
public class LazySingleton {

    public LazySingleton() {
        System.out.println("LazySingleton is created");
    }

    private static LazySingleton instance = null;

    /**
     * 为了对象被多次创建,需要使用synchronizes进行同步
     * 作者并不推荐使用双重检查的形式写单例，比较复杂和丑陋。
     */
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        LazySingleton.getInstance();
        LazySingleton.getInstance();
        LazySingleton.getInstance();
    }
}
