package codes.normal.dp;

/**
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 */
public class MaximumProductSubarray {

    public static int maxProduct(int[] nums) {
        if(nums.length == 0)return 0;
        int max = nums[0];
        int[] dp = new int[nums.length];
        int[] arr = new int[nums.length];
        dp[0] = nums[0];
        arr[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            arr[i] = arr[i-1]*nums[i];
            dp[i] = Math.max(Math.max(dp[i-1],arr[i]),nums[i]);
            if(dp[i]>max){
                max = dp[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2,-5,-2,-4,3}));
    }

}
