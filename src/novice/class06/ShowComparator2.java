package novice.class06;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 优先队列
 * 默认是小根堆
 * @author 张志伟
 * @version v1.0
 */
public class ShowComparator2 {
    /**
     * 重写比较器，将优先队列修改为大根堆
     */
    public static class MyComparator implements Comparator<Integer>{
        /**
         *
         * @param o1
         * @param o2
         * @return
         */
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(8);
        heap.add(2);
        heap.add(3);
        heap.add(1);
        heap.add(4);
        heap.add(5);
        System.out.println(heap.peek());
        heap.add(0);
        System.out.println(heap.peek());
        System.out.println("==============");
        while (!heap.isEmpty()){
            System.out.println(heap.poll());
        }
    }
}
