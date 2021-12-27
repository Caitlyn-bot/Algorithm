package novice.class04;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 利用单链表实现队列和栈
 * @author 张志伟
 * @version v1.0
 */
public class Code02_LinkedListToQueueAndStack {

    public static class Node<V>{
        public V value;
        public Node<V> next;
        public Node(V v) {
            this.value = v;
        }
    }

    public static class MyQueue<V>{
        /**
         * 队列头结点
         */
        private Node<V> head;
        /**
         * 队列尾结点
         */
        private Node<V> tail;
        /**
         * size表示队列的大小
         */
        private int size;

        public MyQueue(){
            head = null;
            tail = null;
            size = 0;
        }

        /**
         * 判断当前队列是否为空
         * @return
         */
        public boolean isEmpty(){
            return size == 0;
        }

        /**
         * 返回队列的大小
         * @return
         */
        public int size() {
            return size;
        }

        /**
         * 添加元素到队列
         * @param value 待添加的元素
         * @return
         */
        public void offer(V value){
            //给待添加元素创建对应的结点
            Node<V> cur = new Node<V>(value);
            if (tail == null){
                //如果tail为空，说明队列为空
                head = cur;
                tail = cur;
            }else {
                //当队尾不为空时,将新结点添加到原队尾的后面
                tail.next = cur;
                //将新的结点标记为队尾，更新队尾的位置
                tail = cur;
            }
            //每添加一个结点，size++
            size++;
        }

        /**
         * 弹出队列元素
         * 先进先出
         * @return
         */
        public V poll() {
            V ans = null;
            if (head != null) {
                //只需要移动head指针，不需要释放被弹出的元素
                //JVM会自动释放不可达的元素
                ans = head.value;
                head = head.next;
                size--;
            }
            if (head == null) {
                tail = null;
            }
            return ans;
        }

        /**
         * 获取待弹出的结点的值，但不是真的弹出
         * @return
         */
        public V peek() {
            V ans = null;
            if (head != null) {
                ans = head.value;
            }
            return ans;
        }

    }

    public static class MyStack<V>{
        private Node<V> head;
        private int size;

        public MyStack() {
            head = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void push(V value) {
            Node<V> cur = new Node<>(value);
            if (head == null) {
                head = cur;
            } else {
                cur.next = head;
                head = cur;
            }
            size++;
        }

        public V pop() {
            V ans = null;
            if (head != null) {
                ans = head.value;
                head = head.next;
                size--;
            }
            return ans;
        }

        public V peek() {
            return head != null ? head.value : null;
        }
    }

    public static void testQueue() {
        MyQueue<Integer> myQueue = new MyQueue<>();
        Queue<Integer> test = new LinkedList<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myQueue.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myQueue.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * maxValue);
                myQueue.offer(num);
                test.offer(num);
            } else if (decide < 0.66) {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.poll();
                    int num2 = test.poll();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myQueue.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myQueue.isEmpty()) {
            int num1 = myQueue.poll();
            int num2 = test.poll();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

    public static void testStack() {
        MyStack<Integer> myStack = new MyStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myStack.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * maxValue);
                myStack.push(num);
                test.push(num);
            } else if (decide < 0.66) {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.pop();
                    int num2 = test.pop();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myStack.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myStack.isEmpty()) {
            int num1 = myStack.pop();
            int num2 = test.pop();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

    public static void main(String[] args) {
        testQueue();
        testStack();
    }

}
