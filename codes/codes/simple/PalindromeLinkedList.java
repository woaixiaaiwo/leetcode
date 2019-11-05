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


    public static ListNode swapPairs(ListNode head) {
        if(head == null)return null;
        ListNode res = doSwap(head,head.next);
        ListNode pre = head;
        head = head.next;
        while(head != null){
            pre.next = doSwap(head,head.next == null?null:head.next);
            pre = head;
            head = head.next;
        }
        return res;
    }

    private static ListNode doSwap(ListNode l1,ListNode l2) {
        if(l1 != null && l2 != null){
            l1.next = l2.next;
            l2.next = l1;
        }
        return l2==null?l1:l2;
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
        ListNode l2 = new ListNode(2);/*
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);*/
        l1.next = l2;/*
        l2.next = l3;
        l3.next = l4;*/
        printList(swapPairs(l1));
    }

}
