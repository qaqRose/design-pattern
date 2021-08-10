package io.github.qxqrose;

/**
 * @author: qiu
 * 2021/8/8
 * 延迟初始化-懒汉式
 * (双重检锁)
 */
public class Singleton2 {

    private Singleton2() {
        //模拟初始化耗时操作
        long s = System.currentTimeMillis();
        long e = s + 2;
        while(e > System.currentTimeMillis()) {}
//        System.out.println("构造Singleton2实例--懒汉式(双重检锁)");
    }

    /**
     * 声明为volatile
     * 禁止指令重排序
     */
    private static Singleton2 INSTANCE = null;

    public static Singleton2 getInstance() {
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if(INSTANCE == null) {

//            System.out.println(Thread.currentThread().getId()  + " wait");
            synchronized (Singleton2.class) {
                if(INSTANCE == null)  {
                    INSTANCE = new Singleton2();
                }
            }
        }
        return INSTANCE;
    }
}
