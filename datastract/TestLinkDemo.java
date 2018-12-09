package com.tuln.datastract;
import java.lang.String;

/**
 * @Created with IntelliJ IDEA
 * @Description: 单链表
 * @Package: com.tuln.datastract
 * @User: FLy
 * @Date: 2018/11/21
 * @Time: 20:22
 */
class TestLink{

    class Entry{ //内部类
        int data; //一般可定义成Object
        Entry next;
        public Entry(){ //头结点
            this.data=-1;
            this.next=null;
        }
        public Entry(int val){
            this.data=val;
            this.next=null;
        }
    }
    private Entry head;//头引用
    public TestLink(){
        this.head=new Entry();
    }
    public TestLink(int val){
        this.insertHead(val);
    }
    /*
     *  头插法
     */
    public void insertHead(int val){
        Entry cur=new Entry(val);
        cur.next=this.head.next; //先链接后面的链表，
        head.next=cur; //再用头部去链接新建的列表
    }
    /*
     *尾插法
     * 找到尾巴 插入数据
     */
    public void insertTail(int val){
        // 找到尾巴
        Entry cur=this.head; //头不动 赋值一个变量去判断。cur为head后的第一个链表
        while(cur.next!=null){
            cur=cur.next;
        }
        Entry entry=new Entry(val);
        //插入数据
        cur.next=entry;
    }
    /*
     *得到单链表的长度(数据节点的个数)
     */
    public int getLength(){
        int count=0;
        Entry cur=this.head.next; //同上 复制head变量去判断
        while(cur!=null){
            count++;
            cur=cur.next;
        }
        return count;
    }
    /*
     *任意位置插入
     */
    public void  insertPos(int val,int pos) throws Exception {
        /*
        if(pos<0||pos>getLength()){
            throw new Exception("链表为空");
        }

        int count=0;
        Entry cur=this.head.next;
        while(cur!=null){
            if(count+1==pos){
                Entry cur1=new Entry(val);
                cur1.next=cur.next;
                cur.next=cur1;
            }
        }*/
        if(pos<0||pos>getLength()){
            return;
        }
        Entry cur =this.head;
        for(int i=0;i<=pos-1;i++){
           cur =cur.next;
        }
        Entry entry=new Entry(val);
        entry.next=cur.next;
        cur.next=entry;
    }
    /*
     *   删除单链表当中所有值为val的节点
     */

    public void delete(int val){

        Entry cur1=this.head;
        Entry cur2=cur1.next; //需要注意head后第一个元素就为val的情况。
        while( cur2!=null ){
            if(cur2.data==val){
               cur1.next=cur2.next;
               cur2=cur1.next;
            }else {
                cur1=cur1.next;
                cur2=cur2.next;
            }
        }
        show();
    }
    public boolean deleteVal(int val){
        Entry pre=this.head;
        Entry cur=this.head.next;
        if(cur==null){
            return false;
        }
        while(cur!=null){
            if(cur.data==val){
                pre.next=cur.next;
                cur=pre.next;
            }else {
                pre=cur;
                cur=pre.next;
            }
        }
        return true;
    }

    /*
     * 打印单链表所有的数据
     */

    public void show(){
        Entry cur=this.head.next;
        while(cur !=null){
            System.out.println(cur.data);
            cur=cur.next;
        }
    }
    /**
     * @Description : 查找倒数结点
     * @param k
     * @return :   int
     * @exception :
     * @date :   2018/11/25 19:22
     */
    public int  lastK(int k) throws UnsupportedOperationException{
        if(k<0||k>getLength()){
            throw new UnsupportedOperationException("k不合法");
        }else{
            Entry pre=this.head;
            Entry cur=this.head;
            for (int i = 0; i <k-1 ; i++) {
                cur=cur.next;
            }
            while (cur.next!=null){
                pre=pre.next;
                cur=cur.next;
            }
            return pre.data;
        }

    }
    /**
     * @Description :  删除倒数k的链；
     * @param k
     * @return :   void
     * @exception :
     * @date :   2018/11/25 19:43
     */
    public void deleteLastK(int k) throws UnsupportedOperationException{
        if(k<0||k>getLength()){
            throw new UnsupportedOperationException("K不合法");
        }
        Entry pre=this.head;
        Entry cur=pre;
        for(int i=0;i<k;i++){
            cur=cur.next;
        }
        while(cur.next!=null){
            pre=pre.next;
            cur=cur.next;
        }
        pre.next=pre.next.next;
    }
    public void createLoop(){
        /**
         * @Description :
         * @param 
         * @return :   void
         * @exception :  
         * @date :   2018/11/26 11:41
         */
        Entry cur=this.head;
        while(cur.next!=null){
            cur=cur.next;
        }
        cur.next=this.head.next.next; //在第二个数据节点构成环。
    }
    public Boolean isLoop(){
        /**
         * @Description :  是否有环
         * @param
         * @return :   java.lang.Boolean
         * @exception :
         * @date :   2018/11/25 19:48
         */
        Entry fast =this.head;
        Entry slow=this.head;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if (slow==fast){
                return true;
            }
        }
        return false;
    }

    public int enterEntry(){
        /**
         * @Description : 求入口点
         * @param
         * @return :   int
         * @exception :
         * @date :   2018/11/25 20:52
         */
        Entry fast =this.head;
        Entry slow=this.head;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if (slow==fast){
               break;
            }
        }
        if(fast==null||fast.next==null){
            return -1;
        }
        slow=this.head;
       while(fast != slow){
            fast=fast.next;
            slow=slow.next;
       }
       return slow.data;
    }
    public int loopLen(){
      /**
       * @Description : 环的长度
       * @param
       * @return :   int
       * @exception :
       * @date :   2018/11/25 21:03
       */
        Entry fast =this.head;
        Entry slow=this.head;
        boolean flg=false;
        int count=0;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow && flg ==true){ //第二次相遇
                break;
            }
            if (slow==fast && flg==false){ //第一次相遇
                flg=!flg;

            }
            if(flg==true){
                count++;
            }
        }
       return count;

    }
    public void reverse(){
        Entry cur=this.head.next;
        Entry curNext=null; //cur.next

    }

    public Entry getHead() {
        return head;
    }

    public void reverseInsertHead(){
        /**
         * @Description : 单链表的逆置；头插法逆置
         * @param
         * @return :   void
         * @exception :
         * @date :   2018/11/26 10:48
         */
        Entry cur =this.head;
        Entry pre=this.head;
        int len=getLength();
        for(int i=len;i>1;i--) {
            boolean bl=false;
            while (bl==false) {
                if (cur.next != null) {
                    pre = pre.next;
                    cur = cur.next;
                }else{
                    cur.next = this.head.next;
                    this.head.next = cur;
                    bl=true;
                }
            }
        }
    }
    public void reverseInsertHead1(){
        Entry tail=this.head.next; //尾巴
        Entry cur1=this.head.next.next;//需要头插的链表
        Entry cur2=cur1.next; //需要头插的下一位的链表；
        while(cur1!=null){
            cur1.next=this.head.next; //连接头部后的链表
            tail.next=cur2; //尾巴连接上后面的链表
            this.head.next=cur1; //头部链接新插的链表
            cur1=cur2;//下一次需要头插的链表。
            if(cur2!=null)
                cur2=cur2.next; //下一次需要与尾巴连的链表

        }

    }
    public Entry reverseLink(){
        /**
         * @Description :  单链表反转
         * @param
         * @return :   Entry
         * @exception :
         * @date :   2018/11/28 19:16
         */
        Entry prev= null;
        Entry reverseHead =this.head;
        Entry cur=this.head;
        Entry curNext=cur.next;
        while(cur!=null){
            cur.next=prev;
            if(curNext==null){
                 reverseHead=cur;
                 break;
             }
            prev=cur;
            cur=curNext;
             curNext=curNext.next;
        }
        return reverseHead;
    }
    public boolean deleteEntry(Entry entryStart,Entry entryDelete){  //o1 时间删除
        /**
         * @Description : 删除；
         * @param entryStart
        * @param entryDelete
         * @return :   boolean
         * @exception :
         * @date :   2018/11/28 20:22
         */
        Entry cur=entryStart;
        if(entryStart==entryDelete){ //只有一个结点
            entryDelete=null;
            entryStart=null;
            return true;
        }
        if(entryDelete.next!=null){  //删除的是中间的结点
            Entry entryDeleteNext=entryDelete.next;
            entryDelete.data=entryDeleteNext.data;
            entryDelete.next=entryDeleteNext.next;
            entryDeleteNext=null;
            return true;
        }
        if(entryDelete.next==null){  //删除尾结点
            while(cur.next!=entryDelete){ // 找到尾结点的前驱
                cur=cur.next;
            }
            cur.next=null;
            entryDelete=null;
            return true;
        }
        return false;

    }

}
public class TestLinkDemo {

    public static boolean isCut(TestLink testLink1,TestLink testLink2){
        /**
         * @Description :  判断是否相交；
         * @param testLink1
        * @param testLink2
         * @return :   boolean
         * @exception :
         * @date :   2018/11/28 21:27
         */
        TestLink.Entry head1=testLink1.getHead(); //长
        TestLink.Entry head2=testLink2.getHead();//短
        int len1=testLink1.getLength();
        int len2=testLink2.getLength();
        int myLen=len1-len2;
        if(myLen<0){
            head1=testLink2.getHead();
            head2=testLink1.getHead();
            myLen=len2-len1;
        }
        while(myLen>0){
            head1=head1.next;
            myLen--;
        }
        while (head1!=null){
            if(head1==head2){
                return true; // 交点；
            }
            head1=head1.next;
            head2=head2.next;
        }
        return false;
    }
    public static TestLink.Entry mergeLink( TestLink link1, TestLink link2){
        TestLink.Entry p1=link1.getHead();
        TestLink.Entry p2=link2.getHead();
        TestLink.Entry newHead=null;
        if(p1.next.data>p2.next.data){
            newHead=link2.getHead();;
        }else{
            newHead=link1.getHead();;
        }
        TestLink.Entry headTmp=newHead;

        while(p1!=null&&p2!=null){
            if(p1.data<p2.data){
               headTmp.next=p1;
               p1=p1.next;

            } else{
              headTmp.next=p2;
              p2=p2.next;
            }
            headTmp=headTmp.next;
        }
            if(p1!=null){
                headTmp.next=p1;
            }else{
                headTmp.next=p2;
            }
      return newHead;

    }
    public static void main(String[] args) throws Exception {
        TestLink testLink = new TestLink();
        /*testLink.insertHead(10);
        testLink.insertHead(20);*/

        for (int i = 0; i < 10; i++) {
            testLink.insertTail(i);//
        }
        testLink.show();
        testLink.insertPos(999, 5);
        testLink.show();
        testLink.delete(1);


    }

    }



