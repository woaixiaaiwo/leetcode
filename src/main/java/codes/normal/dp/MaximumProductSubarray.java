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

    public static int ans(int[] nums){
        if(nums.length == 0)return 0;
        int max = nums[0];
        //表示以i结尾的乘积最大值
        int[] dpMax = new int[nums.length];
        dpMax[0] = nums[0];
        //表示以i结尾的乘积最小值
        int[] dpMin = new int[nums.length];
        dpMin[0] = nums[0];

        //dpMax[i]有以下取值方式：
        //1.当nums[i] >= 0,且 dpMax[i-1] >= 0 时，dpMax[i] = dpMax[i-1]*nums[i];
        //2.当nums[i] >= 0,且 dpMax[i-1] <= 0 时，dpMax[i] = nums[i];
        //3.当nums[i] < 0,如果 dpMax[i-1] > 0，那么和nums[i]相乘会得到一个更大的负数
        //所以此时要和dpMin[i-1]相乘
        //4.当nums[i] < 0,如果 dpMax[i-1] < 0，dpMin[i-1]<dpMax[i-1],nums[i]和dpMin[i-1]相乘
        // 会得到一个更大的正数
        //综上，dpMax[i] = max(dpMax[i-1]*nums[i],nums[i],nums[i]*dpMin[i-1]);

        //dpMin[i]有以下取值方式：
        //1.当nums[i] >= 0,且 dpMin[i-1] >= 0 时，dpMin[i] = dpMin[i-1]*nums[i];
        //2.当nums[i] >= 0,且 dpMin[i-1] <= 0 时，dpMin[i] = dpMin[i-1]*nums[i];
        //3.当nums[i] < 0,如果 dpMin[i-1] > 0，那么和nums[i]相乘会得到一个更大的负数
        //所以此时要和dpMax[i-1]相乘
        //4.当nums[i] < 0,如果 dpMax[i-1] < 0，此时不管和dpMin[i-1]还是好dpMax[i-1]
        //相乘都会得到正数，所以此时dpMin[i]=nums[i]
        //综上，dpMin[i] = min(dpMin[i-1]*nums[i],nums[i]*dpMax[i-1],nums[i]);
        for(int i=1;i<nums.length;i++){
            dpMax[i] = Math.max(dpMax[i-1]*nums[i],Math.max(nums[i],nums[i]*dpMin[i-1]));
            dpMin[i] = Math.min(dpMax[i-1]*nums[i],Math.min(nums[i],nums[i]*dpMin[i-1]));
            max = Math.max(max,dpMax[i]);
        }
        return max;
    }

    //优化版本，由于不dp[i]只依赖dp[i-1]，所以只要定义一个变量即可
    public static int maxProduct(int[] nums){
        if(nums.length == 0)return 0;
        int max = nums[0];
        int oldDpMax = nums[0];
        int oldDpMin = nums[0];
        int dpMax,dpMin;
        for(int i=1;i<nums.length;i++){
            dpMax = Math.max(oldDpMax*nums[i],Math.max(nums[i],nums[i]*oldDpMin));
            dpMin = Math.min(oldDpMax*nums[i],Math.min(nums[i],nums[i]*oldDpMin));
            max = Math.max(max,dpMax);
            oldDpMax = dpMax;
            oldDpMin = dpMin;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(ans(new int[]{2,-5,0,-2,-4,3}));
    }

}
