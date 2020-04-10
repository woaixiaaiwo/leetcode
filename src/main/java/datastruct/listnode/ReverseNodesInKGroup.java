package datastruct.listnode;

import codes.normal.listnode.RotateList;
import common.ListNode;

import java.util.Random;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 *  
 *
 * 示例：
 *
 * 给你这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 */
public class ReverseNodesInKGroup {

    public  ListNode nextDeal = null;
    
    /*public ListNode reverseKGroup(ListNode head, int k) {
        if(k == 1 || head == null)return head;
        ListNode newHead = reverseKNode(head,head,head.next,k-2);
        if(nextDeal != null){
            reverseAndGroup(head,nextDeal,k);
        }
        return newHead;
    }*/

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null)return null;
        ListNode a,b;
        a = b = head;
        //不足k个不翻转
        for(int i=0;i<k;i++){
            if(b == null)return head;
            b = b.next;
        }
        ListNode newHead = reverseNode(a, b);
        a.next = reverseKGroup(b,k);
        return newHead;
    }

    /**
     * 翻转某一区间，左闭右开
     * @param l1
     * @param l2
     * @return
     */
    private ListNode reverseNode(ListNode l1,ListNode l2){
        ListNode pre,cur,nxt;
        pre = null;cur = l1;nxt = l1.next;
        while (cur != l2){
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    /**
     * 翻转链表k个节点，返回新节点
     * @param head
     * @param l1
     * @param l2
     * @param k
     * @return
     */
    private ListNode reverseKNode(ListNode head,ListNode l1,ListNode l2, int k){
        if(k >= 0 && l2 == null){
            nextDeal = null;
            return head;
        }
        if(k > 0){
            ListNode newHead = reverseKNode(head, l2, l2.next, k - 1);
            if(head == newHead){
                return head;
            }
            head = newHead;
        }
        if(k == 0){
            head.next = null;
            head = l2;
            nextDeal = head.next;
        }
        l2.next = l1;
        return head;
    }

    /**
     * 对余下节点进行翻转和连接操作
     * @param l1
     * @param l2
     * @param k
     */
    private void reverseAndGroup(ListNode l1,ListNode l2, int k){
        ListNode newNode = reverseKNode(l2,l2,l2.next,k-2);
        l1.next = newNode;
        if(nextDeal != null){
            reverseAndGroup(l2,nextDeal,k);
        }
    }

    public static void main(String[] args) {
        int size = 100;
        int k = 9;
        int [] arr = new int[size];
        Random random = new Random();
        for(int i=0;i<size;i++){
            arr[i] = random.nextInt(100);
        }
        ListNode generate = ListNode.generate(new int[]{1,2});
        ListNode.print(generate);
        generate = new ReverseNodesInKGroup().reverseKGroup(generate,2);
        System.out.println();
        ListNode.print(generate);
    }

}
