package codes.normal;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 */
public class AddTwoNumbers {

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //以l1为基准链表，和l2相加
        ListNode res = l1;
        ListNode last = l1;
        boolean carry = false;
        while(l1 != null){
            int num;
            if(l2 != null){
                num = l1.val + l2.val;
            }else{
                num = l1.val;
            }
            if(carry){
                num = num+1;
                carry = false;
            }
            if(num >= 10){
                num = num-10;
                carry = true;
            }
            l1.val = num;
            if(l2 != null){
                if(l1.next == null && l2.next != null){
                    l1.next = l2.next;
                    l2.next = null;
                }
                l2 = l2.next;
            }
            l1 = l1.next;
            if(l1 != null){
                last = l1;
            }
        }
        if(carry){
            last.next = new ListNode(1);
        }
        return res;
    }

}
