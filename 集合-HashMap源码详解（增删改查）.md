#####   特点:

 - [ ] key，value都可以为null，key值不能重复；
 - [ ] 不能保证插入有序；
 - [ ] 通过key进行hash；
 - [ ] 非线程安全；
#####  底层数据结构：
HashMap底层的实现是数组+链表实现:数组中存储的是一个个entry实体，hash到同一个索引位置的数据通过链表链接起来；

#####   基本属性：
 - [ ] transient：和序列化相关的关键字

```java
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
//哈希表中数组默认初始值大小为16

static final int MAXIMUM_CAPACITY = 1 << 30;
//哈希表中数组最大容量值

static final float DEFAULT_LOAD_FACTOR = 0.75f;
//默认的加载因子-》扩容使用

static final Entry<?,?>[] EMPTY_TABLE = {};
//用来判断添加元素的table是否为空

transient Entry<K,V>[] table = (Entry<K,V>[]) EMPTY_TABLE; 
//table初始化大小为2的指数级

static class Entry<K,V>{
   final K key; //键值对的key
   V value;   //键值对的value
   Entry<K,V> next;  //next节点
   int hash;  //和key相关的hash
}

transient int size;
//hashMap中存储entry实体的个数

int threshold;
//扩容的阈值=》阈值的计算方式：capacity * loadFactor

final float loadFactor;
//加载因子
```
#####   继承关系

```java
public class HashMap<K,V>
    extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable
//继承了AbstractMap(jdk1.2)
//实现了map接口implements Map<K,V>，可以克隆、可以序列化
```
#####  构造函数：

```java
//指定初始容量、指定加载因子
public HashMap(int initialCapacity, float loadFactor) {
  //基本参数校验
  if (initialCapacity < 0)
      throw new IllegalArgumentException("Illegal initial capacity: " +
                                         initialCapacity);
  if (initialCapacity > MAXIMUM_CAPACITY)
      initialCapacity = MAXIMUM_CAPACITY;
  if (loadFactor <= 0 || Float.isNaN(loadFactor))
      throw new IllegalArgumentException("Illegal load factor: " +
                                         loadFactor);
  //加载因子、扩容阈值初始化
  this.loadFactor = loadFactor;
  threshold = initialCapacity;
  init();
}

   //通过默认加载因子
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }
    //通过默认加载因子和默认容量初始化
    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

   //通过map集合来初始化当前集合
    public HashMap(Map<? extends K, ? extends V> m) {
        this(Math.max((int) (m.size() / DEFAULT_LOAD_FACTOR) + 1,
                      DEFAULT_INITIAL_CAPACITY), DEFAULT_LOAD_FACTOR);
        inflateTable(threshold);

        putAllForCreate(m);
    } 
```
####   CRUD研究（增删改查）：HashMap的hash()是对key值的hash
#####  put添加元素：

```java
public V put(K key, V value) {
     //第一次插入元素，需要对数组进行初始化：注意：数组大小为2的指数关系
    if (table == EMPTY_TABLE) {
        inflateTable(threshold);
    }
    //可以存储key为null的情况
    if (key == null)
        return putForNullKey(value); //null值特殊处理
    //key不为null 
    int hash = hash(key); // key相关的哈希值   
    int i = indexFor(hash, table.length); //通过key来哈希找到该数据所存在的索引位置
    //遍历该位置i下面的链表：（判断key是否存在，存在替换value，不存在创建新entry）
    for (Entry<K,V> e = table[i]; e != null; e = e.next) { 
        Object k;
        if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
        //集合中存在key值 //==用来判断null值
            V oldValue = e.value;
            e.value = value;
            e.recordAccess(this);
            return oldValue;
        }
    }

    modCount++; //版本号
    addEntry(hash, key, value, i); //该位置链表中不存在此key值节点，添加新的节点
    return null;
} 

//插入key为null情况
//key为null做特殊处理，存储在table索引位0号位置
//遍历该位置下的链表，查看key为null的节点是否存在，存在即将value更新为传入的value
//若该链表下不存在则创建新的entry节点插入该链表
private V putForNullKey(V value) {
    for (Entry<K,V> e = table[0]; e != null; e = e.next) {
        if (e.key == null) {
            V oldValue = e.value;
            e.value = value;
            e.recordAccess(this);
            return oldValue;
        }
    }
    modCount++;
    addEntry(0, null, value, 0);
    return null;
} 

    final int hash(Object k) {
        int h = hashSeed; //hashSeed和hashCode()类似
        if (0 != h && k instanceof String) {
            return sun.misc.Hashing.stringHash32((String) k);
            //带sun的是相对比较古老的代码
        }

        h ^= k.hashCode();
        //因为hash值是32bit的，很多时候是分布不均匀的，左移右移是为了让其均匀分布，减少hash冲突
        h ^= (h >>> 20) ^ (h >>> 12); 
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    static int indexFor(int h, int length) {  
    // 基于当前集合的数组大小将hash值转换成数组角标
        // assert Integer.bitCount(length) == 1 : "length must be a non-zero power of 2";
        return h & (length-1);
         // length 16
 //   1001 1111 0110 0110  //hash值
//    0000 0000 0000 1111  //数组大小
//    -------------------
//    0000 0000 0000 0110
//    ==>6 //这个值永远不回超过数组长度
    }     
    void addEntry(int hash, K key, V value, int bucketIndex) {
     
      if ((size >= threshold) && (null != table[bucketIndex])) {
        //扩容，目前集合中元素个数大于阈值或者当前需要插入的角标链表不为空时进行扩容
        resize(2 * table.length);//扩容为二倍扩容
        
        //更新新插入元素的索引位置
        hash = (null != key) ? hash(key) : 0;
        bucketIndex = indexFor(hash, table.length);
       }
       //创造一个新的节点
       createEntry(hash, key, value, bucketIndex);
    }
//扩容时机：当前存储元素的个数size>=扩容阈值threshold时考虑扩容
//扩容大小为2倍的数组大小table.length(数组要满足2的指数级关系)

    void resize(int newCapacity) {
    Entry[] oldTable = table;
    int oldCapacity = oldTable.length;
    if (oldCapacity == MAXIMUM_CAPACITY) {//如果旧数组长度已经达到允许的最大的值
        threshold = Integer.MAX_VALUE;//那么将阈值改变，并不对数组扩容
        return;
    }

    Entry[] newTable = new Entry[newCapacity];
    transfer(newTable, initHashSeedAsNeeded(newCapacity));
    table = newTable;
    threshold = (int)Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1);
    //在默认最大阈值和新的阈值之间取一个最小的
}
//创建新的table数组，并且将原来集合上的元素全部进行hash，找到新的对应位置进行插入

   void transfer(Entry[] newTable, boolean rehash) {
    int newCapacity = newTable.length;
    for (Entry<K,V> e : table) {//遍历旧数组
        while(null != e) {遍历该角标下的链表
            Entry<K,V> next = e.next;
            if (rehash) {//是否进行在Hash
                e.hash = null == e.key ? 0 : hash(e.key);
                //null依然指定0号角标；别的key根据新的数组长度进行在Hash
            }
            int i = indexFor(e.hash, newCapacity);//通过新的Hash获取角标
            e.next = newTable[i];
            newTable[i] = e;
            e = next;
        }
    }
  }
   void createEntry(int hash, K key, V value, int bucketIndex) {
    Entry<K,V> e = table[bucketIndex];
    table[bucketIndex] = new Entry<>(hash, key, value, e);
    size++;
}    
//元素作为当前索引位置的头部元素进行插入


```

#####   get()得到元素: 通过key来获取value

```java
public V get(Object key) {
    if (key == null)
        return getForNullKey();
    Entry<K,V> entry = getEntry(key);//通过key找到对应链表的key结点
    //判断该结点是否为null，则返回null，不是则返回对应value值
    return null == entry ? null : entry.getValue();
}

//1、判断key是否为null，是则特殊处理，知道到0号索引位置查找元素
//2、先通过hash找到索引位置，通过索引位置找到当前链表，通过判断key是否相等找到value进行返回

final Entry<K,V> getEntry(Object key) { //找到对应key结点
    if (size == 0) {//判断集合中是否存在元素
        return null;
    }

    int hash = (key == null) ? 0 : hash(key);//计算出对应key值的哈希值
    for (Entry<K,V> e = table[indexFor(hash, table.length)];//根据哈希值找到对应的角标的头结点
         e != null;
         e = e.next) {
        Object k;
        if (e.hash == hash &&((k = e.key) == key || (key != null && key.equals(k))))
        //判断当前结点是否是要找的key结点， ==判断null值
            return e;
    }
    return null;
}
```
#####  remove():删除元素；

```java
public V remove(Object key) {
    Entry<K,V> e = removeEntryForKey(key);
    return (e == null ? null : e.value);
}

final Entry<K,V> removeEntryForKey(Object key) {
    if (size == 0) {
        return null;
    }
    int hash = (key == null) ? 0 : hash(key);
    int i = indexFor(hash, table.length);//得到对应结点
    Entry<K,V> prev = table[i];
    Entry<K,V> e = prev;

    while (e != null) {
        Entry<K,V> next = e.next;
        Object k;
        //判断是否是key结点
        if (e.hash == hash &&
            ((k = e.key) == key || (key != null && key.equals(k)))) {
            modCount++;
            size--;
            if (prev == e)//key为头结点
                table[i] = next;
            else//非头结点
                prev.next = next;
            e.recordRemoval(this);
            return e;
        }
        prev = e;
        e = next;
    }

    return e;
}   
```

