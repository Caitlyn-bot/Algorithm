package novice.class06;

import java.util.HashMap;

/**
 * 从前序与中序遍历序列构造二叉树
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * @author 张志伟
 * @version v1.0
 */
public class Code05_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length){
            return null;
        }
        return f(preorder,0,preorder.length - 1,inorder,0,inorder.length - 1);
    }
    public static TreeNode f(int[] pre, int L1, int R1, int[] in, int L2, int R2) {
        if (L1 > R1){
            return null;
        }
        TreeNode head = new TreeNode(pre[L1]);
        if (L1 == R1){
            //当前树只有一个结点
            return head;
        }
        //在中序遍历中找到根结点
        int find = L2;
        while (in[find] != pre[L1]){
            find ++;
        }
        //当前长度为find-1-L2 :L1 + 1 + (find - 1 - L2) = L1 + find - L2
        head.left = f(pre,L1 + 1, L1 + find  - L2, in, L2,find - 1);
        head.right = f(pre,L1 + find - L2 + 1, R1, in, find + 1, R2);
        return head;
    }

    public static TreeNode buildTree2(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        HashMap<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            valueIndexMap.put(in[i], i);
        }
        return g(pre, 0, pre.length - 1, in, 0, in.length - 1, valueIndexMap);
    }



    /**
     * 有一棵树，先序结果是pre[L1...R1]，中序结果是in[L2...R2]
     * 请建出整棵树返回头节点
     * @param pre
     * @param L1
     * @param R1
     * @param in
     * @param L2
     * @param R2
     * @param valueIndexMap
     * @return
     */
    public static TreeNode g(int[] pre, int L1, int R1, int[] in, int L2, int R2,
                             HashMap<Integer, Integer> valueIndexMap) {
        if (L1 > R1) {
            return null;
        }
        TreeNode head = new TreeNode(pre[L1]);
        if (L1 == R1) {
            return head;
        }
        int find = valueIndexMap.get(pre[L1]);
        head.left = g(pre, L1 + 1, L1 + find - L2, in, L2, find - 1, valueIndexMap);
        head.right = g(pre, L1 + find - L2 + 1, R1, in, find + 1, R2, valueIndexMap);
        return head;
    }

}
