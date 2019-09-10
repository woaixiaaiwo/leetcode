package codes.hard.dp;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 *
 * 动态规划解法
 */
public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
        //1.定义状态数组 arr[i][j] 表示 s[0...i] 和 p[0...j]匹配
        //这里数组多定义一个长度是因为匹配要从空字符串开始，否则无法定义初始状态
        boolean[][] arr = new boolean[s.length()+1][p.length()+1];
        int m = s.length(), n = p.length();
        //2.定义初始状态，两个空字符是匹配的，所以arr[0][0]=true
        arr[0][0] = true;
        //初始化第一行，即拿空字符串和模式串匹配，如果有*号，让其重复前一个字符0次即可
        for (int j = 1; j <= n; ++j) {
            if (p.charAt(j-1) == '*') arr[0][j] = arr[0][j-2];
        }
        //3.定义转移方程,即计算arr[i][j]的值
        //3.1 如果p[i] == p[j] || p[j] == '.'，说明p[i]和p[j]匹配，此时只要判断p[i-1]和p[j-1]是否匹配即可，即arr[i][j] = arr[i-1][j-1]
        //3.2 否则，如果p[j]='*'，则有如下判断：
        //    3.2.1 如果p[j-1] != s[i],此时*把p[i-1]重复0次，比较s[0...i]和[0...j-2]是否匹配，此时arr[i][j] = arr[i][j-2]
        //    3.2.2 如果p[j-1] = s[i] 或者 p[i-1] = '.'，有：
        //    p[j-1]重复多次，此时判断 s[0...i-1]和[0...j]是否匹配，即arr[i][j]=arr[i-1][j]。比如abbbb和ab*,此时p[j-1] = s[i],所以最后一个字符一定是匹配的，所以此时判断前面的字符是否匹配即可，即s[0...i-1]和p[0...j]是否匹配
        //    p[j-1]重复一次，即arr[i][j]=arr[i][j-1]
        //    p[j-1]重复0次，即arr[i][j]=arr[i][j-2]
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'){
                    arr[i+1][j+1] = arr[i][j];
                }else if(p.charAt(j) == '*'){
                    if(s.charAt(i) == p.charAt(j-1) || p.charAt(j-1) == '.'){
                        arr[i+1][j+1] = arr[i][j+1] || arr[i+1][j] || arr[i+1][j-1];
                    }else {
                        arr[i+1][j+1] = arr[i+1][j-1];
                    }
                }
            }
        }
        return arr[m][n];
    }

    public static void main(String[] args) {
        System.out.println(new RegularExpressionMatching().isMatch("aab","c*a*b"));
    }

}
