####   题目：
实现 strStr() 函数。

给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

示例 1:

输入: haystack = "hello", needle = "ll"
输出: 2
示例 2:

输入: haystack = "aaaaa", needle = "bba"
输出: -1
说明:

当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
####  库函数实现：

```
class Solution {
   
    public int strStr(String haystack, String needle) {
        if(haystack.length()<needle.length() || haystack==null||needle==null){
            return -1;
        }
        if(haystack.length()==0||needle.length()==0){
            return 0;
        }
        return haystack.indexOf(needle);   //查找字符串是不是包含此字符串；
    }
}
```

   
####   思路：具体实现为KMP算法实现 ，如果不了解KMP  请参考     [KMP算法详解](https://blog.csdn.net/Fly_Fly_Zhang/article/details/84942267)

```
class Solution {
    private int[] nextKmp(int [] next , String needle){
        if(next.length==1){
            next[0]=-1;
            return next;
        }
            next[0]=-1;
            next[1]=0;
        int k=0;
        int j=2;
        while(j<next.length){
            if(k==-1 || needle.charAt(j-1)==needle.charAt(k)){
                next[j]=k+1;
                j++;
                k++;
            }else {
                k=next[k];
            }
        }
        return next;
    }
    public int strStr(String haystack, String needle) {
        if(haystack.length()<needle.length() || haystack==null||needle==null){
            return -1;
        }
        if(haystack.length()==0||needle.length()==0){
            return 0;
        }
        int [] next=new int [needle.length()];
        next=nextKmp(next,needle);
        int i=0;
        int j=0;
        while(i<haystack.length()&&j<needle.length()){
            if(j==-1||haystack.charAt(i)==needle.charAt(j)){
                i++;
                j++;
            }else{
                j=next[j];
            }
           
        }
        
        if(j==needle.length()){
            return i-j;
        }
        return -1;
    }
}
```

