package novice.class07;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 * @author 张志伟
 * @version v1.0
 */
public class Code01_BinaryTreeLevelOrderTraversalII {
    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null){
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            //队列中有几个数，就进行几次操作，将下一行的结点按顺序放入队列中
            int size = queue.size();
            List<Integer> curAns = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                //队列弹出一个元素
                TreeNode curNode = queue.poll();
                //将弹出的结点存入当前队列
                curAns.add(curNode.val);
                //将该元素对应的左右子结点按顺序添加到队列中去
                if (curNode.left != null){
                    queue.add(curNode.left);
                }
                if (curNode.right != null){
                    queue.add(curNode.right);
                }
            }
            //每次都将当前列存到头部，最终形成自底向上
            ans.add(0,curAns);
        }
        return ans;
    }
}
