package codes.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 */
public class ClimbingStairs {

    public List<String> climbStairs(int n) {
        //动态规划，dp[i]表示i阶。d[i] = d[i-2]+d[i-1];
        List<String> d[] = new List[n];
        d[0]= Arrays.asList("1");
        d[1]= Arrays.asList("1,1","2");
        for(int i=2;i<n;i++){
            List<String> result = new ArrayList<>();
            for(String s1:d[i-1]){
                result.add("1,"+s1);
            }
            for(String s2:d[i-2]){
                result.add("2,"+s2);
            }
            d[i] = result;
        }
        return d[n-1];
    }


    public static void main(String[] args) {
        System.out.println(new ClimbingStairs().climbStairs(4));
    }

}
