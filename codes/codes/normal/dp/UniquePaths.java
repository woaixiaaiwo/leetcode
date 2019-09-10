package codes.normal.dp;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {

        //arr[i][j] 为机器人从 (1,1)位置 到 (i+1,j+1) 位置的路径总数
        int arr[][] = new int[m][n];
        arr[0][0]=1;
        for(int i=1;i<m;i++){
            arr[i][0]=1;
        }
        for(int i=1;i<n;i++){
            arr[0][i]=1;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                arr[i][j] = arr[i-1][j]+arr[i][j-1];
            }
        }
        return arr[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(new UniquePaths().uniquePaths(2,2));
    }

}
