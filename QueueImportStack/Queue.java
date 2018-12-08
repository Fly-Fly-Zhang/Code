package QueueImportStack;

import java.util.Arrays;

/**
 * @Created with IntelliJ IDEA
 * @Description:
 * @Package: QueueImportStack
 * @author: FLy-Fly-Zhang
 * @Date: 2018/12/5
 * @Time: 10:42
 */
public class Queue {
      private int front;
      private int rear;
      private int [] elem;
      private int allSize;
      private int usedSize;
      public Queue(int size){
          this.allSize=size;
          this.elem=new int [size];
          this.front=0;
          this.rear=0;
          this.usedSize=0;
      }

    public int getUsedSize() {
        return usedSize;
    }

    public boolean isFull(){
          if((this.rear+1)%this.allSize==this.front){
              return true;
          }
          return false;
      }
      public void push(int val){
          if(isFull()){
              this.allSize *= 2;
              this.elem= Arrays.copyOf(this.elem,this.allSize);
          }
          this.elem[this.rear]=val;
          this.rear=(this.rear+1)%allSize;
          this.usedSize++;
      }
      public boolean isEmpty(){
          if(this.rear==this.front){
              return true;
          }
          return false;
      }
      public int pop(){
          if(isEmpty()){
              throw new UnsupportedOperationException("队列为空");
          }
          this.usedSize--;
          int tmp=elem[front];
          this.front=(this.front+1)%allSize;
          return tmp;
      }
}
