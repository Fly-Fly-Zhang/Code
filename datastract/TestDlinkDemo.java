package com.tuln.datastract;

/**
 * @Created with IntelliJ IDEA
 * @Description:
 * @Package: com.tuln.datastract
 * @author: FLy-Fly-Zhang
 * @Date: 2018/12/2
 * @Time: 20:13
 */
class Dlink{
    class Entry2{
        private Entry2 prev;
        private Entry2 next;
        private int data;
        public Entry2(){

                this.data = -1;
                this.next = null;
                this.prev = null;
        }
        public Entry2(int val) {
                this.data = val;
                this.next = null;
                this.prev = null;
            }
        }
        private Entry2 head;
        public Dlink(){
            this.head = new Entry2();
        }
        //头插法
        public void insert(int val) {
            Entry2 entry2 = new Entry2(val);
            entry2.next = this.head.next;
            entry2.prev = this.head;
            this.head.next = entry2;
            if(entry2.next != null) {
                entry2.next.prev = entry2;
            }
        }
        //尾插法
        public void insertTail(int val) {
              Entry2 cur=this.head;
              while(cur.next!=null){
                  cur=cur.next;
              }
              Entry2 entry2=new Entry2(val);
              cur.next=entry2;
              entry2.prev=cur;
        }
         //删除所有值为val的节点
         public void deleteAll(int val) {
             Entry2 cur=this.head.next;
             while(cur!=null){
                 if(cur.data==val){
                     cur.prev.next=cur.next;
                     if(cur.next!=null){ //最后一次删除
                         cur.next.prev=cur.next;
                     }
                 }
                 cur=cur.next;
             }
         }



}

public class TestDlinkDemo {
}
