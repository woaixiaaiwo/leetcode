package codes.normal.dp;

import java.util.Arrays;

/**
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 */
public class CountingBits {

    public static int[] countBits(int num) {

        //dp数组，表示从最后一位数字，从后往前，到达第一个0时走过的数字个数
        int dp[] = new int[num+1];
        int res[] = new int[num+1];
        dp[0] = 0;
        res[0] = 0;
        for(int i=1;i<num+1;i++){
            //如果是偶数，说明最后一位数字就是0，所以dp[i]=0
            if(i%2 == 0){
                dp[i] = 0;
            }else {
                //否则，dp[i]为右移一位的数字对应的个数加1
                dp[i] = dp[i>>1]+1;
            }
        }
        //实际计算时，拿上一个数字1的个数减去到第一个0的距离加1即可
        for(int i=1;i<=num;i++){
            res[i] = res[i-1] - dp[i-1] + 1;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(10)));
    }

}
