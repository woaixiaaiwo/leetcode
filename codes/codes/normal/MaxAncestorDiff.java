package codes.normal;

/**
 * 给定二叉树的根节点 root，找出存在于不同节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。
 *
 * （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-difference-between-node-and-ancestor
 */
public class MaxAncestorDiff {

    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    private int res = 0;

    public int maxAncestorDiff(TreeNode root) {
        if(root == null)return res;
        dfs(root,root.val,root.val);
        return res;
    }

    private void dfs(TreeNode root,int max,int min){
        if(root.val > max)max = root.val;
        if(root.val < min)min = root.val;
        res = Math.max(res,Math.abs(max-min));
        if(root.left != null){
            dfs(root.left,max,min);
        }
        if(root.right != null){
            dfs(root.right,max,min);
        }
    }

}
