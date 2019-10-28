package codes.simple;

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个 N 叉树，找到其最大深度。
 *
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 *
 */
public class MaximumDepthofNaryTree {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public int maxDepth(Node root) {
        if(root == null)return 0;
        return depth(root.children,1);
    }

    private int depth(List<Node> node,int dep){
        if(node == null || node.size() == 0)return dep;
        int max = ++dep;
        for(Node n:node){
            max = Math.max(depth(n.children,dep),max);
        }
        return max;
    }
}
