package StackImportQueue;

import java.util.Arrays;

/**
 * @Created with IntelliJ IDEA
 * @Description: 栈
 * @Package: QueueImportStack
 * @author: FLy-Fly-Zhang
 * @Date: 2018/12/5
 * @Time: 9:08
 */
public class Stack <T > {
    private int top;
    private T[] elem;
    private boolean bl = false;
    public Stack(){
        this.elem=(T[]) new Object[10];
        this.top=0;
    }

    public boolean getBl() {
        return bl;
    }

    public void setBl(boolean bl) {
        this.bl = bl;
    }

    public int getTop() {
        return top;
    }

    /*
     * @Description :栈是否满
     * @param null
     * @return :
     * @exception :
     * @date :   2018/12/5 9:17
     */
    public boolean isFull(){
        if(this.top==this.elem.length){
            return true;
        }
        return false;
    }
    /*
     * @Description :入栈
     * @param val
     * @return :   void
     * @exception :
     * @date :   2018/12/5 9:16
     */
    public void push(T val){

        if(isFull()){
            this.elem=Arrays.copyOf(this.elem,this.elem.length);
        }
        this.elem[top++]=val;
    }
    /*
     * @Description : 判断栈是否为空
     * @param null
     * @return :
     * @exception :
     * @date :   2018/12/5 9:20
     */
    public boolean isEmpty(){
        if(this.top==0){
            return true;
        }
        return false;
    }
    /*
     * @Description :
     * @param
     * @return :   void
     * @exception :
     * @date :   2018/12/5 9:22
     */
    public T pop(){
        if(isEmpty()){
            throw new UnsupportedOperationException("栈为空");
        }
        this.top--;
        return this.elem[top];
    }
    public void show(){
        System.out.println("栈中元素为：[ ");
        for(int i=0;i<this.top;i++){
            System.out.print(this.elem[i]+"; ");
        }
        System.out.println("]");
    }
}

