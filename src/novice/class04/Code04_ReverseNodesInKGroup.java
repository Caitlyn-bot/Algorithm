package novice.class04;

/**
 * K个一组反转链表
 * 测试链接：https://leetcode.com/problems/reverse-nodes-in-k-group/
 * @author 张志伟
 * @version v1.0
 */
public class Code04_ReverseNodesInKGroup {
    public static class ListNode{
        public int val;
        public ListNode next;
    }
    public static ListNode reverseKGroup(ListNode head, int k){
        ListNode start = head;
        ListNode end = getKGroupEnd(head, k);
        if (end == null){
            return head;
        }
        //将end保存
        head = end;
        reverse(start,end);
        ListNode lastEnd = start;
        while (lastEnd.next != null){
            start = lastEnd.next;
            end = getKGroupEnd(start,k);
            if (end == null){
                return head;
            }
            reverse(start,end);
            //将上一组的结尾连到这一组反转后的开头
            lastEnd.next = end;
            //移动指针
            lastEnd = start;
        }
        return head;
    }

    /**
     * 返回k个结点一组的该组的尾结点
     * @param start
     * @param k
     * @return
     */
    public static ListNode getKGroupEnd(ListNode start, int k){
        while (--k != 0 && start != null){
            start = start.next;
        }
        return start;
    }

    /**
     * 反转分组后的链表，并将反转后start指向原本end的下一个结点
     * @param start
     * @param end
     */
    public static void reverse(ListNode start, ListNode end) {
        end = end.next;
        ListNode pre = null;
        ListNode next = null;
        ListNode cur = start;
        while (cur != end){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }
}
