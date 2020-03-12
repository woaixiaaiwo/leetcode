package codes.hard.slidingwindow;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *  
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 *
 * 提示：
 *
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 */
public class SlidingWindowMaximum {

    public static int[] maxSlidingWindow(int[] nums, int k) {

        //使用双向队列
        LinkedList<Integer> queue = new LinkedList<>();
        int[] res = new int[nums.length-k+1];
        int max = nums[0];
        for(int i=0;i<k;i++){
            if(nums[i]>max){
                max = nums[i];
            }
            if(queue.size() == 0){
                queue.addLast(i);
            }else {
                while(queue.size() > 0 && nums[queue.peekLast()] <= nums[i]){
                    queue.removeLast();
                }
                queue.addLast(i);
            }
        }
        res[0] = max;
        for(int i=k;i<nums.length;i++){
            int index = queue.peek();
            //判断max索引是否过期
            if(index <= i-k){
                queue.removeFirst();
                if(queue.size() == 0){
                    queue.addLast(i);
                    res[i-k+1] = nums[i];
                    continue;
                }
            }
            //没过期，判断队列最大值和当前最大值
            int val = nums[queue.peekFirst()];
            //当前最大值>=队列最大值，移除队列所有元素，并把当前数据添加到队首
            if(nums[i] >= val){
                queue.clear();
                queue.addLast(i);
                res[i-k+1] = nums[i];
                continue;
            }else {
                //否则，把当前数据索引添加到队尾的合适位置
                while(queue.size() > 0 && nums[queue.peekLast()] <= nums[i]){
                    queue.removeLast();
                }
                queue.addLast(i);
            }
            //当前最大值<队列最大值的情况，
            res[i-k+1] = val;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-95,92,-85,59,-59,-14,88,-39,2,92,94,79,78,-58,37,48,63,-91,91,74,-28,39,90,-9,-72,-88,-72,93,38,14,-83,-2,21,4,-75,-65,3,63,100,59,-48,43,35,-49,48,-36,-64,-13,-7,-29,87,34,56,-39,-5,-27,-28,10,-57,100,-43,-98,19,-59,78,-28,-91,67,41,-64,76,5,-58,-89,83,26,-7,-82,-32,-76,86,52,-6,84,20,51,-86,26,46,35,-23,30};
        System.out.println(Arrays.toString(maxSlidingWindow(arr,10)));
    }

}
/**
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 *
 * 使用滑动窗口解法
 */