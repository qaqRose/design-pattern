package io.github.qxqrose;

/**
 * @author: qiu
 * 2021/8/8
 * 最简单单例-饿汉式
 */
public class Singleton1 {

    private Singleton1() {
        //模拟初始化耗时操作
        long s = System.currentTimeMillis();
        long e = s + 1000;
        while(e > System.currentTimeMillis()) {}
        System.out.println("构造Singleton1实例--饿汉式");
    }

    private static Singleton1 INSTANCE = new Singleton1();

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}
