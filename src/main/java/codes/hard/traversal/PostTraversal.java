package codes.hard.traversal;

import codes.normal.MorrisTraversal;

import java.util.*;

/**
 * 二叉树后续遍历，非递归版本
 *
 * @author wub
 * @version PostTraversal, v1.0 2019/10/16 13:57
 */
public class PostTraversal {

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

    private List<Integer> list = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) return list;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        Set<TreeNode> set = new HashSet<>();
        while(!stack.isEmpty()){
            TreeNode node = stack.peek();
            if(node.left != null && !set.contains(node.left)){
                stack.push(node.left);
                continue;
            }
            if(node.right != null && !set.contains(node.right)){
                stack.push(node.right);
                continue;
            }
            node = stack.pop();
            list.add(node.val);
            set.add(node);
        }

        return list;
    }

}
