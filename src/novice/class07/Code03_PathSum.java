package novice.class07;

/**
 * 判断该树中是否存在 根节点到叶子节点的路径
 * https://leetcode-cn.com/problems/path-sum/
 * @author 张志伟
 * @version v1.0
 */
public class Code03_PathSum {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static boolean isSum = false;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null){
            return false;
        }
        //防止重复调用该方法将isSum置为true，所以每次调用重置为false
        isSum = false;
        process(root, 0, targetSum);
        return isSum;
    }

    public static void process(TreeNode x, int preNum, int sum){
        if (x.left == null && x.right == null){
            //x是叶子结点
            if (x.val + preNum == sum){
                isSum = true;
            }
            return;
        }
        //x是非叶子结点
        preNum += x.val;
        if (x.left != null){
            process(x.left,preNum,sum);
        }
        if (x.right != null){
            process(x.right,preNum,sum);
        }
    }
}
