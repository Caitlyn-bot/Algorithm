package system.class09;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * 把链表放入数组里，在数组上做partition（笔试用）
 * 分成小、中、大三部分，再把各个部分之间串起来（面试用）
 * @author 张志伟
 * @version v1.0
 */
public class Code03_SmallerEqualBigger {
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 使用数组分割
     * @param head
     * @param pivot 标准值
     * @return
     */
    public static Node listPartition1(Node head, int pivot){
        if (head == null){
            return head;
        }
        Node cur = head;
        int i = 0;
        //获取链表的长度
        while (cur != null){
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        i =  0;
        cur = head;
        for (i = 0; i < nodeArr.length; i++){
            nodeArr[i] = cur;
            cur = cur.next;
        }
        //按照快排的思路划分大于等于和小于区
        arrPartition(nodeArr,pivot);
        //将单个的Node结点连接成单链表
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    /**
     * 按照快排的思路划分大于等于和小于区
     * @param nodeArr
     * @param pivot
     */
    public static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big){
            if (nodeArr[index].value < pivot){
                swap(nodeArr, ++small, index++);
            }else if (nodeArr[index].value == pivot){
                index++;
            }else {
                swap(nodeArr,--big,index);
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    /**
     * 使用有限变量分割调整
     * @param head
     * @param pivot
     * @return
     */
    public static Node listPartition2(Node head, int pivot){
        // small head
        Node sH = null;
        // small tail
        Node sT = null;
        // equal head
        Node eH = null;
        // equal tail
        Node eT = null;
        // big head
        Node mH = null;
        // big tail
        Node mT = null;
        // save next node
        Node next = null;
        // every node distributed to three lists
        while (head != null){
            //保存下一个结点
            next = head.next;
            //将当前结点从链上取下
            head.next = null;
            if (head.value < pivot){
                if (sH == null){
                    sH = head;
                    sT = head;
                }else {
                    sT.next = head;
                    sT = sT.next;
                }
            }else if (head.value == pivot){
                if (eH == null){
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = eT.next;
                }
            }else {
                if (mH == null){
                    mH = head;
                    mT = head;
                }else {
                    mT.next = head;
                    mT = mT.next;
                }
            }
            //移动指针
            head = next;
        }
        // 小于区域的尾巴，连等于区域的头，等于区域的尾巴连大于区域的头
        if (sT != null) {
            // 如果有小于区域
            sT.next = eH;
            // 下一步，谁去连大于区域的头，谁就变成eT
            eT = eT == null ? sT : eT;
        }
        // 下一步，一定是需要用eT 去接 大于区域的头
        // 有等于区域，eT -> 等于区域的尾结点
        // 无等于区域，eT -> 小于区域的尾结点
        // eT 尽量不为空的尾巴节点
        if (eT != null) {
            // 如果小于区域和等于区域，不是都没有
            eT.next = mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
//         head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }



}
