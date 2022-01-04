package system.class11;

import java.util.ArrayList;
import java.util.List;

/**
 * 森林改写二叉树：左孩子，右兄弟
 * 本题测试链接：https://leetcode-cn.com/problems/encode-n-ary-tree-to-binary-tree
 * @author 张志伟
 * @version v1.0
 */
public class Code03_EncodeNaryTreeToBinaryTree {

    public static class Node{
        public int val;
        public List<Node> children;

        public Node(){

        }
        public Node(int _val){
            this.val = _val;
        }
        public Node(int _val, List<Node> _children){
            this.val = _val;
            this.children = _children;
        }
    }

    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x){
            this.val = x;
        }
    }

    class Codec {

        public TreeNode encode(Node root) {
            if (root == null){
                return null;
            }
            TreeNode head = new TreeNode(root.val);
            head.left = en(root.children);
            return head;
        }

        /**
         * 左孩子，右兄弟
         * @param children
         * @return
         */
        private TreeNode en(List<Node> children){
            //head仅用来标记头结点的位置
            TreeNode head = null;
            TreeNode cur = null;
            for (Node child : children) {
                TreeNode tNode = new TreeNode(child.val);
                if (head == null){
                    head = tNode;
                }else {
                    //第一次挂的时候cur就是head，挂在右边的是兄弟
                    cur.right = tNode;
                }
                //当这是第一次循环时，cur就指向头结点head
                cur = tNode;
                //递归，将孩子挂在左边
                cur.left = en(child.children);
            }
            return head;
        }

        public Node decode(TreeNode root) {
            if (root == null){
                return null;
            }
            Node head = new Node(root.val,de(root.left));
            return head;
        }

        private List<Node> de(TreeNode root){
            List<Node> children = new ArrayList<Node>();
            while (root != null){
                Node cur = new Node(root.val,de(root.left));
                children.add(cur);
                //因为右边是兄弟，应该在同一个children下，所以向右移动
                root = root.right;
            }
            return children;
        }

    }

}
