package novice.class06;

/**
 * 相同的树
 * 测试链接：https://leetcode-cn.com/problems/same-tree
 * @author 张志伟
 * @version v1.0
 */
public class Code02_SameTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null){
            return false;
        }
        if (p == null && q == null){
            return true;
        }
        //都不为空
        return p.val == q.val && isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }
}
