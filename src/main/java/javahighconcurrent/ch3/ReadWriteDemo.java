package javahighconcurrent.ch3;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteDemo {


    private static final ReentrantLock lock = new ReentrantLock();
    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();


}
