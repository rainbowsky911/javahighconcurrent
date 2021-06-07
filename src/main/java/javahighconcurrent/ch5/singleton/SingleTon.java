package javahighconcurrent.ch5.singleton;

/**
 *
 * @Auther: zdw
 * @Date: 2021/06/07/10:03
 * @Description:    单例模式
 *                  对于频繁使用的对象，可以省略new操作花费的时间。
 *                  由于new操作减少，对系统内存使用额外效率减低，缩短GC停顿时间。
 */
public class SingleTon {

    public SingleTon(){
        System.out.println("singleton is create");
    }
    private static SingleTon instance = new SingleTon();
    public  static SingleTon getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        SingleTon.getInstance();

    }
}
