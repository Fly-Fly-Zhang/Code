package com.tuln.datastract;

/**
 * @Created with IntelliJ IDEA
 * @Description:
 * @Package: com.tuln.datastract
 * @author: FLy-Fly-Zhang
 * @Date: 2018/12/2
 * @Time: 19:39
 */
class Clink{
    class Entry{
        private int data;
        private Entry next;
        public Entry(){
            this.data=0;
            this.next=null;
        }
        public Entry(int val){
            this.data=val;
            this.next=null;
        }
    }
    private Entry head;
    public Clink(){
            this.head=new Entry();
            this.head.next=this.head;
      }
    //头插法
    public boolean insertHead(Entry entry){

        entry.next=this.head.next;
        this.head.next=entry;
        return true;
    }
    //尾插法
    public  boolean insertTail(Entry entry){
        if(entry==null){
            return  false;
        }
        Entry cur=this.head;
        while(cur.next!=this.head){
            cur=cur.next;
        }
        cur.next=entry;
        entry.next=this.head;
        return true;
    }
    //删除所有值为val的节点
     public boolean deleteAll(int val){
        if(this.head.next==null){
            return false;
        }
        Entry cur=this.head.next;
        Entry pre=this.head;
        while(cur!=this.head){
            if(cur.data==val){
                pre.next=cur.next;
                cur=pre.next;
                continue;
            }
            pre=pre.next;
            cur=cur.next;
        }
        return true;
     }

    //打印
     public void show(Entry head){
        Entry cur=this.head;
        while(cur.next!=this.head){
            cur=cur.next;
            System.out.println(cur.data);
        }
     }

}
public class TestClinkDemo {


}
