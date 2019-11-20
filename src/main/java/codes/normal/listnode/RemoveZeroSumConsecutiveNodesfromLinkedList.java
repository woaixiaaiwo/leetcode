package codes.normal.listnode;

import java.util.*;

/**
 * 给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。
 *
 * 删除完毕后，请你返回最终结果链表的头节点。
 *
 *  
 *
 * 你可以返回任何满足题目要求的答案。
 *
 * （注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。）
 *
 * 示例 1：
 *
 * 输入：head = [1,2,-3,3,1]
 * 输出：[3,1]
 * 提示：答案 [1,2,1] 也是正确的。
 * 示例 2：
 *
 * 输入：head = [1,2,3,-3,4]
 * 输出：[1,2,4]
 * 示例 3：
 *
 * 输入：head = [1,2,3,-3,-2]
 * 输出：[1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list
 */
public class RemoveZeroSumConsecutiveNodesfromLinkedList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummpy = new ListNode(0);
        dummpy.next = head;
        Map<Integer,ListNode> map = new HashMap<>();
        //先遍历所以数据，计算和，后面的和覆盖前面的
        int sum=0;
        for (ListNode node = dummpy;node!=null;node = node.next){
            sum+=node.val;
            map.put(sum,node);
        }
        //计算到当前和的时候，把指针直接指向缓存中的值即可
        sum =0;
        for (ListNode node = dummpy;node!=null;node = node.next){
            sum+=node.val;
            node.next = map.get(sum).next;
        }
        return dummpy.next;
    }

}
