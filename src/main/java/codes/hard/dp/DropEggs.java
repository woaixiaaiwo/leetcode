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
        //dp[i][j]表示i层楼，j个鸡蛋，最坏情况下要的最少次数
        int[][] dp = new int[n+1][k+1];
        //首先都初始化为最坏情况
        for(int i=1;i<=n; i++){
            for(int j=1; j<=k; j++)
                dp[i][j] = i;
        }

        //从第二层楼开始计算
        for(int i=2;i<=n;i++){
            //从两个鸡蛋开始计算
            for(int j=2;j<=k;j++){
                //对每一层楼，都计算它的最坏情况
                //Math.max(dp[x-1][j-1],dp[i-x][j])+1表示碎/不碎对应的情况下的最坏次数
                //min表示从所有的最坏次数中取最优的
                for(int x=1;x<i;x++){
                    dp[i][j]   = Math.min(dp[i][j] ,Math.max(dp[x-1][j-1],dp[i-x][j])+1);
                }
            }
        }

        return dp[n][k];
    }

    //参考
    /*public static int solution2(int eggNum, int floorNum){

        int[][] cache = new int[eggNum+1][floorNum+1];

        for(int i=1;i<=eggNum; i++){

            for(int j=1; j<=floorNum; j++)

                cache[i][j] = j;

        }


        for(int n=2; n<=eggNum; n++){

            for(int m=1; m<=floorNum; m++){

                for(int k=1; k<m; k++){

                    //扔鸡蛋的楼层从1到m枚举一遍，如果当前算出的尝试次数小于上一次算出的尝试次数，则取代上一次的尝试次数。

                    //这里可以打印k的值，从而知道第一个鸡蛋是从第几次扔的。

                    cache[n][m] = Math.min(cache[n][m], 1+Math.max(cache[n-1][k-1],cache[n][m-k]));

                }

            }

        }

        return cache[eggNum][floorNum];
    }*/


    public static void main(String[] args) {
        System.out.println(solution(70,65));
        //System.out.println(solution2(65,70));
    }

}
