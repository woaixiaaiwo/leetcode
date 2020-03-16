package datastruct.stack;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 * 非递归做法
 */
public class BinaryTreePreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null)return res;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.val);
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null ){
                stack.push(node.left);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.createTreeNode(new Integer[]{1,2,3,4,5,6});
        System.out.println(new BinaryTreePreorderTraversal().preorderTraversal(treeNode));
    }

}
