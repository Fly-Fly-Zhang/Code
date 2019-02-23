####   ArrayList,LinkedList的异同点，各自优势（应用场景）
####   ArrayList源码分析以及自定义实现类[ 请点击 ](https://blog.csdn.net/Fly_Fly_Zhang/article/details/87865108)
#####  相同点：

 - [ ] 继承关系：List接口下的实现类，具有list提供的所有方法；
 - [ ] 有序性：数据都是按照插入有序
 - [ ] 重复性：集合中元素是可以重复的‘
 - [ ] null值：都可以存储null值
 - [ ] 安全性：都是非线程安全的
##### 不同点 ：
 - [ ] 数据结构：ArrayList底层是数组，LinkedList底层是链表；
 - [ ] 特有方法： LinkedList实现了Deque接口，具有队列的一些特性，例如：addFirst(),addLast()
 - [ ] 效率：ArrayList查询效率高（O(1)）,在不扩容的情况下添加效率就是O(1) ; LinkedList添加（O(1)）,查询（O(n)）
 - [ ] 应用场景不同：ArrayList 在查询较高的业务场景优先考虑；LinkedList在修改，添加等操作较多的场景下优先考虑；

####   LinkedList.move(Object o)源码分析

```java
remove 删除元素
    public boolean remove(Object o) {
       //判断元素是否为null进行不同的判等处理
       删除元素从前往后删除，重复性元素直接删除第一个节点
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }
    
    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;//删除节点下一个元素
        final Node<E> prev = x.prev;//删除节点前一个元素

        //前驱prev分两种情况：一种是first节点，一种非first节点
        if (prev == null) {
            //当前节点是first节点
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        //后继next分两种情况：一种是last节点，一种是非last节点
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        //将元素置为null
        x.item = null; //方便jc进行回收
        size--;
        modCount++;
        return element;
    }
```

####  Iterator迭代器实现原理参照此篇博客[请点击](https://blog.csdn.net/Fly_Fly_Zhang/article/details/87721790)

```java
package collection;
import java.util.Iterator;
/**
 * @Created with IntelliJ IDEA
 * @Description:
 * @Package: collection
 * @author: FLy-Fly-Zhang
 * @Date: 2019/2/20
 * @Time: 18:32
 */
/*
 *数据按照插入有序
 * 可以重复存储null值
 * 可以重复存储数据
 */
class LinkedList1<T> implements Iterable{
    private class ListNode{
        private T val;
        protected ListNode next;
        protected ListNode(T val){
            this.val=val;
        }
        public T getVal(){
            return val;
        }
    }
    private int size;
    private ListNode head;
    private ListNode last;
    public LinkedList1(T val){
        this.size=0;
        this.head=new ListNode(val );
        last=head;
    }
    public void add(T val){
        last.next=new ListNode(val);
        last=last.next;
        size++;
    }
    private boolean  range(int index){
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException();
        }
        return true;
    }
    public T get(int index){
        range(index);
        ListNode cur=head.next;
        for(int i=0; i<=index ; i++){
            cur=cur.next;
        }
        return cur.getVal();
    }
    public boolean remove(Object obj){
        ListNode cur=head;
        T value=(T)obj;
        while(cur.next!=null){
            if(cur.next.getVal().equals(value)){
                cur.next=cur.next.next;
                if(cur.next==null){//如果删除元素为最后一个节点需要重新确认last
                    last=cur;
                }
                size--;
                return true;
            }
            cur=cur.next;
        }
        return false;

    }
    public void toStrig(){
        ListNode cur=head.next;
        System.out.print("[ ");
        while(cur!=null){
            System.out.print(cur.getVal()+" ");
            cur=cur.next;
        }
        System.out.println("]");
    }
    @Override
    public Iterator iterator() {
        return new Ite();
    }
    private class Ite implements Iterator{
        ListNode pre=head;
        ListNode cur=head;
        public boolean hasNext(){
            if(cur.next!=null){
                return true;
            }
            return false;
        }
        public T next(){
            cur=cur.next;
            return cur.getVal();
        }
        public void remove(){
            pre.next=pre.next.next;
            cur=pre;
            size--;
        }
    }
}
public class LinkedListDemo {


    public static void main(String[] args) {
        LinkedList1<Integer> linkedList=new LinkedList1<>(1);
        linkedList.add(12);
        linkedList.add(33);
        linkedList.add(45);
        System.out.println(linkedList.remove(12));
        linkedList.toStrig();
        Iterator<Integer> iterator=linkedList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next()+" ");
            iterator.remove();
        }
        linkedList.toStrig();
    }
}
```
