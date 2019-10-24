package codes.normal.dp;

/**
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 */
public class HouseRobber {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    public int rob(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        int max = 0;
        if(root == null){
            return max;
        }
        max = root.val;

        //对某一个树根root，其最大值有以下几种情况：
        //1.root.left最大值+root.right最大值,dfs(l)+dfs(r) = dfs(ll)+dfs(lr)+dfs(rl)+dfs(rr)
        //2.root.left最大值+root.right.left最大值+root.right.right最大值 dfs(l)+dfs(rl)+dfs(rr) = dfs(ll)+dfs(lr)+dfs(rl)+dfs(rr)
        //3.root.right最大值+root.left.left最大值+root.left.right最大值 dfs(r)+dfs(ll)+dfs(lr) = dfs(rl)+dfs(rr)+dfs(ll)+dfs(lr)
        //观察发现，上面三种情况是等价的，所以可以合并为dfs(l)+dfs(r)
        //4.root的值+root.right.left最大值+root.right.right最大值+root.left.left最大值+root.left.right最大值 root.val+dfs(rl)+dfs(rr)+dfs(ll)+dfs(lr)
        if(root.left != null && root.right != null){
            max = Math.max(max,dfs(root.left)+dfs(root.right));
            int l1 = dfs(root.left.left);
            int r1 = dfs(root.left.right);
            int l2 = dfs(root.right.left);
            int r2 = dfs(root.right.right);
            max = Math.max(max,root.val+r1+r2+l1+l2);
        }else{
            if(root.left != null ){
                max = Math.max(max,dfs(root.left));
                int l = dfs(root.left.left);
                int r = dfs(root.left.right);
                max = Math.max(max,root.val+r+l);
            }

            if(root.right != null ){
                max = Math.max(max,dfs(root.right));
                int l = dfs(root.right.left);
                int r = dfs(root.right.right);
                max = Math.max(max,root.val+r+l);
            }
        }
        return max;
    }

}
