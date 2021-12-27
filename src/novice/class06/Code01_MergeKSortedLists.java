package novice.class06;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 利用小根堆合成N个单链表
 * @author 张志伟
 * @version v1.0
 */
public class Code01_MergeKSortedLists {
    public static class ListNode {
        public int val;
        public ListNode next;
    }
    public static class ListNodeComparator implements Comparator<ListNode>{

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0){
            return null;
        }
        //建立小根堆
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
        //将每一条链表的头结点放入小根堆
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null){
                heap.add(lists[i]);
            }
        }
        if (heap.isEmpty()){
            return null;
        }
        ListNode head = heap.poll();
        //将头结点的next放入堆中
        if (head.next != null){
            heap.add(head.next);
        }
        ListNode pre = head;
        while (!heap.isEmpty()){
            ListNode cur = heap.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next != null){
                //要记得将cur结点的next放入堆中
                heap.add(cur.next);
            }
        }
        return head;
    }
}
