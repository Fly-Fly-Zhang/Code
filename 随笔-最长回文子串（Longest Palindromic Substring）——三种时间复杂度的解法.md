####   题目：给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"

####   方法一：暴力解法；
#####   采用滑动窗口，先对最长的字符子串进行判断，下来对长度减一的几条子串进行判断；依此类推进行比对；
#####  时间复杂度：O(n3)
#####  代码实现：

```java
class Solution {
    public boolean isPalindromicNumber(char[] ch,int start,int end){
        //判断是否为回文串
        while(start<end){
            if(ch[start]==ch[end]){
                start++;
                end--;
            }else{
                return false;
            }
        }
        return true;
    }
    public String longestPalindrome(String s) {
         if(s==null || s.length()<=1){
             return s;
         }
         char [] ch=s.toCharArray();
         int len=ch.length;//目前检测的子串的长度
         while(len>0){
            for(int i=0;i<=ch.length-len;i++){//采用滑动窗口，将符合长度的子串都进行一次判断
               boolean bl=isPalindromicNumber(ch,i,i+len-1);
               if(bl){
                 return s.substring(i,i+len);
               }
            }
            len--; 
         }
         return "";
    }
}
```
####   方法二：动态规划，从中心向外进行扩散；
#####   思路：我们将s中任何一点（奇数回文串是一点用i表示，偶数是两点用i和i+1表示）作为回文串的中点，然后设置low和high表示其中点左右坐标；那么当low和high的元素相同时，回文串的长度加2，此时与已知的最长回文子串进行比较，找到最长的回文串
#####   时间复杂度：O(n2)
```java
class Solution {
    private String sub="";
    private int maxLen=0;
    private void findMaxLongPalindromicNumber(String s,int low,int high){
          int low1=low;
          while(low>=0&&high<s.length()){//向两边扩散
              if(s.charAt(low)==s.charAt(high)){
                  if(maxLen<high-low+1){
                      //当前最长回文子串的长度大于maxLen保存的,将长的进行保存;
                      maxLen=high-low+1;
                      sub=s.substring(low,high+1);//子串不包含右，所以加一
                  }
                  low--;
                  high++;
              }else{
                 break;
              }
          }
    }
    public String longestPalindrome(String s) {
         if(s==null || s.length()<=1){
             return s;
         }
         for(int i=0;i<s.length()-1;i++){
             findMaxLongPalindromicNumber(s,i,i);//奇数回文串
             findMaxLongPalindromicNumber(s,i,i+1);//偶数回文串
         }
         return sub;
    }
}
```
####   注意：还有一种O（n)的Manacher算法；小生暂未掌握，将我觉得讲的不错的算法链接地址贴上用以膜拜大神 [Manacher算法](https://blog.csdn.net/qq_32354501/article/details/80084325)
