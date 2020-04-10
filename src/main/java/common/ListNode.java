package common;

/**
 * Copyright (C), 上海秦苍信息科技有限公司
 * <p>
 * <类描述>
 *
 * @author wub
 * @version ListNode, v1.0 2020/4/10 16:10
 */
public class ListNode {

    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }


    public static void print(ListNode listNode){
        while(listNode != null){
            System.out.print(listNode.val);
            if(listNode.next != null){
                System.out.print(",");
            }
            listNode = listNode.next;
        }
    }

    public static ListNode generate(int[] arr){
        ListNode head = new ListNode(arr[0]);
        ListNode l = head;
        for(int i=1;i<arr.length;i++){
            l.next = new ListNode(arr[i]);
            l = l.next;
        }
        return head;
    }


}
