package codes.normal.dp;

import java.util.Arrays;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {

        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount);
        for(int i=0;i<coins.length;i++){
            if(coins[i] <= amount){
                dp[coins[i]] = 1;
            }
        }
        for(int i=coins.length-1;i>=0;i--){

        }
    }

    private int cc(int[] coins,int[] dp,int amount){

    }

}
