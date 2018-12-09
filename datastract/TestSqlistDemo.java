package com.tuln.datastract;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Created with IntelliJ IDEA
 * @Description:
 * @Package: com.tuln.datastract
 * @User: FLy
 * @Date: 2018/11/21
 * @Time: 19:04
 */

class TestSqlist{ //顺序表
    private int [] elem;  //
    private int usedsize; //有效数据个数
    public TestSqlist(){
        this(10);
    }
    public TestSqlist(int size){
        this.elem=new int[size];
        this.usedsize=0;
    }
    public boolean isFull(){ //判断数组是否装满
        if(usedsize==elem.length){
            return false;
        }
        return true;
    }
    public boolean Insert(int pos,int value){ //插入元素

        if(isFull()){  //数组扩容
            this.elem=Arrays.copyOf(this.elem,this.elem.length);
        }
        if(pos>usedsize||pos<0){
            return false;
        }else {
            for (int i = usedsize-1; i >=pos ; i--) {
                elem[i+1]=elem[i];
            }
            elem[pos]=value;
            usedsize++;
            return true;
        }
    }
    public boolean isEmpty(){
        if(usedsize==0){
            return true;
        }
        return false;
    }

    public int search(int key){ //查找数组元素
        if(isEmpty()){
            return -1;
        }

        for (int i = 0; i <usedsize ; i++) {
            if(this.elem[i]==key){
                    return i;
            }
        }
        return -1;
    }

    /**
     * @Description :删除关键字key  第一次开出现的key

     * @return :  boolean
     * @exception :
     * @date :   2018/11/24 15:40
     */
    public boolean delete(int key){
        int index=search(key); //调用查找方法，找到角标
        if(index<0){
            return false;
        }else {
            for (int i = index; i< this.usedsize-1  ; i++) {
                this.elem[i]=this.elem[i+1];
            }
            this.usedsize--;
            this.elem[usedsize]=0;
        }
        return true;
    }
    /*
     *得到pos位置的值
     */
    public int getpos(int pos){
        if(pos<0||pos>this.usedsize||isEmpty()){
         throw new UnsupportedOperationException("pos位置不合法或者顺序表为空");
        }
        show(elem[pos]);
        return this.elem[pos];
    }
    public static void show(int tmp){
        System.out.println(tmp);
    }
}
public class TestSqlistDemo {
    public static void main(String[] args) {

    }
}