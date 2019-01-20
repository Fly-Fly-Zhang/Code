 - [ ] 选择排序  ：请看后文 
 - [ ] 直接插入排序  : 请看后文
 - [ ] shell排序 : 请看后文
 - [ ] 冒泡排序  :  请看后文
 - [ ] 快速排序    [快速排序请点此链接](https://blog.csdn.net/Fly_Fly_Zhang/article/details/84994155)
 - [ ] 堆排    [堆排请点此链接](https://blog.csdn.net/Fly_Fly_Zhang/article/details/86213712)
 - [ ] 归并排序    [归并排序请点此链接](https://blog.csdn.net/Fly_Fly_Zhang/article/details/86163425)
####  七种排序的时间复杂度，空间复杂度，稳定性分析
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190120014301642.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0ZseV9GbHlfWmhhbmc=,size_16,color_FFFFFF,t_70)   
####    选择排序：
#####   思想： 从0号角标开始，与后面每一项进行比较，如果前面的比后面的大，那么交换，这样一次循环后将最小的值放到当前i号角标下。
```
public class sort{
   /*
         * @Description : 选择排序
         * @param array
         * @return :   void
         * @exception :  
         * @date :   2019/1/19 16:17
         */
    public static  void selectionSort(int [] array){
        for(int i=0;i<array.length;i++){
            for(int j=i+1;j<array.length;j++){
                if(array[i]>array[j]){
                    int tmp=array[j];
                    array[j]=array[i];
                    array[i]=tmp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }
    public static void main(String[] args) {
        int[] arr={3,1,2,5,4,7,6};
         selectionSort(arr);
    }

```
####   直接插入排序
#####   思想：如果有如下一组数字：4,2,6,5,3 首先当我们在最开始的时候，4 是有序的，然后当拿到数据 2 时，我们需要把 2 放到 4 之前，那也就是说，我们需要让 4 往后移，然后插入 2，以此类推，我们每次在移动的时候，是比较一个数字，移动一个数字，并且是从后往前移动。这样下来之后，时间复杂度会是 O(n^2),但是，当给出的数据是有序的时候，时间复杂度就变成了 0(n).这个很重要！（稳定）
#####   代码实现：

```
 public static  void insertSort(int [] array){
        int j;
        for(int i=1;i<array.length;i++){
            int tmp=array[i];
            for(j=i-1;j>=0;j--){
                if(array[j]>tmp){
                    array[j+1]=array[j];
                }else {
                    break;
                }
            }
            array[j+1]=tmp; //这句不能放break前面；因为如果是有序的数组，那么永远不会进else语句
        }
        System.out.println(Arrays.toString(array));
    }
    public static void main(String[] args) {
        int[] arr={4,2,6,5,3 };
         insertSort(arr);

    }
```

####  shell排序：
#####  思想：采用分组的思想，把一组数据分为若干小组，而在分组的过程当中，并不是紧挨着的元素分组，而是采用特定的分组方式，每一次都让组内有序，这样排序的好处是每次排序的时候都把小数据尽量往前赶，大的数据尽量往后赶。在这里面，每一组的数字个数，在每次分组的时候，都会缩小增量，如第一次每组三个，第二次五个，第三次每组一个去分（最后一次等于直接进行一次插入排序，优点在于，如果不执行前面的分组过程，数据的移动次数更多，更复杂，经过前面几次排序后，数据已经越来越有序了。shell排序就是一种优化版的直接插入排序，插入排序的特点是越有序时间复杂度越低）
#####   好的增量序列的共同特征：

 1. 最后一个增量必须为1。
 2. 应该尽量避免序列中的值（尤其是相邻的值）互为倍数的情况。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190120014243837.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0ZseV9GbHlfWmhhbmc=,size_16,color_FFFFFF,t_70)
#####   代码实现：

```
           /*
            * @Description :shell 排序
            * @param array
            * @param gap
            * @return :   void
            * @exception :
            * @date :   2019/1/20 2:27
            */
    public static void shell(int [] array,int gap){  //gap组数
        for(int i=gap;i<array.length;i++){ //以五组进行分析，gap=5是对应的是橙色线组内的第二个元素，=6时对应红色线组内第二个元素
            //当=10时对应的时橙色线组内的第三个元素，依此类推；
            int tmp=array[i];
            int j=0;
            for(j=i-gap;j>=0;j=j-gap){ //组内进行插入排序
                if(array[j]>tmp){
                    array[j+gap]=array[j];
                }else{
                    break;
                }
            }
            array[j+gap]=tmp;
        }
    }
    public static void shellSort(int [] array){
        int [] brr={5,3,1};
        for(int i:brr){
            shell(array,i);
        }
        System.out.println(Arrays.toString(array));
    }
    public static void main(String[] args) {
        int[] arr={12,5,9,34,6,8,33,56,89,0,7,4,22,55,77};

         shellSort(arr);

    }

```

####   冒泡排序：
#####  思想： 从数组第一个元素开始，如果当前元素比后一个元素大，那么两个元素进行交换，直到本次循环结束，最后一位为最大的值。依此类推，为第n大的值；
#####  优化：

 1. 引入一个boolean标签swap;在每趟排序开始之前将其置为false；如果发生了交换，将其置为true。当一次循环结束后boolean标签仍为false说明没有发生交换，那么此时数组已经变成有序的，没有必要在进行排序；
 2. 在每趟扫描中，记住最后一次交换发生的位置，此位置之后肯定是有序的，之前是无序的；

#####   代码实现：

```
public static void swap(int [] array,int i,int j){
        int tmp=array[i];
        array[i]=array[j];
        array[j]=tmp;
    }
    //未优化的普通冒泡排序；
    public static void bubbleSort(int [] array){
           for(int i=0;i<array.length-1;i++){
               for(int j=0;j<array.length-1-i;j++){
                   if(array[j]>array[j+1]){
                       swap(array,j,j+1);
                   }
               }
           }
        System.out.println(Arrays.toString(array));
    }
    //优化后的冒泡排序；
    public static void bubbleSort1(int [] array){
           int k=array.length-1;
           for(int i=0;i<array.length-1;i++){
               //boolean bl=false;
               int num=-1;
               for(int j=0;j<k;j++){
                   if(array[j]>array[j+1]){
                       //bl=true;
                       swap(array,j,j+1);
                       num=j;
                   }
               }
/*
               if(num!=-1)  //最后一次交换之后都有序，之前都无序；
                   k=num;
               if(bl==false)  //本次循环未发生交换，说明数组有序
                   break;

*/
               if(num==-1){  //合并优化处理
                   break;
               }else{
                   k=num;
               }

           }
        System.out.println(Arrays.toString(array));
    }
    public static void main(String[] args) {
        int[] arr={3,1,2,5,4,7,6};
         bubbleSort(arr);

    }

```


  
 


