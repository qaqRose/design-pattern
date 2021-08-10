package io.github.qxqrose.example;

/**
 * @author: qiu
 * 2021/8/9
 *
 * 双重检锁
 */
public class DoubleCheckedSingleton {

    private DoubleCheckedSingleton() {}

    /**
     * 声明为volatile
     * 禁止指令重排序
     * 变量可见性
     */
    private static volatile DoubleCheckedSingleton INSTANCE = null;

    public static DoubleCheckedSingleton getInstance() {
        if(INSTANCE == null) {
            synchronized (DoubleCheckedSingleton.class) {
                if(INSTANCE == null)  {
                    INSTANCE = new DoubleCheckedSingleton();
                }
            }
        }
        return INSTANCE;
    }
}
