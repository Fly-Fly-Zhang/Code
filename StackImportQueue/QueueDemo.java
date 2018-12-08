package StackImportQueue;

/**
 * @Created with IntelliJ IDEA
 * @Description: 栈实现队列
 * @Package: QueueImportStack
 * @author: FLy-Fly-Zhang
 * @Date: 2018/12/5
 * @Time: 11:09
 */
public class QueueDemo {

    public static <T> void pushQueue(Stack s1,Stack s2,T val){
        s1.push(val);
    }
    public static <T> T popQueue (Stack s1,Stack s2) throws UnsupportedOperationException{
        if(s1.isEmpty()==true&&s2.isEmpty()==true){
            throw new UnsupportedOperationException("队列中无元素");
        }else if(s2.getBl()==true){
            if(s1.getTop()-1==0){
                s1.setBl(false);  //栈为空变为只能进入栈；
            }
            //return (T)s2.pop();
        } else if(s2.isEmpty()==true&&s1.isEmpty()==false){ //s1有元素，s2为空，将s1倒入s2;
            for(int i=s1.getTop();i>0;i--){
                s2.push(s1.pop());
            }
            if(s2.getTop()!=0){
                s2.setBl(true); //s1栈中元素全为可以直接出队列元素
            }
            //return (T)s2.pop();
        }
        return (T)s2.pop();
    }
    public static void main(String[] args) {
        Stack <Integer>s1=new Stack<Integer>();
        Stack <Integer> s2=new Stack<Integer>();

        for(int i=0;i<10;i++){
            pushQueue( s1, s2,i); //根据传入参数确定类型
        }
        int i=3;
        while(i>0){
            int s=popQueue(s1,s2);
          System.out.print(s);
            i--;
        }

    }
}
