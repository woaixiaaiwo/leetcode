package codes.normal;

/**
 * Copyright (C), 上海秦苍信息科技有限公司
 * <p>
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
 */
public class PopulatingNextRightPointersInEachNode {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * 思路：使用多个指针，p1指针指向已填充的层，寻找p1指针下一层的第一个非空节点，标记为p2,使用p保存当前p2节点
     * 然后，遍历p1指针，填充p2指针所在的层。填充完毕后，把p节点赋值给p1,p2节点置位null，重复上述步骤
     * @param root
     * @return
     */
    public Node connect(Node root) {
        Node p,p1,p2;
        p1 = root;
        while(true){
            p = p1;
            p2 = null;
            while(p != null){
                if(p.left != null){
                    p2 = p.left;
                    break;
                }
                if(p.right != null){
                    p2 = p.right;
                    break;
                }
                p = p.next;
            }
            if(p2 == null){
                break;
            }
            p = p2;
            while(p1 != null){
                if(p2 == p1.left){
                    if(p1.right != null){
                        p2.next = p1.right;
                        p2 = p2.next;
                        p1 = p1.next;
                        continue;
                    }
                }else{
                    if(p2 == p1.right){
                        p1 = p1.next;
                        continue;
                    }
                    if(p1.left != null){
                        p2.next = p1.left;
                        p2=p2.next;
                        continue;
                    }else if(p1.right != null){
                        p2.next = p1.right;
                        p2=p2.next;
                        p1 = p1.next;
                        continue;
                    }
                }
                p1 = p1.next;
            }
            p1 = p;
        }
        return root;
    }

}
