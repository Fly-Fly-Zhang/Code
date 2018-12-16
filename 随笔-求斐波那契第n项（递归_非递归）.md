####  题目：
大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
n<=39
####   思路：第N项为n-1  +  n-2 和；
####   递归：

```
public class Solution {
    public int Fibonacci(int n) {
         if(n==0){
             return 0;
         }else if(n==1){
             return 1;
         }
         return Fibonacci(n-1)+Fibonacci(n-2);
    }
}
```
####   非递归:

```
public class Solution {
    public int Fibonacci(int n) {
         if(n==0){
             return 0;
         }else if(n==1){
             return 1;
         }
         int n1=0;
         int n2=1;
        for(int i=2;i<=n;i++){
            int tmp=n1+n2;
            n1=n2;
            n2=tmp;
        }
        return n2;
    }
}
```

