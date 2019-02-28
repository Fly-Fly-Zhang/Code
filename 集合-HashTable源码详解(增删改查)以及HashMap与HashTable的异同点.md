#####   特点：

 - [ ] 底层数据结构：链表加数组；
 - [ ] null值：key,value均不能为null；
 - [ ] key重复性：key不能重复；
 - [ ] 有序性：不能保证插入有序；
 - [ ] 是线程安全的
#####  增长方式 ：原数组长度二倍+1的方式增长
#####  继承关系：

```java
public class Hashtable<K,V>
    extends Dictionary<K,V>  //提供一些基本的抽象方法
    implements Map<K,V>,//拥有Map接口的属性
     Cloneable, //表明该类可以进行clone
     java.io.Serializable { //序列化
```
#####   默认属性：

```java
private transient Entry<K,V>[] table; //数组
private transient int count; //集合中元素个数
    private int threshold;//阈值
 private float loadFactor;//加载因子
  private transient int modCount = 0;//版本号 

```
#####   默认值：HashTable默认数组大小为 11；
#####    构造函数：

```java
   public Hashtable(int initialCapacity, float loadFactor) { //数组大小，加载因子
        if (initialCapacity < 0)//传入数组大小小于0抛出异常
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) //加载因子异常
            throw new IllegalArgumentException("Illegal Load: "+loadFactor);

        if (initialCapacity==0)//传入数组大小为0默认创建长度为1的数组
            initialCapacity = 1;
        this.loadFactor = loadFactor;
        table = new Entry[initialCapacity]; //创建数组
        threshold = (int)Math.min(initialCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
        //得到新的阈值
        initHashSeedAsNeeded(initialCapacity);
    }

    public Hashtable(int initialCapacity) {
        this(initialCapacity, 0.75f);//只传入数组大小，默认加载因子
    }

    public Hashtable() {//无参，默认数组大小为11；
        this(11, 0.75f);
    }

    public Hashtable(Map<? extends K, ? extends V> t) { //传入一个Map集合
        this(Math.max(2*t.size(), 11), 0.75f); //创建一个传入集合元素二倍的数组
        putAll(t);//将集合中所有元素添加进新的集合
    }
```
####    CRUD（增删改查）：
#####   put(): 添加元素

```java
      public synchronized V put(K key, V value) {
      //注意一点：那就是key和value都不能为null
        // Make sure the value is not null
        if (value == null) {//值不能为null
            throw new NullPointerException();
        }

        // Makes sure the key is not already in the hashtable.
        Entry tab[] = table;
        int hash = hash(key); //key也不能为null,hashCode会抛出异常
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (Entry<K,V> e = tab[index] ; e != null ; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) { 
            //这里的判断条件于HashMap相比少了==判断，因为HashMap的值可以为null必须用==判断
                V old = e.value; //value值进行更新
                e.value = value;
                return old;
            }
        }

        modCount++;
        //走到这里说明原集合中没有key
        if (count >= threshold) {//集合中元素个数大于阈值，需要进行扩容；
            // Rehash the table if the threshold is exceeded
            rehash();

            tab = table;
            hash = hash(key);
            index = (hash & 0x7FFFFFFF) % tab.length;
        }

        // Creates the new entry.
        Entry<K,V> e = tab[index];
        tab[index] = new Entry<>(hash, key, value, e);//新结点头插到链表中
        count++;
        return null;
    }
    protected void rehash() { //对数组进行扩容
        int oldCapacity = table.length;
        Entry<K,V>[] oldMap = table;

        // overflow-conscious code
        int newCapacity = (oldCapacity << 1) + 1; //扩容方式：二倍+1
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
        //新的数组长度大于默认最大长度，则数组长度取默认长度
            if (oldCapacity == MAX_ARRAY_SIZE)
                // Keep running with MAX_ARRAY_SIZE buckets
                return;
            newCapacity = MAX_ARRAY_SIZE;
        }
        Entry<K,V>[] newMap = new Entry[newCapacity];//创建新的数组长度

        modCount++;
        threshold = (int)Math.min(newCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
        //计算出新的阈值
        
        boolean rehash = initHashSeedAsNeeded(newCapacity);
        //根据新的长度判断是否进行rehash操作；
        table = newMap;

        for (int i = oldCapacity ; i-- > 0 ;) {对旧数组遍历
            for (Entry<K,V> old = oldMap[i] ; old != null ; ) {对每个链表进行遍历
                Entry<K,V> e = old;
                old = old.next;

                if (rehash) {
                    e.hash = hash(e.key);//得到新的hash值
                }
                int index = (e.hash & 0x7FFFFFFF) % newCapacity;
                e.next = newMap[index];//将e进行头插到新的角标链表中
                newMap[index] = e;
            }
        }
    }

```
#####  remove(Object key) ：删除元素；

```java
     public synchronized V remove(Object key) {
        Entry tab[] = table;
        int hash = hash(key);
        int index = (hash & 0x7FFFFFFF) % tab.length; 
        //通过Hash确定key在数组中的位置
        for (Entry<K,V> e = tab[index], prev = null ; e != null ; prev = e, e = e.next) {
        //遍历当前角标的链表哦
            if ((e.hash == hash) && e.key.equals(key)) {
            //因为没有null值所以不需要==判断null值
                modCount++;
                if (prev != null) {//非头结点
                    prev.next = e.next;
                } else {//头结点
                    tab[index] = e.next;
                }
                count--;
                V oldValue = e.value;
                e.value = null;//方便回收
                return oldValue;
            }
        }
        return null; //没有找到，返回null；
    }
```
#####   get(Object key) ： 通过key得到value；

```java
 public synchronized V get(Object key) {
        Entry tab[] = table;
        int hash = hash(key);
        int index = (hash & 0x7FFFFFFF) % tab.length;
        //通过Hash得到对应角标
        for (Entry<K,V> e = tab[index] ; e != null ; e = e.next) {
        //遍历当前角标的链表，找到key，从而得到value
            if ((e.hash == hash) && e.key.equals(key)) {
                return e.value;
            }
        }
        return null; //没有找到
    }
```

####   HashMap与HashTable异同点
#####  相同点：

 - [ ] 底层数组结构都是数组加链表；
 - [ ] key值都不能重复；
 - [ ] 插入元素都不能保证插入有序；
 - [ ] 哈希过程都是通过Key进行hash的；
#####   不同点：
 - [ ]   安全性问题：HashMap不能保证线程安全；HashTable能保证线程安全
 - [ ] 效率问题：HashMap在单线程效率高；HashTable在单线程效率低：
 - [ ] 继承关系：HashMap继承自AbstractMap ；  HashTable继承自Dictionary；
 - [ ] null值问题：HashMap==》 key，value都可以为null ；HashTable==》都不能为null；
 - [ ] 扩容方式: HashMap:  2*table.length  ;   HashTable:  2*table.length+1;
 - [ ] 默认值：HashMap默认数组大小为16； HashTable默认数组大小为11；
 - [ ] Hash算法不同；
 

