package com.tuln.datastract;

/**
 * @Created with IntelliJ IDEA
 * @Description: 循环队列
 * @Package: com.tuln.datastract
 * @author: FLy-Fly-Zhang
 * @Date: 2018/12/4
 * @Time: 20:44
 */

class QueueDemo{
    private int front;//队头
    private int rear;//队尾
    private int[] elem;
    private int usedSize=0;
    private int allSize=10;
    public QueueDemo(){
        this(10);
    }
    public QueueDemo(int val){
        this.elem=new int [val];
        this.front=0;
        this.rear=0;
    }
    //入队
    public void push(int val){
        if(isFull()){
            throw  new UnsupportedOperationException("队列已满");
        }
        elem[this.rear]=val;
        rear=++rear % allSize;
        this.usedSize++;
    }
    //出队
    public void pop(){
        if(isEmpty()){
            throw new UnsupportedOperationException("栈为空");
        }
        front = ++front%allSize;
        this.usedSize--;

    }
    //空
    public boolean isEmpty(){
        if(rear==front){
            return true;
        }
        return false;
    }
    //满
    public Boolean isFull(){
        if((rear+1)%allSize==this.front){
            return true;
        }
        return false;
    }
    //打印
    public void show(){
        int i=front;
        System.out.println("队列元素为： ");
        while(i!=rear){
            System.out.print(elem[i]+" ");
            i=(i+1)%allSize;
        }
    }
}
public class TestQueueDemo {


}
