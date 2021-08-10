package io.github.qxqrose;

/**
 * @author: qiu
 * 2021/8/8
 * 非线程安全 单例
 */
public class NonSafeSingleton {

    private NonSafeSingleton() {
        //模拟初始化耗时操作
        long s = System.currentTimeMillis();
        long e = s + 1000;
        while(e > System.currentTimeMillis()) {}
        System.out.println("构造非单例模式");
    }

    private static NonSafeSingleton INSTANCE = null;

    public static NonSafeSingleton getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new NonSafeSingleton();
        }
        return INSTANCE;
    }
}
