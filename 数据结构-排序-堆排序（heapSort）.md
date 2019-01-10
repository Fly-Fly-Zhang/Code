####   算法：（只支持从小到大排序）
已知父，推子：左孩子(2n+1) ; 右孩子（2n+2） ;
已知子，推父：子为n  ,父为（n-1）/2；
####   时间复杂度：nlog2n
####    步骤：
- [ ] 建立大根堆>>> 不断地调整 (先从最小的根堆开始，使父大于子，慢慢的变为大根堆)
- [ ] 交换-->>调整  （大根堆建立之后，将0号下标的值，依次与最后面角标的值进行交换，这样0号角标的最大值到最后面。）
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190110092458293.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0ZseV9GbHlfWmhhbmc=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190110092953515.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0ZseV9GbHlfWmhhbmc=,size_16,color_FFFFFF,t_70)

####   代码实现

```
package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @Created with IntelliJ IDEA
 * @Description:  堆排序
 * @Package: sort
 * @author: FLy-Fly-Zhang
 * @Date: 2019/1/9
 * @Time: 19:19
 */
public class TestHeapSort {

    public static void adjust(int [] array,int start ,int end){
        int tmp=array[start];
        //循环复杂度O(log2n)
        for(int i=start*2+1 ; i<=end ; i=i*2+1){ //i为start的左孩子 ，下一次的i为i的左孩子   // 循环复杂度O(n/2)
            if(i<end && array[i]<array[i+1]){  //保证判断后的i 为左右孩子中最大的值
                i++;
            }
            if(array[i]>tmp){
                array[start]=array[i];
                start=i;
            }else{
                break;
            }
            /*
            if(array[i]<tmp){  //对于相等地情况，他会进入他的孩子，但是实际上他的孩子都已经小于他 所以跑一圈后还是break;  因为目前地小根堆已经有序；
                break;
            }
            */
        }
        array[start]=tmp;
    }
    public static void heapSort(int [] array){
        //建立大根堆 ，不断地调整
        for(int i= (array.length-1-1)>>1 ; i>=0 ; i--){ //n =array.length-1  父 （n+1）/2
            adjust(array,i,array.length-1);
        }
        // 交换 ，调整
        for(int j=0 ; j<array.length-1 ;j++ ) {  //可以写成j<array.length-2  因为最后一次是 0 号角标的交换
            int tmp=array[0];
            array[0]=array[array.length-1-j];
            array[array.length-1-j]=tmp;
            adjust(array,0,array.length-1-j-1);  //因为array.length-1-j 位置已经是第j大的值了 ，所以只需要从上一位开始排序就好；
        }
    }
    public static void main(String[] args) {
        /*int[] array = {10,3,2,1,99,76,34,76,23};
        heapSort(array);
        System.out.println(Arrays.toString(array));*/
        int[] array = new int[10000];//n   log2n
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10000)+1;
        }
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}

```

