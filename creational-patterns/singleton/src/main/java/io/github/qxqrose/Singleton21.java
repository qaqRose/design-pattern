package io.github.qxqrose;

/**
 * @author: qiu
 * 2021/8/8
 * 延迟初始化-懒汉式
 * （静态内部类  一种简单优雅的做法）
 */
public class Singleton21 {

    private Singleton21() {
        //模拟初始化耗时操作
        long s = System.currentTimeMillis();
        long e = s + 1000;
        while(e > System.currentTimeMillis()) {}
        System.out.println("构造Singleton21实例--懒汉式(静态内部类)");
    }

    public static Singleton21 getInstance() {
        return HelperSingleton11.singleton21;
    }

    /**
     * 定义一个静态内部类
     * java的语义可以保证在引用该字段之前，不会去初始化这个字段
     */
    static class HelperSingleton11 {
        static Singleton21 singleton21 = new Singleton21();
    }

    /**
     * 增加一个方法
     * 测试没有引用静态字段的时候，java 是否会进行初始化
     */
    public static void simple() {
        System.out.println("simple");
    }
}
