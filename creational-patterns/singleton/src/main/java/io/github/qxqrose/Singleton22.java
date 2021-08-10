package io.github.qxqrose;

/**
 * @author: qiu
 * 2021/8/8
 * 延迟初始化-懒汉式
 * （方法同步锁 简单，但效率低）
 */
public class Singleton22 {

    private Singleton22() {
        //模拟初始化耗时操作
        long s = System.currentTimeMillis();
        long e = s + 1000;
        while(e > System.currentTimeMillis()) {}
        System.out.println("构造Singleton22实例--懒汉式(方法同步锁)");
    }

    private static Singleton22 INSTANCE = null;

    /**
     * 增加同步锁
     * 但是99%的情况是不需要同步的，比较浪费资源
     * @return
     */
    public static synchronized Singleton22 getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Singleton22();
        }
        return INSTANCE;
    }


}
