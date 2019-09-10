package codes.hard.dp;

/**
 * 通配符匹配
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 *
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 */
public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        //1.定义状态数组 arr[i][j] 表示 s[0...i] 和 p[0...j]匹配
        boolean[][] arr = new boolean[s.length()+1][p.length()+1];
        arr[0][0] = true;
        int m = s.length(), n = p.length();
        for(int i=1;i<=n;i++){
            if(p.charAt(i-1) == '*'){
                arr[0][i] = arr[0][i-1];
            }
        }
        //3.定义转移方程,即计算arr[i][j]的值
        //3.1 如果p[i] == p[j] || p[j] == '?'，说明p[i]和p[j]匹配，此时只要判断p[i-1]和p[j-1]是否匹配即可，即arr[i][j] = arr[i-1][j-1]
        //3.2 否则，如果p[j]='*'，此时有:
        // 1.*匹配空字符串，此时有p[i][j] = p[i][j-1]
        // 2.*匹配一个字符串，此时有p[i][j] = p[i-1][j-1]
        // 3.*匹配多个字符串，此时有p[i][j] = p[i-1][j]
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'){
                    arr[i+1][j+1] = arr[i][j];
                }else if(p.charAt(j) == '*'){
                    if(j == 0){
                        arr[i+1][j+1] = true;
                    }else {
                        arr[i+1][j+1] = arr[i+1][j] || arr[i][j] || arr[i][j+1];
                    }
                }
            }
        }
        return arr[m][n];
    }

    private void printArr(boolean[][] arr,int m,int n){
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                System.out.print(arr[i][j]?1:0);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println(new WildcardMatching().isMatch("","?"));
    }


}
