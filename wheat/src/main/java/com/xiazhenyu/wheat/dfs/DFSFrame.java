package com.xiazhenyu.wheat.dfs;

/**
 * Date: 2025/1/24
 * <p>
 * Description: dfs深度优先搜索框架
 *
 * @author xiazhenyu
 */
public class DFSFrame {


    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        //前序位置
        traverse(root.left);
        //中序位置
        traverse(root.right);
        //后序位置
    }


    public class TreeNode {

        int val;

        TreeNode left, right;

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }

}