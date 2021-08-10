package io.github.qxqrose;

/**
 * @author: qiu
 * 2021/8/8
 *
 * 双重检锁测试
 * 代码来自  http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckTest.java
 *
 * 链接：http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html
 * 在高版本jdk(jdk5以上)无法实验双重检锁被破坏的结果
 *
 */
public class DoubleCheckTest
{


    // static data to aid in creating N singletons
    /**
     * 固定的值，给单例类初始化
     */
    static final Object dummyObject = new Object(); // for reference init
    static final int A_VALUE = 256; // value to initialize 'a' to
    static final int B_VALUE = 512; // value to initialize 'b' to
    static final int C_VALUE = 1024;

    /**
     * 对象数组
     * 每个数组元素 引用一个 单例类
     */
    static ObjectHolder[] singletons;  // array of static references
    static Thread[] threads; // array of racing threads

    /**
     * 多个线程去创建单例
     */
    static int threadCount; // number of threads to create
    /**
     * 创建多少个单例
     */
    static int singletonCount; // number of singletons to create


    static volatile int recentSingleton;


    // I am going to set a couple of threads racing,
    // trying to create N singletons. Basically the
    // race is to initialize a single array of
    // singleton references. The threads will use
    // double checked locking to control who
    // initializes what. Any thread that does not
    // initialize a particular singleton will check
    // to see if it sees a partially initialized view.
    // To keep from getting accidental synchronization,
    // each singleton is stored in an ObjectHolder
    // and the ObjectHolder is used for
    // synchronization. In the end the structure
    // is not exactly a singleton, but should be a
    // close enough approximation.
    //


    // This class contains data and simulates a
    // singleton. The static reference is stored in
    // a static array in DoubleCheckFail.

    /**
     * 单例内部类
     * 有4个属性， 每个属性都有固定值（构造的时候去初始化）
     */
    static class Singleton
    {
        public int a;
        public int b;
        public int c;
        public Object dummy;

        public Singleton()
        {
            a = A_VALUE;
            b = B_VALUE;
            c = C_VALUE;
            dummy = dummyObject;
        }
    }

    /**
     * 检查单例类
     * 1. 检查属性是否初始化完成
     * @param s
     * @param index 表示第几个单例
     */
    static void checkSingleton(Singleton s, int index)
    {
        int s_a = s.a;
        int s_b = s.b;
        int s_c = s.c;
        Object s_d = s.dummy;
        if(s_a != A_VALUE)
            System.out.println("[" + index + "] Singleton.a not initialized " +
                    s_a);
        if(s_b != B_VALUE)
            System.out.println("[" + index
                    + "] Singleton.b not intialized " + s_b);

        if(s_c != C_VALUE)
            System.out.println("[" + index
                    + "] Singleton.c not intialized " + s_c);

        if(s_d != dummyObject)
            if(s_d == null)
                System.out.println("[" + index
                        + "] Singleton.dummy not initialized,"
                        + " value is null");
            else
                System.out.println("[" + index
                        + "] Singleton.dummy not initialized,"
                        + " value is garbage");
    }

    // Holder used for synchronization of
    // singleton initialization.
    static class ObjectHolder
    {
        public Singleton reference;
    }

    static class TestThread implements Runnable
    {
        public void run()
        {
            for(int i = 0; i < singletonCount; ++i)
            {
                ObjectHolder o = singletons[i];
                if(o.reference == null)
                {
                    synchronized(o)
                    {
                        if (o.reference == null) {
                            o.reference = new Singleton();
                            recentSingleton = i;
                        }
                        // shouldn't have to check singelton here
                        // mutex should provide consistent view
                    }
                }
                else {
                    checkSingleton(o.reference, i);
                    // 如果其他线程比较快，当前线程也加快速度
                    int j = recentSingleton-1;
                    if (j > i) i = j;
                }
            }
        }
    }

    public static void main(String[] args)
    {
//        if( args.length != 2 )
//        {
//            System.err.println("usage: java DoubleCheckFail" +
//                    " <numThreads> <numSingletons>");
//        }
        int nThread = 3;
        int count = 3;

        // read values from args
//        threadCount = Integer.parseInt(args[0]);
//        singletonCount = Integer.parseInt(args[1]);
        threadCount = nThread;
        singletonCount = count;

        // create arrays
        threads = new Thread[threadCount];
        singletons = new ObjectHolder[singletonCount];

        // fill singleton array
        for(int i = 0; i < singletonCount; ++i)
            singletons[i] = new ObjectHolder();

        // fill thread array
        for(int i = 0; i < threadCount; ++i)
            threads[i] = new Thread( new TestThread() );

        // start threads
        for(int i = 0; i < threadCount; ++i)
            threads[i].start();

        // wait for threads to finish
        for(int i = 0; i < threadCount; ++i)
        {
            try
            {
                System.out.println("waiting to join " + i);
                threads[i].join();
            }
            catch(InterruptedException ex)
            {
                System.out.println("interrupted");
            }
        }
        System.out.println("done");
    }
}


