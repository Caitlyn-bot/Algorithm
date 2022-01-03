package system.class10;

import java.util.Stack;

/**
 * 不使用递归，采用压栈的方式遍历二叉树
 * @author 张志伟
 * @version v1.0
 */
public class Code03_UnRecursiveTraversalBT {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 使用迭代实现先序遍历
     * @param head
     */
    public static void pre(Node head){
        System.out.print("pre-order: ");
        if (head != null){
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()){
                head = stack.pop();
                System.out.print(head.value + " ");
                //因为是栈，先序遍历是根左右，要先将右压入栈中
                if (head.right != null){
                    stack.push(head.right);
                }
                if (head.left != null){
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    /**
     * 使用迭代实现中序遍历
     * @param head
     */
    public static void in(Node head){
        System.out.print("in-order: ");
        if (head != null){
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null){
                if (head != null){
                    //先将一条线所有的左孩子放到栈中
                    stack.push(head);
                    head = head.left;
                }else {
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    //将当前结点的右结点放入
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    /**
     * 使用迭代+双栈实现后续遍历
     * @param head
     */
    public static void pos1(Node head){
        System.out.print("pos-order: ");
        if (head != null){
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(head);
            while (!s1.isEmpty()){
                //根右左
                head = s1.pop();
                s2.push(head);
                if (head.left != null){
                    s1.push(head.left);
                }
                if (head.right != null){
                    s2.push(head.right);
                }
            }
            // 左 右 根
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " ");
            }
        }
        System.out.println();
    }

    /**
     * 使用迭代+单栈实现后续遍历
     * @param head
     */
    public static void pos2(Node head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(head);
            Node c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && head != c.left && head != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && head != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    head = c;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");
    }

}
