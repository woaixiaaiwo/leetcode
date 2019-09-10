package codes.hard.dp;

/**
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 * 1.插入一个字符
 * 2.删除一个字符
 * 3.替换一个字符
 *
 * 动态规划做法
 */
public class EditDistance {

    public int minDistance(String word1, String word2) {

        //1.定义状态数组，d[i][j]表示word1[0...i]和word2[0...j]的编辑距离
        int[][] d = new int[word1.length()+1][word2.length()+1];
        int w1length = word1.length(),w2length = word2.length();
        //2.初始状态
        d[0][0] = 0;
        for(int i=1;i<=word1.length();i++){
            d[i][0] = i;
        }
        for(int j=1;j<=word2.length();j++){
            d[0][j] = j;
        }

        for(int i=1;i<=word1.length();i++){
            for(int j=1;j<=word2.length();j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    d[i][j] = d[i-1][j-1];
                }else {
                    //d[i-1][j]表示删除word1[i]的操作，此时编辑距离就是word1[0...i-1]和word2[0...j]的编辑距离加1
                    //d[i][j-1]表示word1[i]后添加一个word2[j]的操作，此时同时消去的word2[j],就变成了d[i][j-1]。此时编辑距离就是word1[0...i]和word2[0...j-1]的编辑距离加1
                    //d[i-1][j-1]表示word1[i]修改为word2[j]的操作，此时同时消去的word2[j],就变成了d[i][j-1]。此时编辑距离就是word1[0...i-1]和word2[0...j-1]的编辑距离加1
                    //取三者最小的数字，作为d[i][j]的值
                    d[i][j] = Math.min(Math.min(d[i-1][j],d[i][j-1]),d[i-1][j-1])+1;
                }
            }
        }
        return d[w1length][w2length];
    }

    public static void main(String[] args) {
        System.out.println(new EditDistance().minDistance("abc","abc"));
    }

}
