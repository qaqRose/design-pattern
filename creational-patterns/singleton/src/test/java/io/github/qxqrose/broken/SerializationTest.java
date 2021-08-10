package io.github.qxqrose.broken;

import io.github.qxqrose.example.Singleton;
import junit.framework.TestCase;

import java.io.*;

/**
 * @author: qiu
 * 2021/8/10
 *
 * 序列化破坏单例
 */
public class SerializationTest extends TestCase {

    public void testSingleton() {
        Singleton singleton = Singleton.getInstance();
        Singleton singleton1 = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(singleton);

            byte[] bytes = bos.toByteArray();
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            singleton1 = (Singleton) ois.readObject();

        } catch (Exception e) {}

        assertNotSame(singleton, singleton1);
    }
}
