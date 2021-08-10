package io.github.qxqrose.example;

/**
 * @author: qiu
 * 2021/8/9
 */
public class LazyHolderSingleton {

    private LazyHolderSingleton() {}

    public static LazyHolderSingleton getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 定义一个静态内部类
     * java的语义可以保证在引用该字段之前，不会去初始化这个字段
     */
    static class Holder {
        static LazyHolderSingleton INSTANCE = new LazyHolderSingleton();
    }

}
