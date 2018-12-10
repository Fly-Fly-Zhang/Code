#### 时间复杂度: O(nm) ;
#### 需求：解决字符串主串是否包含子串的问题；
####  思路：采用双指针；主串i 字串 j   当i与j相同时 i++，j++，如不同，j=0； i回退上次开始比较的的位置+1    匹配成功返回第一个字符的下标；

```
class BF{
        public static  int  bf(String str,String sub){
                   int i=0;
                   int j=0;
                   while(i<str.length()&&j<sub.length()){
                            if(str.charAt(i)==sub.charAt(j)){
                                i++;
                                j++；
                            }else{
                                i=i-j+1; //回退到i上次比较起始位置+1；
                                j=0;
                            }
                   }
                   if(j==sub.length()){
                       return i-j ; //找到返回下标
                   }
                   return -1;
        }
}
```

