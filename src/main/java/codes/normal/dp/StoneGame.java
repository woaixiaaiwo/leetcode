package codes.normal.dp;

/**
 * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
 *
 * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
 *
 * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
 *
 * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
 *
 *  
 *
 * 示例：
 *
 * 输入：[5,3,4,5]
 * 输出：true
 * 解释：
 * 亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
 * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
 * 如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
 * 如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
 * 这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/stone-game
 */
public class StoneGame {


    /**
     * 对博弈问题的通用解法，参考https://leetcode-cn.com/problems/stone-game/solution/jie-jue-bo-yi-wen-ti-de-dong-tai-gui-hua-tong-yong/
     * 声明dp数组，dp[i][j]表示区间i到j中，先手/后手对应的最优选择
     * dp[i][j].first：表示区间i到j中，先手的最佳选择
     * dp[i][j].second：表示区间i到j中，后手的最佳选择
     *
     * 定义好了dp数组的含义，接下来就是定义状态转移方程了
     *
     * 首先，对于区间i到j，先手的最佳选择有两种：
     *
     * 1.选择左边那一堆，此时最大值为：
     * arr[i]+dp[i+1][j].second
     * 解释：我作为先手，选择了最左边的石头，现在剩下[i+1...j]的石头堆，而且下一次我是作为后手选择
     *
     * 2.选择右边那一堆，此时最大值为：
     * arr[j]+dp[i][j-1].second
     * 解释：和上面的解释一样。此时，dp[i][j].first的转移方程：
     * dp[i][j].first = Math.max(arr[i]+dp[i+1][j].second,arr[j]+dp[i][j-1].second);
     *
     *
     * 然后，对于后手来说：
     *
     * 如果先手选择了左边那一堆，那么有:
     * dp[i][j].second = dp[i+1][j].first;
     * 解释：因为先手选择了最左边的石头，现在剩下[i+1...j]的石头堆，而且下一次我是作为先手选择的
     *
     * 如果先手选择了右边那一堆，那么有:
     * dp[i][j].second = dp[i][j-1].first;
     * 解释：和上面的解释一样
     *
     * 初始条件：
     * 对i==j来说：
     * dp[i][j].first = arr[i];
     * dp[i][j].second = 0;
     * 因为i==j，所以只要一堆石头，先手只有一个选择就是arr[i]，后手没有选择的余地了
     *
     * 因为dp[i][j] 要依赖 dp[i+1][j]和dp[i][j-1]，所以计算dp数组时，要斜着遍历数组才行
     *
     */
    public boolean stoneGame(int[] piles) {
        return true;
    }



}
