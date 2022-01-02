package system.class09;

import java.util.HashMap;

/**
 * 复制带随机指针的链表
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 * 要求：时间复杂度O(N)，额外空间复杂度O(1)
 * https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 * @author 张志伟
 * @version v1.0
 */
public class Code04_CopyListWithRandom {

    public static class Node{
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 借助hashMap实现复制链表
     * @param head
     * @return
     */
    public static Node copyRandomList1(Node head){
        //key老结点
        //value新结点
        HashMap<Node,Node> map = new HashMap<Node,Node>();
        Node cur = head;
        //给所有的结点创建对应的新结点
        while (cur != null){
            map.put(cur,new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node copyRandomList2(Node head){
        if (head == null){
            return head;
        }
        Node cur = head;
        Node next = null;
        // 1 -> 2 -> 3 -> null
        // 1 -> 1' -> 2 -> 2' -> 3 -> 3'
        while (cur != null){
            //先保存原结点的下一个结点
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node copy = null;
        // 1 1' 2 2' 3 3'
        // 依次设置 1' 2' 3' random指针
        while (cur != null){
            next = cur.next.next;
            copy = cur.next;
            copy.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        // 老 新 混在一起，next方向上，random正确
        // next方向上，把新老链表分离
        while (cur != null){
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }


}
