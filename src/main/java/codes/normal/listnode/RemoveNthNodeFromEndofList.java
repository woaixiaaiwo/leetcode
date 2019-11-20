package codes.normal.listnode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 */
public class RemoveNthNodeFromEndofList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    //两个指针，相距n个节点。当快指针为null时，慢指针正好到达倒数n个节点处
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre,slow,fast;
        pre = null;
        slow = fast = head;
        for(int i=0;i<n;i++){
            fast = fast.next;
        }
        while(fast != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        if(pre == null){
            head = slow==null?null:slow.next;
        }else {
            pre.next = slow.next;
            slow.next = null;
        }
        return head;
    }

}
