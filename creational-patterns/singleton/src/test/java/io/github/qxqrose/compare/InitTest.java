package io.github.qxqrose.compare;

import io.github.qxqrose.example.Singleton;
import io.github.qxqrose.example.SyncLazySingleton;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: qiu
 * 2021/8/8
 *
 * 耗时比较
 */
public class InitTest {

    public static void main(String[] args) {
        int n = 1000 * 10000;
        long s,e;
        s = System.currentTimeMillis();

        // 避免java优化
        Set<Singleton> set1 = new HashSet<>();
        Set<SyncLazySingleton> set2= new HashSet<>();

        for (int i = 0; i < n; i++) {
            set1.add(Singleton.getInstance());
        }
        e = System.currentTimeMillis();
        System.out.println("耗时：" + (e-s) );

        s = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            set2.add(SyncLazySingleton.getInstance());
        }
        e = System.currentTimeMillis();
        System.out.println("耗时：" + (e-s) );

    }
}
