#### 题目：
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
说明:

必须在原数组上操作，不能拷贝额外的数组。
尽量减少操作次数。
####  思路：定义一个n 用来记录值为val的元素个数，将所有不为val的元素遍历一遍；
法2 看起来更为明了

####  方法一
```
class Solution {
    public void moveZeroes(int[] nums，int val) { 
           int  num=0;//值为val的个数；
           for(int i=0){
                  if(nums[i]==val){
                        num++;
                  }else{ //将当前i 搬运到第一个值为val的角标；
                        nums[i-num]=nums[i];
                  }
           }
           while(num>0){ //将数组后面num长度值赋为val 
                  nums[nums.length-num]=val;
                  num--;
           }
    }
    }
```
####  方法二：采用双指针，原理和上面一样，但是看起来更为明了；

```
class Solution {
    public void moveZeroes(int[] nums,int val) { 
       int i=0;
        int j=0; 将数组中所有不为val的值遍历一遍；
        while(i<nums.length){
            if(nums[i]==val){
                i++;
            }else{ 
                nums[j++]=nums[i++];
            }
        }
        while(j<nums.length){ //遍历结束，数组后面就是原数组中值为val的
            nums[j++]=val;
        }
}
}
```

