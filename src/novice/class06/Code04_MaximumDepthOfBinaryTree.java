package novice.class06;

/**
 * 二叉树的最大深度
 * 测试链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * @author 张志伟
 * @version v1.0
 */
public class Code04_MaximumDepthOfBinaryTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }
}
