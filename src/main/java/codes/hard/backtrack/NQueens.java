package codes.hard.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 使用递归+回溯
 */
public class NQueens {
    //列数，由于行数是不会重复的，所以只记录列即可
    boolean rows[];
    // 主对角线 性质为 行数-列数为一常数。但是可能为负数，所以定义的时候要加上n，所以大小为2n
    boolean hills[];
    // 次对角线 性质为 行数+列数为一常数。大小为2n
    boolean dales[];
    //皇后个数
    int n;
    // 输出
    List<List<String>> output = new ArrayList();
    // 皇后的位置，即当前行数的哪一列，比如第5行的第3列，则有queens[4]=2
    int queens[];

    public boolean isNotUnderAttack(int row, int col) {
        return !(rows[col] || hills[row - col + n] || dales[row + col]);
    }

    public void placeQueen(int row, int col) {
        queens[row] = col;
        rows[col] = true;
        hills[row - col + n] = true;  //主对角线
        dales[row + col] = true;      //次对角线
    }

    public void removeQueen(int row, int col) {
        queens[row] = 0;
        rows[col] = false;
        hills[row - col + n] = false;  //主对角线
        dales[row + col] = false;      //次对角线
    }

    public void addSolution() {
        List<String> solution = new ArrayList<String>();
        for (int i = 0; i < n; ++i) {
            int col = queens[i];
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < col; ++j) sb.append(".");
            sb.append("Q");
            for(int j = 0; j < n - col - 1; ++j) sb.append(".");
            solution.add(sb.toString());
        }
        output.add(solution);
    }

    public void backtrack(int row) {
        for(int col=0;col<n;col++){
            if(isNotUnderAttack(row,col)){
                placeQueen(row,col);
                if(row == n-1){
                    addSolution();
                }else {
                    backtrack(row+1);
                }
                removeQueen(row,col);
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        rows = new boolean[n];
        hills = new boolean[2 * n];
        dales = new boolean[2 * n];
        queens = new int[n];

        backtrack(0);
        return output;
    }

    public static void main(String[] args) {
        List<List<String>> lists = new NQueens().solveNQueens(4);
        System.out.println(lists.size());
        for(List<String> l:lists){
            for(String s:l){
                System.out.println(s);
            }
            System.out.println("--------------------");
        }
    }

}
