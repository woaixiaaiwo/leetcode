package codes.normal;

import java.util.LinkedList;

/**
 * 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
 *
 *  
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 进阶:
 *
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 * 示例:
 *
 * 输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出: 7 -> 8 -> 0 -> 7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 */
public class AddTwoNumbersII {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        LinkedList<ListNode> stack1 = new LinkedList<>();
        LinkedList<ListNode> stack2 = new LinkedList<>();
        ListNode temp = l1;
        while(temp != null){
            stack1.push(temp);
            temp = temp.next;
        }
        temp = l2;
        while(temp != null){
            stack2.push(temp);
            temp = temp.next;
        }

        if(stack1.size() < stack2.size()){
            LinkedList<ListNode> tempStack = stack1;
            stack1 = stack2;
            stack2 = tempStack;
        }
        boolean carry = false;
        while (!stack1.isEmpty()){
            l1 = stack1.pop();
            if(!stack2.isEmpty()){
                l1.val = l1.val + l2.val;
            }
            if(carry){
                l1.val = l1.val+1;
                carry = false;
            }
            if(l1.val >= 10){
                l1.val = l1.val - 10;
                carry = true;
            }
        }
        if(carry){
            temp = new ListNode(1);
            temp.next = l1;
            l1 = temp;
        }
        return l1;
    }


}
