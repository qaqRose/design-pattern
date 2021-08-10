package io.github.qxqrose.example;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author: qiu
 * 2021/8/8
 */
public class SingletonTest extends TestCase {

    public void testGetInstance() {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        // 两个实例是否相等
        assertEquals(instance1, instance2);
    }

    /**
     * 并发测试
     * 使用线程池并发获取实例
     * 将实例放在set容器，如果set的大小不为1，说明单例不是线程安全的
     */
    public void testConcurrentGetInstance() throws ExecutionException, InterruptedException {
        // 并发数
        final int n = 5;

        ExecutorService executorService = Executors.newFixedThreadPool(n);
        // 获取实例
        Callable<Singleton> callable = Singleton::getInstance;
        Future<Singleton>[] futures = new Future[n];
        for (int i = 0; i < n; i++) {
            futures[i] = executorService.submit(callable);
        }

        Set<Singleton> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            while(!futures[i].isDone()) {}
            // 实例添加到集合
            set.add(futures[i].get());
        }

        // 集合元素只有一个，说明获取的实例都同一个
        assertEquals(1, set.size());
    }
}