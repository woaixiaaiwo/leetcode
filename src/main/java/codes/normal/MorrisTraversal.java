package codes.normal;

import codes.simple.MaximumDepthofNaryTree;

import java.util.*;

/**
 * 使用O(1)空间复杂度和O(n)时间复杂度中序遍历二叉树
 *
 * 总体思想就是：记当前遍历的节点为 cur。
 *
 * 1、cur.left 为 null，保存 cur 的值，更新 cur = cur.right
 *
 * 2、cur.left 不为 null，找到 cur.left 这颗子树最右边的节点记做 last，这里找最右边节点的时候要排除当前节点
 *
 * 2.1 last.right 为 null，那么将 last.right = cur，更新 cur = cur.left
 *
 * 2.2 last.right 不为 null，说明之前已经访问过，第二次来到这里，表明当前子树遍历完成，保存 cur 的值，更新 cur = cur.right
 */
public class MorrisTraversal {

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


    public TreeNode createTreeNode(Integer[] arr){
        TreeNode[] treeNodes = new TreeNode[arr.length];
        int j=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i] == null){
                treeNodes[i] = null;
            }else {
                treeNodes[i] = new TreeNode(arr[i]);
            }
        }
        for(int i=0;i<arr.length;i++){
            if(arr[i] != null){
                if(++j < arr.length){
                    treeNodes[i].left = treeNodes[j];
                }
                if(++j < arr.length) {
                    treeNodes[i].right = treeNodes[j];
                }
            }
        }
        return treeNodes[0];
    }

    private void helper(List<Integer> list,TreeNode root){
        if(root.left != null){
            helper(list,root.left);
        }
        list.add(root.val);
        if(root.right != null){
            helper(list,root.right);
        }
    }

    /**
     * 递归遍历
     * @param root
     * @return
     */
    public List<Integer> recursiveTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root != null){
            helper(res,root);
        }
        return res;
    }

    /**
     * 非递归遍历，使用栈
     * @param root
     * @return
     */
    public List<Integer> stackTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        stack.push(cur);
        while(cur != null && !stack.isEmpty()){
            while (cur != null && cur.left != null){
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

    /**
     * inorderTraversal，空间复杂度为1
     * 1、cur.left 为 null，保存 cur 的值，更新 cur = cur.right
     *
     * 2、cur.left 不为 null，找到 cur.left 这颗子树最右边的节点记做 last，这里找最右边节点的时候要排除当前节点
     *
     * 2.1 last.right 为 null，那么将 last.right = cur，更新 cur = cur.left
     *
     * 2.2 last.right 不为 null，说明之前已经访问过，第二次来到这里，表明当前子树遍历完成，保存 cur 的值，更新 cur = cur.right
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        TreeNode last;
        while (cur != null){
            if(cur.left != null){
                //找到cur.left的最右节点
                last = cur.left;
                while (last.right != null && last.right != cur){
                    last = last.right;
                }
                if(last.right == null){
                    last.right = cur;
                    cur = cur.left;
                }else {
                    res.add(cur.val);
                    cur = cur.right;
                }
            }else {
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MorrisTraversal morrisTraversal = new MorrisTraversal();
        TreeNode treeNode = morrisTraversal.createTreeNode(new Integer[]{1,2,3,4,5,6,null,null,null,null,7});
        System.out.println(morrisTraversal.recursiveTraversal(treeNode));
        System.out.println(morrisTraversal.stackTraversal(treeNode));
        System.out.println(morrisTraversal.inorderTraversal(treeNode));
    }
}
