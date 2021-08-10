package io.github.qxqrose.example;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author: qiu
 * 2021/8/10
 */
public class EnumSingletonTest extends TestCase {

    public void testInstance() {
//        EnumSingleton.INSTANCE.func();
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        assertEquals(instance1, instance2);
    }

    public void testConcurrentInstance() {

        // 并发数
        final int n = 10;

        ExecutorService executorService = Executors.newFixedThreadPool(n);
        Callable<EnumSingleton> callable = () -> EnumSingleton.INSTANCE;
        Set<EnumSingleton> set = new HashSet<>();

        Future<EnumSingleton>[] futures = new Future[n];
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