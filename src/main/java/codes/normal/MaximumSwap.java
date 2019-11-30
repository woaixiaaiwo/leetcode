package codes.normal;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 *
 * 示例 1 :
 *
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 * 示例 2 :
 *
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-swap
 */
public class MaximumSwap {

    //todo:优化：只要存索引即可，不用存数字
    public static int maximumSwap(int num) {
        String s = String.valueOf(num);
        char[] arr = s.toCharArray();
        Map<Integer,Character> maxMap = new HashMap<>();
        char currentMax = 0;
        Map<Character,Integer> indexMap = new HashMap<>();
        for(int i=arr.length-1;i>=0;i--){
            if(arr[i]>currentMax){
                currentMax = arr[i];
                indexMap.put(currentMax,i);
            }
            maxMap.put(i,currentMax);
        }
        for(int i=0;i<arr.length;i++){
            Character max = maxMap.get(i);
            int index = indexMap.get(max);
            if(arr[i] < max && i < index){
                swap(arr,i,index);
                break;
            }
        }
        return Integer.valueOf(new String(arr));
    }

    private static void swap(char[] arr,int i,int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        System.out.println(maximumSwap(2736));
    }
}
