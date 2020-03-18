package datastruct.stack;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 */
public class DailyTemperatures {

    /**
     * 思路：使用栈
     * 把数组下标放入栈内，遍历数组，对每一个i，判断T[i]的值是否大于栈顶值j对应的T[j]
     * 1.T[i] > T[j]，说明找到高于j温度的天，j出栈，更新res[j] = i - j;
     * 2.重复步骤1，直到不满足条件，把i入栈
     * 3.i自增，继续遍历余下元素
     */
    public static int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        if(T.length == 0)return res;
        LinkedList<Integer> stack = new LinkedList<>();
        for(int i=0;i<T.length;i++){
            if(stack.isEmpty()){
                stack.push(i);
            }else{
                while(!stack.isEmpty()){
                    Integer index = stack.peek();
                    if(T[i] > T[index]){
                        stack.pop();
                        res[index] = i - index;
                    }else break;
                }
                stack.push(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }

}
