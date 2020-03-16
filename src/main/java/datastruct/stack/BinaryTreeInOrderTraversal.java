package datastruct.stack;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的 中序 遍历。
 *
 * 非递归做法
 */
public class BinaryTreeInOrderTraversal {

    public List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode cur = root;
        while(cur != null && !stack.isEmpty()){
            while(cur.left != null){
                stack.push(cur.left);
                cur = cur.left;
            }
            TreeNode popNode = stack.pop();
            res.add(popNode.val);
            if(popNode.right != null){
                stack.push(popNode.right);
                cur = popNode.right;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.createTreeNode(new Integer[]{1,2,3,4,5,6});
        System.out.println(new BinaryTreeInOrderTraversal().inOrderTraversal(treeNode));
    }

}
