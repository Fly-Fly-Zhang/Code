#####   特点：WeakHashMap添加的元素随着时间推移会减少；
####   java四种引用：

 - [ ] 强引用：一个对象如果只有强引用，那么垃圾回收器绝不会回收它，即使在内存不足的情况下，JVM宁愿抛出内存不足的异常都不会回收该对象；
  

```java
 String s=new String(""); //new的对象为强引用
 s= null; //强引用置为空会回收该对象；
```

 - [ ]  软引用： 如果一个对象只有软引用，那么在内存充足的情况下，垃圾回收器则不会回收它，如果内存不足，垃圾回收器会回收该对象；  Java中提供SoftReference 处理软引用；
  - [ ]  弱引用：弱引用所作用的对象，一旦发生垃圾回收，该引用所作用的对象就会被回收掉，弱引用相对于软引用，生命周期更短；   java中提供WeakReference处理弱引用；
 - [ ] 虚引用 ： 虚引用无法左右对象的生命周期 ； Java中提供PhantomReference 处理虚引用；

####   如何在WeakHashMap中存入各种引用对象，以及各种引用的应用场景

```java

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMap {
    public static void main(String[] args) {
       //weakHashMap普通插入元素以及迭代器应用；
        WeakHashMap <Integer, Integer> weakHashMap1 = new WeakHashMap <Integer, Integer>();
        weakHashMap1.put(1,11);
        weakHashMap1.put(2, 22);
        weakHashMap1.put(3, 33);
        Iterator <Map.Entry <Integer, Integer>> iterator = weakHashMap1.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry <Integer, Integer> entry = iterator.next();
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key+":"+value);
        }



        //创建一个软引用的集合，SoftReference<A>表明类A的对象为软引用；创建其它引用集合，依照此创建方法
        WeakHashMap<SoftReference<A>, String> weakHashMap = new WeakHashMap <SoftReference<A>, String>();

        ReferenceQueue<A> queue = new ReferenceQueue<A>(); //创建一个引用队列
        SoftReference<A> w = new SoftReference<A>(new A(), queue); 
        //创建一个软引用类对象，并将new A()对象置为软引用对象
        weakHashMap.put(w, "lll"); //将此软引用对象添加到集合中；



        //各种引用的调用函数
        softReferenceDemo(); 
        weakReferenceDemo();
        phantomReferenceDemo();

    }
    
    //创建一个7M的内存空间
    static class A{
        public A() {
            byte[] array = new byte[7*1024*1024];//7M
        }
    }



    //软引用
    public static void softReferenceDemo() {
        ReferenceQueue<A> queue = new ReferenceQueue<A>();
        SoftReference<A> w = new SoftReference<A>(new A(), queue);

        System.out.println(w.get()); //得到这个对象
        System.out.println(w.isEnqueued());//判断该对象是否是垃圾回收器标记即将回收的对象，如果是返回true;
        System.out.println(queue.poll());//删除集合中第一个元素如果为空则返回null
         /**
         * main.java.com.WeakHashMapGY02$A@1e3ac11b
         * false
         * null
         */

        /**
         * 当对象要被垃圾回收器回收的情况下，
         * 他会将该对象放到ReferenceQueue实例中。
         * 再次进行插入元素操作前会检测当前的队列中是否有数据，有数据则将数据进行删除
         */
        /**
         * 划重点：
         * //此处需要的内存，内存受限，不够用了，因此触发GC，回收软引用对象
         *
         */
        byte[] array = new byte[7*1024*1024+500*1024]; 
        System.gc(); //手动触发gc垃圾回收器，
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(w.get());
        System.out.println(w.isEnqueued());
        System.out.println(queue.poll());
        /**
         * main.java.com.tulun.WeakHashMapGY02$A@1e3ac11b
         * false
         * null
         */

        /**
         * 当前运行未进行软引用回收，如何做到软引用回收呢？ =》 
         * //现在编辑器对于超出我们默认编辑器大小的对象，如果操作系统还有空余内存，
         * //那么他会继续开辟空间，而不是触发gc；
         *
         * 设置堆内存大小，限定一个比较小的值？
         * 如何设置堆内存参数？-》什么参数设置？——》验证
         *
         */
    }


    //弱引用
    public static void weakReferenceDemo() {
        ReferenceQueue<A> queue = new ReferenceQueue<A>();
        WeakReference<A> w = new WeakReference<A>(new A(), queue);

        System.out.println(w.get());
        System.out.println(w.isEnqueued());
        System.out.println(queue.poll());
//        main.java.com.tulun.WeakHashMapGY02$A@20724356
//        false
//        null

        System.gc(); //弱引用只有gc就会被回收

        System.out.println(w.get());
        System.out.println(w.isEnqueued());
        System.out.println(queue.poll());

//        null
//        true
//        java.lang.ref.WeakReference@25e12e2c

    }
    
    
    // 虚引用
    public static void phantomReferenceDemo() {
        ReferenceQueue<A> queue = new ReferenceQueue<A>();
        PhantomReference<A> ptr = new PhantomReference<A>(new A(), queue);
        System.gc(); //此处启动GC，回收A对象，queue收到通知，该对象回收了
        if(ptr.isEnqueued()){ //为true，表明该对象被标记，将被回收；
            System.out.println("ptr.isEnqueued()");
        }else{
            System.out.println("not ptr.isEnqueued()");
        }
        //ptr.isEnqueued()
    }
}

```

