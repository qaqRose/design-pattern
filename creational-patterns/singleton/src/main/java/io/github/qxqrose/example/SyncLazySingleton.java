package io.github.qxqrose.example;

/**
 * @author: qiu
 * 2021/8/8
 * 延迟初始化-懒汉式
 * （方法同步锁 简单，但效率低）
 */
public class SyncLazySingleton {

    private SyncLazySingleton() {}

    private static SyncLazySingleton INSTANCE = null;

    public static synchronized SyncLazySingleton getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new SyncLazySingleton();
        }
        return INSTANCE;
    }


}
