####   题目：合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:

输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6
####   思路：创建一个新表头，然后将所有链表元素按照大小加入到新的链表中；
####   代码实现：
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
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null || lists.length==0){
            return null;
        }
        int i=0;
        ListNode head=new ListNode(0);//新链表表头
        ListNode pre=head;//新链表最后一个不为空的元素
        int tmp=-1;//记住每一轮当前最小值的坐标
        while(i<lists.length){
            while(lists[i]!=null && pre!=head&&lists[i].val==pre.val){
                 //这个循环会大幅度提升速度
                //先将所有与pre.val相同的元素加入新链表
                pre.next=lists[i];
                pre=pre.next;
                lists[i]=lists[i].next;
            }
            if(lists[i]!=null){//当前链表有元素
                if(tmp==-1){//先将本次循环第一个元素赋值给tmp；
                    tmp=i;
                }else{
                   if(lists[tmp].val>lists[i].val){//找到本轮循环最小的元素；
                       tmp=i;
                   } 
                }
            }
            if(i==lists.length-1){//本轮最后一个元素，将tmp放入新数组中
                if(tmp==-1){ //tmp处理本轮所有元素都为空的情况;
                    break;
                }
                pre.next=lists[tmp];
                pre=pre.next;
                lists[tmp]=lists[tmp].next;//已经加入新链表，原链表表头后移
                tmp=-1;
                i=-1;//下面会加一次，变为0，开始下一轮循环；
            }
            i++;
        }
        return head.next;//返回去掉我们添加的表头后的链表
    }
}
```
