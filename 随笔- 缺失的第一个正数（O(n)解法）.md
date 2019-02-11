####    题目：给定一个未排序的整数数组，找出其中没有出现的最小的正整数。

示例 1:

输入: [1,2,0]
输出: 3
示例 2:

输入: [3,4,-1,1]
输出: 2
示例 3:

输入: [7,8,9,11,12]
输出: 1
说明:

你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。

####  O(n)解法
#####   思路:如果数组正好是1-nums.length的正整数，那么最小遗漏的正整数就是数组长度+1；  因此，如果数组中的值大于数组长度，那么这种值我们不用理会。那么我们可以建立一个新的数组sum，让它的坐标正好对应1-nums.length的值，如果nums中的值在1-nums.length之间，那么将sum对应坐标至为1（表示有这个数）；一次遍历完之后，我们遍历sum不为1那么就是最小的正整数；
```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        int [] sum=new int[nums.length+1];
        for(int num : nums){
            if(num<=nums.length&&num>0){
               sum[num]=1;
            }
        }
        for(int i=1;i<sum.length;i++){
            if(sum[i]!=1){
                return i;
            }
        }
        return sum.length;
    }
}
```

