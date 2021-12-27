package system.class03;

/**
 * 删除单链表给定的值
 * @author 张志伟
 * @version v1.0
 */
public class Code02_DeleteGivenValue {
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 删除单链表中value为num的结点
     * @param head
     * @param num
     * @return
     */
    public static Node removeValue(Node head, int num) {
        //首先要考虑头结点是否需要删除，如果需要，找到我们返回的新头结点
        while (head != null){
            if (head.value != num){
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null){
            if (cur.value == num){
                pre.next = cur.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
