package system.class06;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 堆
 * @author 张志伟
 * @version v1.0
 */
public class Code02_Heap {
    /**
     * 数组中某一结点的左右孩子分别为2*i+1和2*i+2
     * 父节点(i-1)/2
     */
    public static class MyMaxHeap{
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit){
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty(){
            return heapSize == 0;
        }

        public boolean isFull(){
            return heapSize == limit;
        }

        public void push(int value){
            if (isFull()){
                throw new RuntimeException("heap is full!");
            }
            heap[heapSize] = value;
            heapInsert(heap,heapSize++);
        }

        public int pop(){
            //堆顶
            int ans = heap[0];
            swap(heap,0,--heapSize);
            heapify(heap,0,heapSize);
            return ans;
        }

        /**
         * 新加进来的数，现在停在了index位置，请依次往上移动，
         * 移动到0位置，或者干不掉自己的父亲了，停！
         * @param arr
         * @param index
         */
        private void heapInsert(int[] arr,int index){
            //(0 - 1) / 2 = 0
            while (arr[index] > arr[(index - 1) / 2]){
                swap(arr,index,(index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        /**
         * 从index位置不断向下沉，与左右孩子中较大的进行比较，如果孩子大，则交换，知道没有孩子或孩子比自己小
         * @param arr
         * @param index
         * @param heapSize
         */
        private void heapify(int[] arr,int index,int heapSize){
            //左孩子
            int left = 2 * index + 1;
            while (left < heapSize){
                //找到较大孩子的下标
                //left + 1表示右孩子
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                largest = arr[largest] > arr[index] ? largest : index;
                if (largest == index){
                    //当较大孩子比自己小时，下沉结束，break
                    break;
                }
                //index与较大孩子交换
                swap(heap,index,largest);
                //进行迭代
                index = largest;
                left = 2 * index + 1;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

    }

    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }


    public static class MyComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

    public static void main(String[] args) {
        // 小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(3);
        //  5 , 3
        System.out.println(heap.peek());
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        System.out.println(heap.peek());
        while(!heap.isEmpty()) {
            System.out.println(heap.poll());
        }







        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

    }

}
