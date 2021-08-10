package io.github.qxqrose;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author: qiu
 * 2021/8/8
 */
public class NonSafeSingletonTest extends TestCase {

    public void testConcurrentGetInstance() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<NonSafeSingleton> callable = NonSafeSingleton::getInstance;
        Set<NonSafeSingleton> set = new HashSet<>();

        // 并发数
        final int n = 5;

        Future<NonSafeSingleton>[] futures = new Future[n];
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

        assertTrue(1 !=set.size());
    }
}