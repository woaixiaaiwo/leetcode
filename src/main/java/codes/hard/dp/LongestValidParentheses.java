package codes.hard.dp;

/**
 * <p>
 * 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {

        //定义dp数组，dp[i]表示第i个位置最长的有效括号
        int dp[] = new int[s.length()];
        int max = 0;
        //开始计算
        //如果s[i]==')'且s[i-1]=='('，那么dp[i] = dp[i-2]+2
        //如果s[i]和s[i-1]都等于')'，则有如下判断：
        //判断dp[i-1]的值，就是i-1位置最长的括号格式，判断最左边的那一个值是不是'('
        //举例：()(())这个字符串，当遍历到最后一个值时，dp[4]=2,这首要判断4-2位置，即2位置是不是(，此时2位置是(，所以dp[5]=dp[4]+2
        //但是，还有一点不要忽略了，dp[1]=2,且和当前dp[5]的字符串可以组成更长的字符串，所以还要加上dp[1]。即最后表达式为:
        //1.判断s[i-1-dp[i-1]]是否为'('
        //2.如果是，那么dp[i] = dp[i-1]+2+dp[i-dp[i-1]-2]
        for(int i=1;i<s.length();i++){
            if(s.charAt(i) == ')'){
                char left = s.charAt(i-1);
                if(left == '('){
                    if(i-2 >= 0){
                        dp[i] = dp[i-2] + 2;
                    }else {
                        dp[i] = 2;
                    }
                }else {
                    int leftDpIndex = i-1-dp[i-1];
                    if(leftDpIndex >= 0){
                        if(s.charAt(leftDpIndex) == '('){
                            dp[i]=dp[i-1]+2;
                            if(i-dp[i-1]-2 >= 0){
                                dp[i]=dp[i]+dp[i-dp[i-1]-2];
                            }
                        }
                    }
                }
                max = Math.max(max,dp[i]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestValidParentheses().longestValidParentheses("()()))))()()("));
    }
}
