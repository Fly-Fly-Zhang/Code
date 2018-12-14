####   题目：给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

注意：

num1 和num2 的长度都小于 5100.
num1 和num2 都只包含数字 0-9.
num1 和num2 都不包含任何前导零。
你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
####   思路：从两个字符串最后一位开始相加，如果结果大于9，进位。 当有一个加完后，调用substring方法将剩余的字符串给拼接过去；
```
class Solution {
    public String addStrings(String num1, String num2) {
        if(num1==null || num2==null || num1.length()==0 || num2.length()==0 ){
            return "";
        }
        int len1=num1.length()-1;
        int len2=num2.length()-1;
        int result=0;
        String s="";
        int sum;
        while(len1>=0||len2>=0){
            if(len1<0&&len2>=0){  //num2 没加完
                 sum=num2.charAt(len2)+result-'0'; 
                if(result!=0){
                    if(sum>9){
                    result=1;
                    s=String.valueOf(sum%10)+s;
                    }else{
                    s=String.valueOf(sum%10)+s;
                      result=0;   
                    }
                }else{
                    s=num2.substring(0,len2+1)+s;
                    result=0;
                    break;
                }
            }
             if(len1>=0&&len2<0){
                 sum=num1.charAt(len1)+result-'0'; 
                if(result!=0){
                    if(sum>9){
                    result=1;
                    s=String.valueOf(sum%10)+s;
                    }else{
                    s=String.valueOf(sum%10)+s;
                      result=0;   
                    }
                }else{
                    s=num1.substring(0,len1+1)+s;
                    result=0;
                    break;
                }
            }
            if(len1>=0&&len2>=0){
                sum=num1.charAt(len1)+num2.charAt(len2)+result-2*'0';
                if(sum>9){
                    result=1;
                    s=String.valueOf(sum%10)+s;
                }else{
                    s=String.valueOf(sum)+s;
                    result=0;
                }
            }
            len1--;
            len2--;
        }
        if(result!=0){
            s=Integer.toString(result)+s;
        }
        return s;
    }
}
```

