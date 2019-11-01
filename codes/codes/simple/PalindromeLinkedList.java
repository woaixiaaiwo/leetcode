package codes.simple;

/**
 * 请判断一个链表是否为回文链表。
 */
public class PalindromeLinkedList {

    public static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
    }

    public static boolean isPalindrome(ListNode head) {
        if(head == null)return false;
        ListNode p1=head,p2=head;
        while(p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next;
            if(p2 != null){
                p2 = p2.next;
            }
        }
        boolean isOu = p2 == null;
        p2 = p1.next;
        //翻转前半段链表
        reverseLinkedList(head,p1);
        if(isOu){
            if(p1.val != p1.next.val){
                return false;
            }
            p1 = p1.next.next;
        }else {
            p1 = p1.next;
        }
        while(p1 != null && p2 != null){
            if(p1.val != p2.val){
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }


    private static ListNode reverseLinkedList(ListNode start,ListNode end) {
        ListNode pre = start,head = start.next,tail = null,last = end.next;
        if(head == null)return pre;
        tail = head.next;
        pre.next = null;
        while(true){
            head.next = pre;
            pre = head;
            if(tail == null || tail == last)break;
            head = tail;
            tail = tail.next;
        }
        return pre;
    }

    private static void printList(ListNode start){
        while(start != null){
            System.out.print(start.val);
            start = start.next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(1);
        /*ListNode l5 = new ListNode(2);
        ListNode l6 = new ListNode(1);*/
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        /*l4.next = l5;
        l5.next = l6;*/
        /*printList(l1);
        System.out.println();
        printList(reverseLinkedList(l2,l4));*/
        System.out.println(isPalindrome(l1));
    }

}
