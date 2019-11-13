package codes.normal;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * ps：即对链表进行归并排序
 *
 * 链接：https://leetcode-cn.com/problems/sort-list/
 */
public class SortList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode sortList(ListNode head) {

        //1.获取链表长度
        int length = 0;
        ListNode node = head;
        while(node != null){
            length++;
            node = node.next;
        }
        ListNode res = null;
        for(int i = 1;i<length;i=i*2){
            ListNode begin = head;
            int size = i*2;
            boolean isEnd = false;
            while (true){
                ListNode end = begin;
                for(int j=0;j<size-1;j++){
                    if(end.next != null){
                        end = end.next;
                    }else {
                        isEnd = true;
                        break;
                    }
                }
                ListNode l1 = begin,l2=end;
                begin = end.next;
                if(res == null){
                    res = mergeSort(l1,l2,size);
                    head = res;
                }else{
                    mergeSort(l1,l2,size);
                }
                if(isEnd || begin == null)break;
            }
            res = null;
        }
        return head;
    }

    private static ListNode mergeSort(ListNode begin,ListNode end,int length){
        int mid = length / 2;
        ListNode midNode = begin,midPre=begin,beginPre = null,res = begin;
        int i = 0;
        while(midNode != null && i++<mid){
            midPre = midNode;
            midNode = midNode.next;
        }
        if(midNode == null)return begin;
        ListNode start = midNode;
        end = end.next;
        while(start != end && begin != midNode){
            if(start.val >= begin.val){
                beginPre = begin;
                begin = begin.next;
                continue;
            }
            ListNode temp = start;
            start = start.next;
            midPre.next = temp.next;
            temp.next = begin;
            if(beginPre != null){
                beginPre.next = temp;
            }else {
                res = temp;
            }
        }
        return res;
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
        int[] arr = new int[]{39,96,115,88,-31,-96,106,131};
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
        l1 = sortList(l1);
        System.out.println();
        print(l1);
    }

}
