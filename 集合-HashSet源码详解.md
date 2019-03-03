####   特点：
#####   如果对HashMap理解不深，请先查阅  [HashMap源码详解](https://blog.csdn.net/Fly_Fly_Zhang/article/details/87902090)  ，否则会对本片博客理解有一定困难；
#####  底层数据结构：HashSet底层封装的是HashMap，所以元素添加会放到HashMap的key中，value值使用new Object对象作为value；所以HashSet和HashMap的所具有的特点是类似的；

 - [ ] 数据不能重复；
 - [ ] 可以存储null值；
 - [ ] 数据不能保证插入有序；
#####  默认值：与HashMap相同；
#####  基本属性：

```java

   private transient HashMap<E,Object> map;//HashMap集合

    private static final Object PRESENT = new Object();//调用HashMap方法，装入的value值
```

##### 继承关系：

```java   
   public class HashSet<E>
    extends AbstractSet<E>//继承了AbstractSet
    implements Set<E>,//实现set方法
     Cloneable,//标记本类可以被克隆
      java.io.Serializable//可以被序列化
```
#####   构造函数：均调了HashMap对应的构造函数

```java
     public HashSet() {
        map = new HashMap<>();
    }

    public HashSet(Collection<? extends E> c) {
        map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
        addAll(c);
    }

    public HashSet(int initialCapacity, float loadFactor) {
        map = new HashMap<>(initialCapacity, loadFactor);
    }

    public HashSet(int initialCapacity) {
        map = new HashMap<>(initialCapacity);
    }

    HashSet(int initialCapacity, float loadFactor, boolean dummy) {
        map = new LinkedHashMap<>(initialCapacity, loadFactor);
    }

```
####  CRUD(增删改查)：

 - [ ] add（）

```java
   public boolean add(E e) {//只能添加一个null值
        return map.put(e, PRESENT)==null; 
		//HashMap的put方法，如果是新添加的结点，会返回null；
		//如果是原有结点，那么会返回value值，这样返回的就是false这样表明集合中已经有该元素；
    }

```

 - [ ] remove（）；
 
```java
    public boolean remove(Object o) {
        return map.remove(o)==PRESENT;
		//HashSet添加进HashMap中所有value值都是PRESENT，
		//如果找到key值，那么返回的value值肯定是PRESENT；
		//没有找到，返回的是null；
        
    }
```

   
#####   HashSet应用场景：去重

 - [ ]   打印全部数据，重复性元素只打印一次;
 

```java
        ArrayList<Integer> arrayList=new ArrayList<>(100000);
        for (int i = 0; i <100000 ; i++) {
            arrayList.add((int) (Math.random()*1000));
        }
        iterator=arrayList.iterator();
        HashSet<Integer> hashSet1=new HashSet<>();
        while(iterator.hasNext()){
            Integer val= iterator.next();
            if(hashSet1.add(val)){//第一次添加才会返回true
                System.out.println(val);

            }
        }    
```

