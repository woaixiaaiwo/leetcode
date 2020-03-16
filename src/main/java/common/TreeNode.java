package common;

import codes.normal.MorrisTraversal;

public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        TreeNode(int x) { val = x; }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
        
        public static TreeNode createTreeNode(Integer[] arr){
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
}