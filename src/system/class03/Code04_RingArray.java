package system.class03;

/**
 * 使用数组实现队列
 * @author 张志伟
 * @version v1.0
 */
public class Code04_RingArray {
    public static class MyQueue{
        private int[] arr;
        /**
         * end
         */
        private int pushI;
        /**
         * begin
         */
        private int pollI;
        /**
         * 当前队列有多少个数据
         */
        private int size;
        /**
         * limit表示该队列的最大容量，及数组arr的容量
         */
        private final int limit;

        public MyQueue(int limit){
            arr = new int[limit];
            pushI = 0;
            pollI = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value){
            if (limit == size){
                throw new RuntimeException("队列已满，无法继续加入");
            }
            size++;
            arr[pushI] = value;
            pushI = nextIndex(pushI);
        }

        public int pop(){
            if (size == 0){
                throw new RuntimeException("队列已空，无法继续取出");
            }
            size--;
            int ans = arr[pollI];
            pollI = nextIndex(pollI);
            return ans;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        /**
         * 如果现在的下标是i，返回下一个位置
         * @param i
         * @return
         */
        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }

    }
}
