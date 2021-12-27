package novice.class07;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * https://leetcode-cn.com/problems/path-sum-ii/
 * @author 张志伟
 * @version v1.0
 */
public class Code04_PathSumII {
    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null){
            return ans;
        }
        List<Integer> path = new LinkedList<>();
        process(root,0,targetSum,ans,path);
        return ans;
    }
    public void process(TreeNode x,int preSum,int sum,List<List<Integer>> ans,List<Integer> path){
        if (x.left == null && x.right == null){
            if (x.val + preSum == sum){
                path.add(x.val);
                //将当前的路径拷贝添加到结果中去
                ans.add(copy(path));
                //清空当前这一级路径，用于存放可能存在的下一条路径
                path.remove(path.size() - 1);
            }
            return;
        }
        //x是非叶子结点
        path.add(x.val);
        preSum += x.val;
        if (x.left != null){
            process(x.left, preSum, sum, ans, path);
        }
        if (x.right != null){
            process(x.right, preSum, sum, ans, path);
        }
        //如果当前结点
        path.remove(path.size() - 1);
    }

    public static List<Integer> copy(List<Integer> path) {
        List<Integer> ans = new LinkedList<>();
        for (Integer num : path) {
            ans.add(num);
        }
        return ans;
    }

}
