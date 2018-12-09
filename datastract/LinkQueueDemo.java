package com.tuln.datastract;

/**
 * @Created with IntelliJ IDEA
 * @Description: 链式队列
 * @Package: com.tuln.datastract
 * @author: FLy-Fly-Zhang
 * @Date: 2018/12/4
 * @Time: 21:26
 */
class LinkQueue{
    class Entry{
        private int data;
        private Entry next;
        Entry(){
            this.data=0;
            this.next=null;
        }
        Entry(int val){
            this.data=val;
            this.next=null;
        }
        private Entry front=null;
        private Entry rear=null;
        private int usedSize=0;
        //入队
        public void push(int val){
            Entry cur=new Entry(val);
            if(front==null&&rear==null){
                front=cur;
                rear=front;
            }else{
                rear.next=cur;
                rear=rear.next;
            }
            this.usedSize++;
        }
        //出队
        public void pop(){
            if(Empty()){
                throw new UnsupportedOperationException("链表队列为空"); //不能支持的操作
            }
            front=front.next;
            usedSize--;

        }
        //空
        public boolean Empty(){
            if(front==rear){
                return true;
            }
            return false;
        }
        //打印
        public void show(){
            Entry cur=front;

            while(cur!=null){
                System.out.println(cur.data);
                cur=cur.next;
            }
        }
    }
}
public class LinkQueueDemo {
    public static void main(String[] args) {
        LinkQueue lk=new LinkQueue();

    }


}
