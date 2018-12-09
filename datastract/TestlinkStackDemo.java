package com.tuln.datastract;

/**
 * @Created with IntelliJ IDEA
 * @Description:
 * @Package: com.tuln.datastract
 * @author: FLy-Fly-Zhang
 * @Date: 2018/12/4
 * @Time: 20:08
 */
class LinkStack{
    class Entry{
        private int val;
        private Entry next;
        public Entry(){
            this.val=-1;
            this.next=null;
        }
        public Entry(int val){
            this.val=val;
            this.next=null;
        }
    }
    private int top;
    private Entry head;
    LinkStack(){
        this.head=new Entry();

    }
    //头插法入栈
    public void push(int val){
        Entry cur=new Entry(val);
        cur.next=this.head.next;
        this.head.next=cur;

    }
    //头插法出栈
    public void pop(){
        if(this.head.next==null){
            throw new UnsupportedOperationException("栈为空");
        }
        Entry cur=this.head.next;
        this.head.next=cur.next;

    }
}
public class TestlinkStackDemo {


}
