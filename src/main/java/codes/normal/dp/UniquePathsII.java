package codes.normal.dp;

public class UniquePathsII {


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(n == 0)return m;
        //arr[i][j] 为机器人从 (1,1)位置 到 (i+1,j+1) 位置的路径总数
        obstacleGrid[0][0]=obstacleGrid[0][0]==1?0:1;
        for(int i=1;i<m;i++){
            if(obstacleGrid[i-1][0] == 0 || obstacleGrid[i][0]==1){
                obstacleGrid[i][0]=0;
            }else {
                obstacleGrid[i][0]=1;
            }
        }
        for(int i=1;i<n;i++){

            if(obstacleGrid[0][i-1] == 0 || obstacleGrid[0][i]==1){
                obstacleGrid[0][i]=0;
            }else {
                obstacleGrid[0][i] = 1;
            }
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(obstacleGrid[i][j] == 1){
                    obstacleGrid[i][j] = 0;
                }else {
                    obstacleGrid[i][j]  = obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
                }
            }
        }
        return obstacleGrid[m-1][n-1];
    }


    public static void main(String[] args) {
        int[][] arr = new int[3][0];
        System.out.println(arr.length);
        System.out.println(arr[0].length);
    }

}
