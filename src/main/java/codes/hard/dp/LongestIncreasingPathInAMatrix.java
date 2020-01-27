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

/**
 * 思路：dfs深度优先搜索+记忆化
 */
public class LongestIncreasingPathInAMatrix {

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

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {8,9,4},
                {7,6,5}
        };
        System.out.println(new LongestIncreasingPathInAMatrix().longestIncreasingPath(matrix));
    }

}
