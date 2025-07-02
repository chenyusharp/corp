package com.xiazhenyu.wheat.bfs;


import com.xiazhenyu.wheat.dfs.DFSFrame.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Date: 2025/1/24
 * <p>
 * Description: bfs广度优先搜索框架
 *
 * @author xiazhenyu
 */
public class BFSFrame {


    /**
     *  写法一
     * @param root
     */
    public void levelOrderTraverse1(TreeNode root) {

        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            System.out.println(cur.getVal());

            //把cur的左右子节点加入队列
            if (cur.getLeft() != null) {
                queue.offer(cur.getLeft());
            }
            if (cur.getRight() != null) {
                queue.offer(cur.getRight());
            }
        }
    }


    /**
     * 写法二
     * @param root
     */
    public void levelOrderTraverse2(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        //记录当前遍历到的层数（根结点视为第一层）
        int depth = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = queue.poll();
                System.out.println("depth=" + depth + ",val=" + cur.getVal());
                //把cur的左右子节点加入队列
                if (cur.getLeft() != null) {
                    queue.offer(cur.getLeft());
                }
                if (cur.getRight() != null) {
                    queue.offer(cur.getRight());
                }
            }
            depth++;
        }
    }


    /**
     * 写法三
     * @param root
     */
    public void levelOrderTraverse3(TreeNode root) {

        if (root == null) {
            return;
        }
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(root, 1));

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            // 访问 cur 节点，同时知道它的路径权重和
            System.out.println("depth = " + cur.depth + ", val = " + cur.node.getVal());
            //把cur的左右子节点加入队列
            if (cur.node.getLeft() != null) {
                queue.offer(new State(cur.node.getLeft(), cur.getDepth() + 1));
            }
            if (cur.node.getRight() != null) {
                queue.offer(new State(cur.node.getRight(), cur.getDepth() + 1));
            }
        }
    }


    public class State {

        TreeNode node;
        int depth;

        public State(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }

        public TreeNode getNode() {
            return node;
        }

        public void setNode(TreeNode node) {
            this.node = node;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }
    }


}