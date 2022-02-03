package system.class27;

import java.util.ArrayList;

/**
 * 给定两棵二叉树的头节点head1和head2，返回head1中是否有某个子树的结构和head2完全一样
 *
 * 使用先序遍历将两棵树序列化成字符串，用KMP
 * 注意：要补空
 * @author 张志伟
 * @version v1.0
 */
public class Code02_TreeEqual {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int v){
            this.value = v;
        }
    }

    /**
     * 暴力递归
     * @param big
     * @param small
     * @return
     */
    public static boolean containsTree1(Node big, Node small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        if (isSameValueStructure(big, small)) {
            return true;
        }
        return containsTree1(big.left, small) || containsTree1(big.right, small);
    }

    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left)
                && isSameValueStructure(head1.right, head2.right);
    }

    public static boolean containsTree2(Node big, Node small) {
        if (small == null){
            return true;
        }
        if (big == null){
            return false;
        }
        ArrayList<String> b = preSerial(big);
        ArrayList<String> s = preSerial(small);
        String[] str = new String[b.size()];
        for (int i = 0; i < str.length; i++) {
            str[i] = b.get(i);
        }

        String[] match = new String[s.size()];
        for (int i = 0; i < match.length; i++) {
            match[i] = s.get(i);
        }
        return getIndexOf(str, match) != -1;
    }



    private static ArrayList<String> preSerial(Node node) {
        ArrayList<String> ans = new ArrayList<>();
        pres(node,ans);
        return ans;
    }

    private static void pres(Node node, ArrayList<String> ans) {
        if (node == null){
            ans.add(null);
        }else {
            ans.add(String.valueOf(node.value));
            pres(node.left, ans);
            pres(node.right, ans);
        }
    }

    private static int getIndexOf(String[] str, String[] match) {
        if (str == null || match == null || str.length < 1 || str.length < match.length){
            return -1;
        }
        int x = 0;
        int y = 0;
        int[] next = getNextArray(match);
        while (x < str.length && y < match.length){
            if (isEqual(str[x], match[y])){
                x++;
                y++;
            }else if (next[y] == -1){
                //说明已经回到match的头部
                x++;
            }else {
                y = next[y];
            }
        }
        return y == match.length ? x - y : -1;
    }



    private static int[] getNextArray(String[] match) {
        if (match.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int p = 2;
        int cn = 0;
        while (p < match.length){
            if (isEqual(match[p - 1], match[cn])){
                next[p++] = ++cn;
            }else if (cn > 0){
                //如果上一位相同这一位不同
                cn = next[cn];
            }else {
                next[p++] = 0;
            }
        }
        return next;
    }

    private static boolean isEqual(String s, String match) {
        if (s == null && match == null){
            return true;
        }else {
            if (s == null || match == null){
                return false;
            }else {
                return s.equals(match);
            }
        }
    }

    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int bigTreeLevel = 7;
        int smallTreeLevel = 4;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node big = generateRandomBST(bigTreeLevel, nodeMaxValue);
            Node small = generateRandomBST(smallTreeLevel, nodeMaxValue);
            boolean ans1 = containsTree1(big, small);
            boolean ans2 = containsTree2(big, small);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }

}
