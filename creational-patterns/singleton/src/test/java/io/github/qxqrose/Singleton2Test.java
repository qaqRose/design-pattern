package io.github.qxqrose;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author: qiu
 * 2021/8/8
 */
public class Singleton2Test extends TestCase {

    public void testGetInstance() {
        Singleton2 instance1 = Singleton2.getInstance();
        Singleton2 instance2 = Singleton2.getInstance();
        assertEquals(instance1, instance2);
    }

    public void testConcurrentGetInstance() {

        // 并发数
        final int n = 20;

        ExecutorService executorService = Executors.newFixedThreadPool(n);
        Callable<Singleton2> callable = Singleton2::getInstance;
        Set<Singleton2> set = new HashSet<>();

        Future<Singleton2>[] futures = new Future[n];
        for (int i = 0; i < n; i++) {
            futures[i] = executorService.submit(callable);
        }

        for (int i = 0; i < n; i++) {
            while(!futures[i].isDone()) {}
            try {
                if(futures[i].get() == null) {
                    System.out.println("实例为空");
                }
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