#####    ArrayList特点：

 - [ ] 重复性：数据可以重复
 - [ ] null值：可以有null值存在
 - [ ] 有序性：能保证数据插入有序
#####    常用方法介绍：
 - [ ] int size(): 集合中存储元素的个数；
 - [ ] boolean isEmpty():判断当前元素是否为空，返回值为boolean类型；
 - [ ] boolean contains(Object  o): 判断当前集合是否包含Object对象；
 - [ ] Iterator<E>   iterator(): 迭代器，返回iterator实例；
 - [ ] Object[ ]   toArray (): 将集合转化成数组；
 - [ ] < T >   T[ ]   toArray( T[ ]  a): 将集合转化成数组；
 - [ ] boolean  add（E e):  添加元素；
 - [ ] boolean  remove(Object o):  删除元素；
 - [ ] boolean  containsAll (  Collection < ? >   c) : 判断入参集合是否属于当前集合；
 - [ ] boolean addAll( Collection < ? extends  E >  c) : 对该集合添加子集合形成新的集合 ；
 - [ ] boolean addAll(int index , Collection< ? extends E > c )  : 在指定的位置添加子集合 ；
 - [ ] void   clean () : 将集合清除掉;
 - [ ] boolean equals(Object  o):  判断是否相等；
 - [ ] E  get(int index):通过指定位置来获取元素；
 - [ ] int indexOf (Object  o): 判断当前元素在集合中的位置（从头查找）；
 - [ ] int lastIndexOf (Object o):判断当前元素在集合中的位置（从尾部查找）；
 - [ ] List< E > subList(int fromIndex ,int toIndex) : 找当前集合的子集；
 - [ ]  removeAll（）：list1.remove(list2) ;  这句话的意思是：list1集合与list2集合 先  找到 交集，然后 在 list1中删除 交集 ，然后将删除交集后的list1重新赋给list1，list2中的元素不变，然后打印list1，这样就会输出 删除交集后的 list1 。 removeAll删除的是 两个集合的交集 。 
 - [ ] retainAll（）:  boolean flag = list1.retainAll(list2) ;  list1 和 list2 两个集合 先取 交集 。 然后 将交集 赋给 list1 ， 如果 list1集合元素组成发生了变化，那么就返回 true ， 否则返回 false 。特殊情况： list1 和 list2 两个集合 完全相同，所以 list1 和list2 的交集 就是他们 本身， 把交集 赋给 list1时 ，list1 没有发生任何的变化，所以返回 false 。综上所述：retainAll（）中 只要list1发生变化，就返回 true，不发生变化就返回false 。

####   源码分析：
#####   继承关系：

```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable

```

 - [ ]  ArrayList继承AbstractList，父类中对部分接口进行实现;
 - [ ] 实现了List接口提供的方法;
 - [ ] Serializable说明该类能够被序列化 ，能够被克隆、序列化;
#####  ArrayList底层数据结构是链表；
#####  基本属性：
 - [ ] 默认容量大小 ：
 

```java
  private static final int  DEFAULT_CAPACITY = 10;
```

 - [ ] 默认数组大小：

 

```java
private static final Object[] EMPTY_ELEMENTDATA = {};
```

 - [ ] 存储元素的数组：
 

```java
private transient Object[] elementData;
```

 - [ ] 集合存储元素的个数：
 

```java
private int size;
```
#####   构造函数

```java 
//有参构造，指定集合初始化大小
public ArrayList(int initialCapacity) {
        super();
        //指定大小不合法，则直接抛出异常
        初始化数组大小
        
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        this.elementData = new Object[initialCapacity];
    }
   //无参构造
    public ArrayList() {
        super();
        this.elementData = EMPTY_ELEMENTDATA;
    }
    //有参构造，通过集合来创建新的集合

    public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        size = elementData.length;
        // c.toArray might (incorrectly) not return Object[] (see 6260652)
        if (elementData.getClass() != Object[].class)
        //集合类型不同，通过copy来将原数组给新数组
            elementData = Arrays.copyOf(elementData, size, Object[].class);
    }
    
   
```

#####   增长方式：按照原数组的1.5倍进行扩容；
#####   CRUD方法研究（增删改查）：

 - [ ] add(E e) : 首先需要进行数组扩容考虑，如果要扩容（size+1 > table.length）, 按照1.5倍进行扩容；然后将元素插入数组尾部，并计数 size+1;

```java
public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
    return true;
}
private void ensureCapacityInternal(int minCapacity) {
    if (elementData == EMPTY_ELEMENTDATA) { //判断当前数组是否为空
        minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        //判断当前需要的数组大小与默认数组大小值进行比较，找出最大的
    }

    ensureExplicitCapacity(minCapacity);
}
private void ensureExplicitCapacity(int minCapacity) {
    modCount++;//版本号发生变化

    // overflow-conscious code
    if (minCapacity - elementData.length > 0)
    //将需要数组大小与当前数组大小进行比较，如果不够用，对数组进行扩容
        grow(minCapacity);
}
//数组扩容
private void grow(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);//对原数组长度进行1.5倍扩容
    if (newCapacity - minCapacity < 0)
    //扩容长度小于需要长度，将扩容长度定为需要长度
        newCapacity = minCapacity;
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    // 通过copy的的方式实现数组扩容
    elementData = Arrays.copyOf(elementData, newCapacity);
}   
```

 - [ ] remove (Object o): 先找到元素存储位置（null和非null的对象判断相等不同），将后面的元素向前移动 。注意：相同元素存在的情况下，只需要找到从0号开始第一次出现的元素；
 

```java
public boolean remove(Object o) {
    if (o == null) { //null值判断
        for (int index = 0; index < size; index++)
            if (elementData[index] == null) {
                fastRemove(index);
                return true;
            }
    } else {//对象判断
        for (int index = 0; index < size; index++)
            if (o.equals(elementData[index])) {
                fastRemove(index);
                return true;
            }
    }
    return false;
} 
//将后面元素向前移动
private void fastRemove(int index) {
    modCount++;//版本号发生变化
    int numMoved = size - index - 1;// 删除元素后的元素个数
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index,
                         numMoved); //利用copy实现将删除元素后面元素前移的目标
    elementData[--size] = null;//元素个数减一
}  
```

 - [ ] get(int index): 获取元素；
 

```java
public E get(int index) {
    rangeCheck(index);
    return elementData(index);
}
//输入角标进行合法性检查 
 private void rangeCheck(int index) {
     if (index < 0 || index >= this.size)
         throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
 } 
E elementData(int index) {
    return (E) elementData[index];
}      
```
####   自定义ArrayList

```java
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * @Created with IntelliJ IDEA
 * @Description: 自定义ArrayList
 * @Package: collection
 * @author: FLy-Fly-Zhang
 * @Date: 2019/2/15
 * @Time: 20:48
 */
class ArrayList<E> implements Iterable<E>{
    private E[] array;
    private int size;//装填元素对应的角标
    public ArrayList2(){
        this.array=(E[])new Object[10];
        this.size=0;
    }
    
    public boolean isFull(){ // 判断数组是否装满 
        if(size<array.length)
            return true;
        return false;
    }
    private void grow(){ //数组1.5倍扩容
        this.array=Arrays.copyOf(array,size+(size>>1));
    }
    
    public  boolean add(E e){//添加元素
        if(isFull()){
            grow();
        }
        this.array[size++]=e;
        return true;
    }
    private boolean checkRange(int i){ //角标合法性检查
        if(0<i&&i>=size)
            throw new IndexOutOfBoundsException();
        return true;
    }
    public E get(int i){
        checkRange(i);
        return array[i];
       
    }
    public boolean isEmpty(){ //判断数组是否为空
        if(size==0)
            return true;
        return false;
    }
    private void moveLast(int i){ //将i后面的元素向前移一位
        while(i+1<size){
            array[i]=array[i+1];
        }
        size--;
    }
    public boolean remove(Object obj){
        if(isEmpty())
            return false;
        E num=(E)obj;
        for(int i=0;i<size;i++){
            if(array[i].equals(num)){
                moveLast(i);//后面元素向前移动
                return true;
            }
        }
        return false;
    }
    @Override
    public Iterator<E> iterator() { //
         return new Itr(0);
    }
    private  class Itr implements Iterator<E>{
        private int ind;
        protected Itr(int start){
            this.ind=start;
        }
        @Override
        public boolean hasNext() {
            if(ind<size)
                return true;
            return false;

        }
        @Override
        public E next() {
            if(hasNext())
                return get(ind++ );
            throw new IndexOutOfBoundsException();
        }
        @Override
        public void remove() {
            if(hasNext())
                ArrayList2.this.remove(ind);
            throw new IndexOutOfBoundsException();
        }
    }
}
public class ArrayListDemo {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
        ArrayList<Integer>  arrayList1=new ArrayList<>();
        //添加元素 boolean add(E e)
        arrayList1.add(30);
        arrayList1.add(12);
        arrayList1.add(12);
        arrayList2.add(12);
        arrayList2.add(34);
        arrayList2.add(12);
    }
}
```

####   ArrayList迭代器元素详解以及自定义迭代器的实现  [请点击](https://blog.csdn.net/Fly_Fly_Zhang/article/details/87721790)

####   ArrayList求并集，交集，差集  [请点击](https://blog.csdn.net/Fly_Fly_Zhang/article/details/87867518)
