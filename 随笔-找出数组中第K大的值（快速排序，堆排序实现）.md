####  题目： 找出数组中第K大的值，要求对数组不能进行排序，时间复杂度为O(nlog2k)  
####    快速排序实现
#####  思路：这个链接为 [快速排序的原理（从小到大排序）](https://blog.csdn.net/Fly_Fly_Zhang/article/details/84994155)   如果不懂快排建议先看此链接， 题目的要求是找第K大的数，那么我们将快速排序转换为从大到小排序进行分析。  找第k大的数，其实就是找角标k-1的数， 当par 大于k-1 时，那么par的左边有par个大的数 所以在左边进行查找；当par小于k-1时，par 左边相对于整个数组来说相对大的数为 par个，是小于k的 ，所以在右边进行查找 ；  当par=k-1是左侧正好有k的相对于整个数组来说相对大的数；
####   注意:左侧的数并不是从大到小排好序的，只是当par=k-1时，左侧的数都>=array[k-1] ，右边的都<=array[k-1]  所以array[par]正好为第k大的数
#####   代码实现：

```
public class Demo {

     /*
         * @Description : 一次找基准
         * @param array
        * @param low
        * @param high
         * @return :   int
         * @exception :  
         * @date :   2019/1/18 23:54
         */
    public  static int  partion(int [] array,int low,int high){
       
        int tmp=array[low];
        while(low<high){
            while(low<high && array[high]<=tmp){ //因为tmp为array[low] 所以从high开始找
                high--;
            }
            if(high<=low){
                break;
            }else{  //将比tmp大的值放在左边
                array[low]=array[high];
            }
            while(low<high && array[low]>=tmp){
                low++;
            }
            if(low>=high){
                break;
            }else{
                array[high]=array[low]; //将比tmp小的值放在右边
            }

        }
        return low;
    }
    /*
    * @Description :快速排序
    * @param k
   * @param array
   * @param low
   * @param high
    * @return :   void
    * @exception :  
    * @date :   2019/1/18 23:54
    */
    public static void find_K(int k,int [] array,int low,int high ){
   
        int par=partion(array,low,high);
        if(par==k-1){ //正好左侧有k个数；
            System.out.println(array[k - 1]);
        }else if(par>k-1){  //当比k-1 大时，在左边进行查找
            find_K(k,array,low,par-1);
        }else if(par<k-1){  //比k-1小时，在右边进行查找
            find_K(k,array,low+1,high);
        }
    }
    public static void main(String[] args) {
        int[] arr={3,1,2,5,4,7,6};
        find_K(1,arr,0,arr.length-1);
    }

}

```

####   堆排实现
#####   思路： 堆排不懂的请点击此链接  [堆排序](https://blog.csdn.net/Fly_Fly_Zhang/article/details/86213712)    根据大根堆的特点，当我们调整的时候，只需要调整k次，那么就会得到第K大的数；即array[array.length-k]
#####   代码实现 ：

```
public class  Demo {
    /*
         * @Description : 建立大根堆
         * @param array
        * @param start
        * @param end
         * @return :   void
         * @exception :  
         * @date :   2019/1/19 0:47
         */
    public static void adjust(int [] array,int start,int end){
        
           int tmp=array[start];
          for(int i=start*2+1;i<=end;i=i*2+1){  //i为start对应的左孩子
               if(i<end && array[i]<=array[i+1]){  // 保证i为最大的那个孩子；
                   i++;
               }
               if(array[i]>tmp){
                   array[start]=array[i];
                   start=i;
               }else{
                   break;
               }
           }
           array[start]=tmp;
    }
    /*
         * @Description : 寻找第K大的值
         * @param array
        * @param k
         * @return :   void
         * @exception :  
         * @date :   2019/1/19 0:48
         */
    public static void find_K(int [] array,int k) throws Exception {
        
        if(k>array.length)
            throw new Exception("k值大于数组长度");
        //建立大根堆
        for(int i=(array.length-1-1)>>1 ;i>=0;i--){ //子找父
            adjust(array,i,array.length-1);
        }
        //调整，建立大根堆
        for(int j=0;j<k;j++){  //只排序k 次。
            int tmp=array[array.length-1-j];
            array[array.length-1-j]=array[0];
            array[0]=tmp;
            adjust(array,0,array.length-1-j-1);
        }
        System.out.println(array[array.length-k]);
        /*
        for(int j=0;j<k-1;j++){  //只排序k-1次。   //小优化，比上面少排序一次，因为大根堆最上面即为最大的，
        //所以排序k减一次后，大根堆最上面既为第k大的值，直接输出就好；
            int tmp=array[array.length-1-j];
            array[array.length-1-j]=array[0];
            array[0]=tmp;
            adjust(array,0,array.length-1-j-1);
        }
        System.out.println(array[0]);
        */
    }
    public static void main(String[] args) {
        int[] arr={3,1,2,5,4,7,6};
        try{
            find_K(arr,8);
        }catch (Exception e){
            System.out.println(e);
        }

    }

}

```

