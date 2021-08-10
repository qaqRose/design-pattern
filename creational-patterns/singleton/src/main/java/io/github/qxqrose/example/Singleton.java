package io.github.qxqrose.example;

/**
 * @author: qiu
 * 2021/8/8
 */
public final class Singleton {

    private Singleton() {}

    private static Singleton INSTANCE = new Singleton();

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
