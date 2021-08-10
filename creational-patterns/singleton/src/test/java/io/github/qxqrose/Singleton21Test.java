package io.github.qxqrose;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author: qiu
 * 2021/8/8
 */
public class Singleton21Test extends TestCase {


    public void testInitializing() {
        Singleton21.simple();
        Singleton21.getInstance();
    }

    public void testConcurrentGetInstance() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<Singleton21> callable = Singleton21::getInstance;
        Set<Singleton21> set = new HashSet<>();

        // 并发数
        final int n = 5;

        Future<Singleton21>[] futures = new Future[n];
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