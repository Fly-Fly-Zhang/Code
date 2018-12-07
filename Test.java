import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Class.forName;

/**
 * @Created with IntelliJ IDEA
 * @Description:
 * @Package: PACKAGE_NAME
 * @author: FLy-Fly-Zhang
 * @Date: 2018/11/30
 * @Time: 17:56
 */
class MinStack {
    int [][] elem;
    int top;
    /** initialize your data structure here. */
    public MinStack() {
        this.elem=new int[5][2];
        this.top=0;
    }

    public void push(int x) {
        if(top==0){
            this.elem[top][0]=x;
            this.elem[top][1]=x;
        }

        if(this.elem.length==top){
            this.elem=Arrays.copyOf(this.elem,this.elem.length);

        }
        if(top>0){

            if(this.elem[top-1][1]<x){
                this.elem[top][0]=x;
                this.elem[top][1]=this.elem[top-1][1];
            }else{
                this.elem[top][0]=x;
                this.elem[top][1]=x;
            }
        }
        top++;
    }

    public void pop() throws Exception {
        if(top==0){
            throw new Exception("栈为空");
        }
        top--;
    }

    public int top() {
        return this.elem[top-1][0];
    }

    public int getMin() {
        return elem[top--][1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
class Solution {
    public boolean isValid(String s) {

        if(s.length()%2!=0){ //奇数个直接false
            return false;
        }
        int i=0;
        while(i+1<s.length()){
            switch (s.charAt(i)){
                case '(':
                    if(s.charAt(i+1)!=')')
                        return false;
                case '[' :
                    if(s.charAt(i+1)!=']')
                        return false;
                case '{' :
                    if(s.charAt(i+1)!='}')
                        return false;

            }
            i=i+2;
        }
        return true;
    }
}
    public class Test {
       public static void remain(int num){
           switch (o){
               def
           }
       }
        public static void main(String[] args)  {
            System.out.println(2&1);
        }
    }

