package codes.hard.tree;

import codes.normal.MorrisTraversal;

/**
 * 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trim-a-binary-search-tree
 */
public class TrimBinarySearchTree {

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

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null)return null;
        if(root.val >= L && root.val <= R){
            root.left = trimBST(root.left,L,R);
            root.right = trimBST(root.right,L,R);
            return root;
        }
        if(root.val < L){
            return trimBST(root.right,L,R);
        }
        return trimBST(root.left,L,R);
    }
    
    
    
}
