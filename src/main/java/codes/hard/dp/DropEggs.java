package codes.hard.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 你面前有一栋从 1 到 N 共 N 层的楼，然后给你 K 个鸡蛋（K 至少为 1）。
 * 现在确定这栋楼存在楼层 0 <= F <= N，在这层楼将鸡蛋扔下去，鸡蛋恰好没摔碎（高于 F 的楼层都会碎，低于 F 的楼层都不会碎）。
 * 现在问你，最坏情况下，你至少要扔几次鸡蛋，才能确定这个楼层 F 呢？
 *
 * @author wub
 * @version DropEggs, v1.0 2020/1/13 16:56
 */
public class DropEggs {

    /**
     * 假设有一初始楼层x，和dp数组dp[i][j]
     * dp[i][j]表示从i层楼，测试j个鸡蛋，最坏情况下需要的最少次数
     *
     * 从x层扔下，有两种情况：
     * 1.鸡蛋碎了，那么下次测试从0->x-1楼层，同时只有j-1个鸡蛋了
     * 2.鸡蛋没碎，下次测试从x+1->i楼层，即i-x层楼
     * 从这一层最坏情况，就是上面两者的最大值。我们要求的，就是所有最坏情况下，次数最少的
     * 所以，res = Min{Max(dp(x-1,k-1),dp(n-x,k))+1 | x in {0,1,2...,n}}
     */


    public static int solution(int n,int k){

        int[][] dp = new int[n][k];
        for(int i=0;i<n;i++){
            dp[i][0] = i+1;
        }
        for(int i=0;i<k;i++){
            dp[0][i] = 1;
        }

        for(int i=1;i<n;i++){

        }


    }

    public static void main(String[] args) {
        //System.out.println(dp(20,2));
    }

}
