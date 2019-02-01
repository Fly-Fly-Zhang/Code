####   题目：给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).

####   思路：三个数首先用一个数确定基准，采用双指针找出包含这个基准数的三数之和与target最接近的和；当找到的值比target大时，那么我们就尝试找相对小一点的和，那么high--；反之low++；当找到的三数之和整好和target相同，那是最接近的，直接退出就好；
####   时间复杂度：O(n2)
####   代码实现：
```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        
        Arrays.sort(nums);//对数组进行排序
        int approachNum=nums[0]+nums[1]+nums[2];//第一次的三数之和
        for(int i=0;i<nums.length-2;i++){//找到基准
            int low=i+1;
            int high=nums.length-1;
            while(low<high){
                int tmp=nums[i]+nums[low]+nums[high];
                if(Math.abs(target-approachNum)>Math.abs(target-tmp)){ 
                    //比较当前tmp与上一次保存的，将于target差距小的保存起来；
                    approachNum=tmp;
                }
                if(target<tmp){//目前值比target大，那么就相对往小的1减
                    high--;
                }else if(target>tmp){
                    low++;
                }else{//当前值与target相等，最接近是等于它，直接返回
                    return target;
                }
                 
            }
            
        }
        return approachNum;
    }
}
```
