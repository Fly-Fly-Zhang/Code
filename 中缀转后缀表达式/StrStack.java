package tulun.work;

import java.util.Arrays;

/**
 * @Created with IntelliJ IDEA
 * @Description:
 * @Package: tulun.work
 * @author: FLy-Fly-Zhang
 * @Date: 2018/12/3
 * @Time: 22:43
 */
public class StrStack {
    private int top;
    private String [][] elem;
    public StrStack(){
        this.elem=new String[5][2];
        this.top=0;
    }
    public boolean isFull(){
        if(elem.length==top){
            return true;
        }
        return false;
    }
    public void push(String val,int series){
        if(isFull()){
            elem=Arrays.copyOf(elem,elem.length);
        }
        this.elem[top][0]=val;
        this.elem[top][1]=String.valueOf(series);
        top++;
    }
    public boolean isEmpty(){
        if(top==0){
            return true;
        }
        return false;
    }
    public void pop() throws NullPointerException{
        if(isEmpty()){
            throw new NullPointerException("栈为空");
        }
        top--;
    }
    public int  getTop(){
        return top;
    }

    public int  getElemSer() {
        return Integer.parseInt(elem[top-1][1]);
    }
    public String  getElemVal() {
        return elem[top-1][0];
    }
    public void show(){
        System.out.println("栈内元素为[ ");
        for (int i = 0; i <top ; i++) {
            System.out.println(elem[top][0]+" ");
        }
        System.out.println("]");
    }
}











