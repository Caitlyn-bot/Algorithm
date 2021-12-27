package novice.class04;

/**
 * 利用单链表求两数之和
 * @author 张志伟
 * @version v1.0
 */
public class Code05_AddTwoNumbers {
    public static class ListNode{
        public int val;
        public ListNode next;
        public ListNode(int value){
            this.val = value;
        }
        public ListNode(int value ,ListNode next){
            this.val = value;
            this.next = next;
        }
    }

    public static ListNode addTwoNumbers(ListNode node1,ListNode node2){
        int length1 = listLength(node1);
        int length2 = listLength(node2);
        ListNode l = length1 > length2 ? node1 : node2;
        ListNode s = l == node1 ? node2 : node1;
        ListNode curL = l;
        ListNode curS = s;

        //carry表示是否需要进位
        int carry = 0;
        //curNum代表当前位的数
        int curNum = 0;
        //我们将长的链表改造并返回
        //last会决定最后是否进位创建新的结点
        ListNode last = curL;
        //第一阶段，长链表和短链表都不为空
        while (curS != null){
            //注意不要忘记加上进位的值
            curNum = curL.val + curS.val + carry;
            curL.val = (curNum % 10);
            carry = curNum / 10;
            //记录最新的last位置
            last = curL;
            curL = curL.next;
            curS = curS.next;
        }
        //第二阶段，短链表为空，长链表不为空
        while (curL != null){
            curNum = curL.val + carry;
            curL.val = (curNum % 10);
            carry = curNum / 10;
            //记录最新的last位置
            last = curL;
            curL = curL.next;
        }
        //第三阶段，长链表和短链表都为空
        if (carry != 0){
            //说明需要进位创建新的结点
            last.next = new ListNode(carry);
        }
        return l;
    }

    /**
     * 获取链表的长度
     * @param head
     * @return
     */
    public static int listLength(ListNode head){
        int length = 0;
        while (head != null){
            head = head.next;
            length ++;
        }
        return length;
    }
}
