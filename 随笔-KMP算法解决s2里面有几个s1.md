####  时间复杂度O(n+m)
####  问题：s1="asc" s2="ababcdd"
####  思路：KMP不再多说，如果查找到，那就返回的下标+1继续找

```
public class{
           public static int [] next(String sub,int [] next){
                   next[0]=-1;
                   if(next.length==1){
                       return next;
                   }
                     next[1]=0;
                   if(next.length==2){
                        return next;
                   }
                   int j=2;
                   int k=0;//j-1 的k值；
                   while(j<sub.length()){
                           if(k==-1||sub.charAt(j-1)==sub.charAt(k) ){
                                   next[j]=k+1;
                                    k=k+1;
                                    j++;
                            }else{
                                     k=next[k];

                            }
                   }
                   return next;
           }
           public  static int kmp(String str , String sub, int index){ 
                       if(str.length()==0||sub.length()==0){
                               return -1;
                       }
                       if(str.length()<sub.length()){
                              String tmp=sub;
                              sub=str;
                              str=tmp;
                              
                       }
                       int i=index;
                       int j=0;
                       int num=0;
                       int [] next=new int [sub.length()];
                       next(sub,next);
                       while(i<str.length()){
                              if(j==sub.length()){
                                    num++;
                                    i=i-j+1; //匹配成功角标的下一位；
                                    j=0;
                              }
                              if(j==-1||str.charAt(i)==sub.charAt(j)){
                                    i++;
                                    j++;
                              }else{
                                    j=next[j];
                              }
                       
                       }
                        if(j==sub.length()&&i==str.length()) {
                                num++;
                        }
                       return num;
           }
           public static void main(String args [ ] ){
                      String s1="abc";
                      String s2="ababcdd";
                      System.out.println(kmp(s2,s1,0));
           }
}
```

