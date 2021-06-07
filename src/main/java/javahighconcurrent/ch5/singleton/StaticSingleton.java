package javahighconcurrent.ch5.singleton;

/**
 * 书中认为的单例模式的最优实现方案
 *
 * @author zdw
 */
public class StaticSingleton {


    private StaticSingleton() {
        System.out.println("StaticSingleton is created!");
    }

    public static StaticSingleton getInstance() {
        //只有在第一次调用getInstance()方法才会创建StaticSingleton的实例
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {

        StaticSingleton.getInstance();
        //  StaticSingleton.getInstance();
        // StaticSingleton.getInstance();
    }

    /**
     * 方法声明private,使得我们外部不能轻易访问它
     * 利用了虚拟机的类初始化机制创建单例
     */
    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }

}
