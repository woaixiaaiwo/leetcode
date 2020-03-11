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
        int m = s.length(),n = p.length();
        boolean[][] arr = new boolean[m+1][n+1];
        //表示都是空字符串，匹配
        arr[0][0] = true;
        //状态转移方程定义：
        //1.s[i] == p[j],那么arr[i][j]=arr[i-1][j-1]
        //2.s[i] != p[j]
        //2.2 p[j] == ? 那么 arr[i][j]=arr[i-1][j-1]
        //2.3 p[j] == * 那么:
        //2.3.1 *匹配空字符串 arr[i][j]=arr[i][j-1]
        //2.3.2 *匹配单个字符串 arr[i][j]=arr[i-1][j-1]
        //2.3.3 *匹配多个字符串 arr[i][j]=arr[i-1][j]
        //初始化
        for(int i=1;i<=n;i++){
            if(p.charAt(i-1) == '*'){
                arr[0][i] = arr[0][i-1];
            }
        }

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '?'){
                    arr[i][j] = arr[i-1][j-1];
                }else{
                    if(p.charAt(j-1) == '*'){
                        arr[i][j] = arr[i][j-1] || arr[i-1][j-1] || arr[i-1][j];
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
        System.out.println(new WildcardMatching().isMatch("afdfdfsdfdfsdfdsfd","a*f"));
    }


}
