package com.tuln.datastract;

import java.util.Arrays;

/**
 * @Created with IntelliJ IDEA
 * @Description:
 * @Package: com.tuln.datastract
 * @author: FLy-Fly-Zhang
 * @Date: 2018/12/2
 * @Time: 20:51
 */
class SqStackDemo {
    private int top;
    private int[] elem;
    public SqStackDemo() {
        this(10);
    }

    public SqStackDemo(int size) {
        this.top = 0;
        this.elem = new int[size];
    }
    public boolean isFull(){
        if(this.elem.length==top){
            return false;
        }
        return true;
    }
    //入栈
    public void push(int val){
        if(isFull()==false){
            elem=Arrays.copyOf(elem,elem.length*2);
        }
        this.elem[top]=val;
        this.top++;
    }
    public boolean isEmpy(){
        if(top==0){
            return false;
        }
        return true;
    }
    //出栈
    public void pop () throws Exception{
        if(isEmpy()==false){
            throw new Exception("栈中无元素");
        }
        this.top--;
    }
    //打印栈中元素
    public void show(){
        System.out.println("[ ");
        for (int i = 0; i <top-1 ; i++) {
            System.out.println(elem[i]+" ");
        }
        System.out.println("]");
    }
}

public class TestSqStackDemo {

}
