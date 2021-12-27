package novice.class07;

/**
 * 判断二叉搜索时
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 * @author 张志伟
 * @version v1.0
 */
public class Code05_IsBinarySearchTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info{
        public boolean isBST;
        public int max;
        public int min;
        public Info(boolean isBST, int max, int min){
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public boolean isValidBST(TreeNode root) {
        return process(root).isBST;
    }

    public Info process(TreeNode root){
        if (root == null){
            return null;
        }

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        int max = root.val;
        int min = root.val;

        if (leftInfo != null){
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null){
            max = Math.max(max,rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }
        boolean isBST = true;

        if (leftInfo != null && !leftInfo.isBST){
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST){
            isBST = false;
        }
        boolean leftMaxLessRoot = leftInfo == null ? true : (leftInfo.max < root.val);
        boolean rightMinMoreRoot = rightInfo == null ? true : (rightInfo.min > root.val);
        if (!(leftMaxLessRoot && rightMinMoreRoot)){
            isBST = false;
        }
        return new Info(isBST,max,min);
    }
}
