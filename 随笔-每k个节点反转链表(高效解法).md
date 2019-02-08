####   题目：给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。

示例 :

给定这个链表：1->2->3->4->5

当 k = 2 时，应当返回: 2->1->4->3->5

当 k = 3 时，应当返回: 3->2->1->4->5

说明 :

你的算法只能使用常数的额外空间。
你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

####   思路：单独写一个反转的函数，只要n%k==0那么说明链表未反转的链表结点有k个，那么就进行一次反转；另外需要注意的是，k肯定是大于等于2的，这样反转链表才有意义；
####   代码实现:
```java

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    ListNode newTail=null;
    public void reverse(ListNode head,ListNode tail){
     //head为头结点，不带有任何参数，并且能调用这个函数，说明至少有两个及以上的节点需要逆置，
     //所以第一次的cur代表第二个元素，不会空指针
        ListNode cur=head.next.next;//准备前插的元素
        ListNode curNext=cur.next;//前插的下一个元素，可能为空
        while(cur!=null){
            //不需要判断cur和curNext为空的情况，while里面可以直接写true
            //因为tail是本次逆置部分的最后一个元素，cur前插肯定会遇见它
            cur.next=head.next;//连接后面
            head.next=cur;//连接头结点
            if(cur==tail){
                newTail.next=curNext;//将新链表的尾部与下一次k链的第一个元素进行连接
                break;
            }
            cur=curNext;//下一个需要头插的元素
            curNext=cur.next;
        }
        
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null||k<=1){
            return head;
        }
        ListNode newHead=new ListNode(-1);//每k个反转链表的前置头结点
        newHead.next=head;
        ListNode tail=head;//每一组链表的尾结点
        head=newHead;
        int n=1;
        while(tail!=null){
            if(n%k==0){
                newTail=newHead.next;//新的尾结点
                reverse(newHead,tail);
                newHead=newTail;//上一组的尾结点做下一组的头结点
                tail=newTail; 
            }
            tail=tail.next;
            n++;
        }
        return head.next;
    }
}
```
