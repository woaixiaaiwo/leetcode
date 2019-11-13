package codes.normal;

/**
 * 奇偶链表
 *
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 *
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 */
public class OddEvenLinkedList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    // low b写法
    /*public static ListNode oddEvenList(ListNode head) {
        ListNode oddNode,evenStartNode,evenNode;
        if(head == null || head.next == null){
            return head;
        }
        oddNode = head;
        evenStartNode = head.next;
        evenNode = null;
        while(true){
            if(oddNode.next != null && oddNode.next.next != null){
                if(evenNode == null){
                    evenNode = evenStartNode;
                }else {
                    evenNode.next = oddNode.next;
                    evenNode = evenNode.next;
                }
                oddNode.next = oddNode.next.next;
                oddNode = oddNode.next;
                evenNode.next = null;
                continue;
            }
            if(oddNode.next != null){
                if(evenNode != null){
                    evenNode.next = oddNode.next;
                }
            }
            oddNode.next = evenStartNode;
            break;
        }
        return head;
    }*/

    public static ListNode oddEvenList(ListNode head) {
        if(head == null)return head;
        ListNode odd = head,even = head.next,evenHead = even;
        while(even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
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
        int[] arr = new int[]{1,2};
        ListNode l1 = new ListNode(arr[0]);
        ListNode l = l1;
        for(int i=1;i<arr.length;i++){
            l.next = new ListNode(arr[i]);
            l = l.next;
        }
        return l1;
    }

    public static void main(String[] args) {
        ListNode l1 = generate();
        print(l1);
        l1 = oddEvenList(l1);
        System.out.println();
        print(l1);
    }

}
