package system.class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 用队列实现栈
 * @author 张志伟
 * @version v1.0
 */
public class Code07_TwoQueueImplementStack {

    public static class TwoQueueStack<T>{
        public Queue<T> queue;
        public Queue<T> help;
        public TwoQueueStack(){
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        /**
         * 将value添加到queue中
         * @param value
         */
        public void push(T value){
            //offer方法返回add方法的返回值
            queue.offer(value);
        }

        /**
         * 弹出
         * @return
         */
        public T poll(){
            //当队列中有大于一个元素时，将元素poll出放入help中
            while (queue.size() > 1){
                help.offer(queue.poll());
            }
            //将队列中剩余的那个元素就是目标元素
            T ans = queue.poll();
            //将两个队列复原，数据存到queue中
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return ans;
        }

        public T peek(){
            while (queue.size() > 1){
                help.offer(queue.poll());
            }
            T ans = queue.poll();
            help.offer(ans);
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return ans;
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }

    }
    public static void main(String[] args) {
        System.out.println("test begin");
        TwoQueueStack<Integer> myStack = new TwoQueueStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.poll().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");

    }
}
