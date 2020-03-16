package datastruct.stack;

import common.TreeNode;

import java.util.*;


public class BinaryTreePostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null)return res;
        LinkedList<TreeNode> stack = new LinkedList<>();
        Set<TreeNode> set = new HashSet<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode treeNode = stack.peek();
            TreeNode cur = treeNode;
            if(treeNode.right != null && !set.contains(treeNode.right)){
                stack.push(treeNode.right);
                cur = treeNode.right;
            }
            if(treeNode.left != null && !set.contains(treeNode.left)){
                stack.push(treeNode.left);
                cur = treeNode.left;
            }
            if(cur == treeNode){
                cur = stack.pop();
                res.add(cur.val);
                set.add(cur);
            }
        }
        return res;
    }

    public void helper(List<Integer> list,TreeNode root) {
        if(root.left != null){
            helper(list,root.left);
        }
        if(root.right != null){
            helper(list,root.right);
        }
        list.add(root.val);
    }

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.createTreeNode(new Integer[]{1,2,3,4,5,6,null,null,null,null,7});
        System.out.println(new BinaryTreePostorderTraversal().postorderTraversal(treeNode));
        List<Integer> list = new ArrayList<>();
        new BinaryTreePostorderTraversal().helper(list,treeNode);
        System.out.println(list);
    }
}
