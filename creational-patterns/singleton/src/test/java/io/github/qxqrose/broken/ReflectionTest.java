package io.github.qxqrose.broken;

import io.github.qxqrose.example.Singleton;
import junit.framework.TestCase;

import java.lang.reflect.Constructor;

/**
 * @author: qiu
 * 2021/8/10
 *
 * 反射破坏单例
 */
public class ReflectionTest extends TestCase {


    public void testSingleton() {
        Singleton singleton = Singleton.getInstance();
        Singleton singleton1 = null;
        try {
            Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor(null);
            constructor.setAccessible(true);
            singleton1 = constructor.newInstance();
        } catch (Exception e) {}

        assertNotSame(singleton, singleton1);
    }
}
