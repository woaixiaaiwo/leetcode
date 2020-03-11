package codes.hard.dp;


/**
 * 给定一个整数矩阵，找出最长递增路径的长度。
 *
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 *
 * 示例 1:
 *
 * 输入: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径为 [1, 2, 6, 9]。
 * 示例 2:
 *
 * 输入: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
 */


public class LongestIncreasingPathInAMatrix {


    /**
     * 思路1：dfs深度优先搜索+记忆化
     */
    //定义方向
    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m, n;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;
        int ans = 0;
        int cache[][] = new int[m][n];
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                ans = Math.max(ans, dfs(matrix, i, j,cache));
        return ans;
    }

    //每一个相邻元素，如果a[i][j]和a[i+1][j]存在大小关系，则给它做一条边
    //把整个矩阵转换成一个有向无环图，使用dfs遍历，获取最大长度
    private int dfs(int[][] matrix, int i, int j,int cache[][]) {
        if(cache[i][j] != 0){
            return cache[i][j];
        }
        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j]){
                cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y,cache));
            }
        }
        return ++cache[i][j];
    }





    /**
     * 思路2：记录元素值和元素位置i，j。通过元素值从小到大排序，元素值大的依赖元素值小的，进行dp
     */
    /*public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return 0;
        //元素入最小堆，数组下标0-value，数组下标1-matrix中y坐标，数组下标2-matrix中x坐标，最小堆以value排序
        Queue<int[]> minDump = new PriorityQueue<int[]>((pre,next) -> pre[0] - next[0]);
        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix[0].length; x++){
                minDump.offer(new int[]{matrix[y][x],y,x});
            }
        }
        //dp(记录当前元素累积到的最大递增路径长度)
        int[][] dp = new int[matrix.length][matrix[0].length];
        //实时记录最大路径，作为返回值返回
        int maxLength = 0;
        //元素从小到大开始dp(保证大的元素排在小的元素后被累积)
        while(minDump.size() > 0){
            int[] curElement = minDump.poll();
            int value = curElement[0];
            int y = curElement[1];
            int x = curElement[2];
            int curMax = 1;
            //四个方向比较最大路径(如果matrix元素大于周边的元素，则最长路径在周边dp的基础上+1)
            if(y > 0 && value > matrix[y - 1][x])
                curMax = Math.max(curMax,dp[y - 1][x] + 1);
            if(y < matrix.length - 1 && value > matrix[y + 1][x])
                curMax = Math.max(curMax,dp[y + 1][x] + 1);
            if(x > 0 && value > matrix[y][x - 1])
                curMax = Math.max(curMax,dp[y][x - 1] + 1);
            if(x < matrix[0].length - 1 && value > matrix[y][x + 1])
                curMax = Math.max(curMax,dp[y][x + 1] + 1);
            //累积dp
            dp[y][x] = curMax;
            //实时记录最大值
            maxLength = Math.max(maxLength,curMax);
        }
        return maxLength;
    }

    作者：fansir
    链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/solution/javaban-chun-dp-by-fansir/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {8,9,4},
                {7,6,5}
        };
        System.out.println(new LongestIncreasingPathInAMatrix().longestIncreasingPath(matrix));
    }

}
