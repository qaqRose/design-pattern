package io.github.qxqrose;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author: qiu
 * 2021/8/8
 */
public class Singleton1Test extends TestCase {

    public void testGetInstance() {
        Singleton1 instance1 = Singleton1.getInstance();
        Singleton1 instance2 = Singleton1.getInstance();
        assertEquals(instance1, instance2);
    }

    public void testConcurrentGetInstance() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<Singleton1> callable = Singleton1::getInstance;
        Set<Singleton1> set = new HashSet<>();

        // 并发数
        final int n = 5;

        Future<Singleton1>[] futures = new Future[n];
        for (int i = 0; i < n; i++) {
            futures[i] = executorService.submit(callable);
        }

        for (int i = 0; i < n; i++) {
            while(!futures[i].isDone()) {}
            try {
                set.add(futures[i].get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        assertEquals(1, set.size());
    }
}