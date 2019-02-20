####  迭代器：是一种设计模式，提供了一种方法，来对集合，容器进行遍历的方式，不需要关注底层数据结构和数据类型，来达到底层和上层遍历解耦的目的。
####   集合如何获得一个迭代器：集合要具有iterator 方法需要实现iterable接口，要自定义一个迭代器内部类，类需要实现Iterator<E>接口；
 

 - [ ]   iterator方法:

```java
 public Iterator<E> iterator() {
        return new Itr();//具体的迭代器需要自定义一个内部类实现Iterator接口
    }
    
```

 - [ ] 集合自定义迭代器实现：
 

```java
public class DIYArrayList<T> implements Iterable<T> {
    private T[] data;//存储元素数组
    private int size;//元素个数
    public DIYArrayList() {
        this(10);
    }
    public DIYArrayList(int campcity) {
        if (campcity < 0){
            try {
                throw new IllegalAccessException("参数异常");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        data = (T[]) new Object[campcity];
        size = 0;
    }

    //集合是否满
    private boolean isFull() {
        return size == data.length;
    }

    //扩容
    private void grow() {
        int newLength = size+size>>1;//1.5倍扩容
        data = Arrays.copyOf(data, newLength);
    }

    //范围判断
    private boolean checkRange(int index) {
        if (index< 0 || index >= size)
             throw new IndexOutOfBoundsException();

        return true;
    }
    //重写集合转换数组方法
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < size; i++) {

            buffer.append(data[i]+" ");
        }
        return buffer.toString();
    }
     //添加元素
    public boolean add(T e) {
        if (isFull()) {//数组已满，对数组进行扩充
            grow();
        }
        data[size++]=e;
        return true;
    }
    //获取指定角标的集合元素
    public T get(int index) {
        checkRange(index); //对角标进行合法性检查
        return data[index];
    }
    //对集合指定角标元素进行删除
    public T remove(int index) {
        checkRange(index);
        T v = data[index];
        int mov = size -index-1;
        System.arraycopy(data, index+1, data,index,mov);
        data[--size] = null;
        return v;
    }
    //对集合指定元素进行删除
    public boolean remove (T e) {
    //注意对null值和非null进行区别对待
        if (e == null) { 
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    int mov = size -i-1;
                    System.arraycopy(data, i+1, data,i,mov);
                    data[--size] = null;
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (e.equals(data[i])) {
                    int mov = size -i-1;
                    System.arraycopy(data, i+1, data,i,mov);
                    data[--size] = null;
                    return true;
                }
            }
        }
        return false;
    }
   //获取一个迭代器
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    class Itr implements Iterator<T> {
        private int afterindex;//前一个位置
        private int beforeindex;//后一个位置

        @Override
        public boolean hasNext() {
            return afterindex != size;
        }

        @Override
        public T next() {
            int i = afterindex;
            afterindex++;
            T v = data[i];
            beforeindex = i;
            return v;
        }

        @Override
        public void remove() {
            // T next = next();
            //DIYArrayList.this.remove(next);
            //注意，这里只能调用集合中的角标删除方法，因为一个集合中可能有多个与删除元素相同的元素，
            //这样可能删除的元素并不是迭代器指定那个元素；
            DIYArrayList.this.remove(beforeindex ); //删除的是上一次next()的值
            afterindex=beforeindex; //等效于afterindex--
            beforeindex --;
        }
    }

    public static void main(String[] args) {
        DIYArrayList<Integer> arrayList = new DIYArrayList<>();
        arrayList.add(12);，
        arrayList.add(34);
        arrayList.add(56);
        System.out.println(arrayList);
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
          Integer next = iterator.next();
          if (next == 33) {
               iterator.remove(); //remove()必须与next（）配合使用，源码也是这样设计的
            }
        }
        System.out.println(arrayList.size);
    }
}

```

#####   Iterator方法介绍以及源码分析

 - [ ] boolean  hasNext()：判断集合是否还有元素，true则表示还存在元素
 - [ ] E   next()  : 返回当前数据
 - [ ] void remove（）：删除元素：
######    注意：
 - [ ] hasNext()和next()需要配合使用，每次next()之前都必须调用一次hasNext（）进行判断；
 - [ ] 每次调用remove（）之前必须调用一次next（），那么也就必须调用hasNext();
 - [ ] 版本号的实例分析：
 

```java
 Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            if (value == 33) {
                 arrayList.remove(Integer.valueOf(33)); 
                 //对集合进行增删改查，集合版本号会发生变化，迭代器的不会发生变化
            }
               iterator.remove(); 
               //在迭代器进行删除时，迭代器的版本副本与集合的版本号不同，抛出并发行异常

        }

```

#####   源码解析
```java
private class Itr implements Iterator<E> {
        int cursor;       // hasNext()表示的坐标
        int lastRet = -1; // next()当前坐标
        int expectedModCount = modCount; 
        //版本号，统一集合和迭代器的版本号，如果集合发生修改，那么集合的版本号就会发生变化，
        //那么集合和迭代器的版本号就会不同意，会发生并发性异常；

        Itr() {}
        //判断下一个是否有下一个元素
        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();//版本检查
            int i = cursor;
            if (i >= size)//next()角标位置已经大于集合中元素的个数
                throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length)
           //上一个if没有抛出异常，那么说明size合法，这里按理不会出现i大于数组长度的问题，
           //如果出现说明在迭代器创建后集合发生了改变，因此应该抛一个并发性异常
                throw new ConcurrentModificationException();
            cursor = i + 1;//下一次hasNext()判断角标
            return (E) elementData[lastRet = i];//返回本次hasNext()确定有效的角标元素
        }

        public void remove() {
            if (lastRet < 0)//删除元素的角标不合法
                throw new IllegalStateException();
            checkForComodification();//版本检查

            try {
                ArrayList.this.remove(lastRet);
                //调用集合的remove()方法进行删除操作
                cursor = lastRet;
                //删除完成后，cursor角标的元素移到了lastRet位置，
                lastRet = -1;
                //这句意味着，每次remove()之前必须进行next()操作
                expectedModCount = modCount;
                //本次删除操作，迭代器是知晓的，所以统一版本号
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

    
        final void checkForComodification() {
        //版本检查，如果不同则抛出并发性异常，说明有多个线程对集合进行操作；
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
```

