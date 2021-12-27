package novice.class04;

/**
 * @author 张志伟
 * @version v1.0
 */
public class Code01_ReverseList {
    /**
     * 单链表结构
     */
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value = data;
        }
    }
    /**
     * 双链表结构
     */
    public static class DoubleNode{
        public int value;
        public DoubleNode last;
        public DoubleNode next;
        public DoubleNode(int data){
            this.value = data;
        }
    }

    /**
     * 反转单链表
     * @param head
     * @return
     */
    public Node reverseNode(Node head){
        Node pre = null;
        Node next = null;
        while (head != null){
            //改链
            //先将当前顺序的下一个结点用next记录
            next = head.next;
            //将当前节点的链子改为指向前一个结点
            head.next = pre;
            //移动
            //将当前节点转为上一个结点
            pre = head;
            //将下一个结点转换为当前结点
            head = next;
        }
        //当循环结束时，需要返回链表的头部
        return pre;
    }

    /**
     * 反转双链表
     * @param head
     * @return
     */
    public DoubleNode reverseDoubleNode(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null){
            //先保存下一个结点
            next = head.next;
            //修改两个指针的指向
            head.next = pre;
            head.last = next;
            //移动指针
            pre = head;
            head = next;

        }
        return pre;
    }

}
