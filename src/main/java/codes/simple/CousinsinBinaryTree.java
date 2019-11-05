package codes.simple;

import codes.normal.MorrisTraversal;

/**
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 *
 * 如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。
 *
 * 我们给出了具有唯一值的二叉树的根节点 root，以及树中两个不同节点的值 x 和 y。
 *
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true。否则，返回 false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cousins-in-binary-tree
 */
public class CousinsinBinaryTree {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    int left = 0;
    int right = 0;
    TreeNode leftNode;
    TreeNode rightNode;
    
    public boolean isCousins(TreeNode root, int x, int y) {
        if(root == null)return false;
        traversal(root,0,x,y);
        return left == right && leftNode != rightNode;
    }

    private void traversal(TreeNode root,int deep,int x,int y){
        if(left != 0 && right != 0)return;
        if(root.left != null){
            if(root.left.val == x){
                left = deep+1;
                leftNode = root;
            }
            else if(root.left.val == y){
                right = deep+1;
                rightNode = root;
            }
            else {
                traversal(root.left,deep+1,x,y);
            }
        }
        if(root.right != null){
            if(root.right.val == x){
                left = deep+1;
                leftNode = root;
            }
            else if(root.right.val == y){
                right = deep+1;
                rightNode = root;
            }
            else {
                traversal(root.right,deep+1,x,y);
            }
        }
    }

}
