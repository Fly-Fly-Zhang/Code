package QueueImportStack;

/**
 * @Created with IntelliJ IDEA
 * @Description:
 * @Package: QueueImportStack
 * @author: FLy-Fly-Zhang
 * @Date: 2018/12/5
 * @Time: 11:08
 */
public class StackDemo {
    public static void enterStack(Queue q1,Queue q2,int val){
         if( q1.getUsedSize()!=0&&q2.getUsedSize()!=0 ){
            throw new UnsupportedOperationException("栈队列都不为空,无法存入");
         }
         if(q1.getUsedSize()==0&&q2.getUsedSize()==0){
             q1.push(val);
         }else if(q1.getUsedSize()!=0){
             q1.push(val);
         }else {
             q2.push(val);
         }
    }
    public static int popStack(Queue q1,Queue q2){
         if(q1.getUsedSize()==0&&q2.getUsedSize()==0 || q1.getUsedSize()!=0&&q2.getUsedSize()!=0 ){
             throw new UnsupportedOperationException("栈为空或者栈队列都不为空");
         }
         if(q1.getUsedSize()!=0){
            int i=q1.getUsedSize();
            while(i>1){
                q2.push(q1.pop()); //q2存入q1中输出元素
                i--;
            }
            return q1.pop();//最后一个元素pop出去
         }else{
             int i=q2.getUsedSize();
             while(i>1){
                 q1.push(q2.pop()); //q1存入q2中输出元素
                 i--;
             }
             return q2.pop();//最后一个元素pop出去
         }
    }
    public static void main(String[] args) {
        Queue q1=new Queue(10);
        Queue q2=new Queue(10);
        for (int i = 0; i <10 ; i++) {
            enterStack(q1, q2,i);
        }
        System.out.print(popStack(q1, q2));
        enterStack(q1, q2,20);
        System.out.print(popStack(q1, q2)+" ");
        System.out.print(popStack(q1, q2)+" ");
        System.out.print(popStack(q1, q2)+" ");
    }
}
