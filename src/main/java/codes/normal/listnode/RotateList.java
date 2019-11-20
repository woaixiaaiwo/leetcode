package codes.normal.listnode;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 *
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-list
 */
public class RotateList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }


    /**
     * 普通版本
     */
    /*public static ListNode rotateRight(ListNode head, int k) {
        //首先计算链表长度
        if(head == null)return head;
        int length = 0;
        ListNode node = head,tail = null;
        while(node != null){
            length++;
            node = node.next;
            if(node != null){
                tail = node;
            }
        }
        int step = length - (k%length);
        if(step == length)return head;
        node = head;
        for(int i=0;i<step-1;i++){
            node = node.next;
        }
        tail.next = head;
        head = node.next;
        node.next = null;
        return head;
    }*/

    /**
     * 旋转版本
     */
    public static ListNode rotateRight(ListNode head, int k) {
        //首先计算链表长度
        if(head == null)return head;
        int length = 0;
        ListNode node = head,tail = null;
        while(node != null){
            length++;
            node = node.next;
            if(node != null){
                tail = node;
            }
        }
        int step = length - (k%length);
        if(step == length)return head;
        node = head;
        for(int i=0;i<step-1;i++){
            node = node.next;
        }
        tail.next = head;
        head = node.next;
        node.next = null;
        return head;
    }

    private static void print(ListNode listNode){
        while(listNode != null){
            System.out.print(listNode.val);
            if(listNode.next != null){
                System.out.print(",");
            }
            listNode = listNode.next;
        }
    }

    private static ListNode generate(){
        int[] arr = new int[]{1,2,3,4,5};
        ListNode l1 = new ListNode(arr[0]);
        ListNode l = l1;
        for(int i=1;i<arr.length;i++){
            l.next = new ListNode(arr[i]);
            l = l.next;
        }
        return l1;
    }

    public static void main(String[] args) {
        ListNode generate = generate();
        print(generate);
        generate = rotateRight(generate,5);
        System.out.println();
        print(generate);
    }

}
