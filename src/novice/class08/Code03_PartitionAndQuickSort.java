package novice.class08;

import java.util.Stack;

/**
 * 快速排序
 * @author 张志伟
 * @version v1.0
 */
public class Code03_PartitionAndQuickSort {
    /**
     * 以最后一个数p为标准
     * 从头开始，构建<=p区，
     * 如果<=p与<=区的下一个交换
     * 如果>p则，当前直接下一个
     * @param arr
     */
    public static void splitNum(int[] arr){
        //小于等于区的边界
        int lessEqualR = -1;
        //当前位置
        int index = 0;
        //最右侧的数
        int mostR = arr.length - 1;
        while (index < arr.length){
            if (arr[index] < arr[mostR]){
                swap(arr,lessEqualR + 1,index);
                lessEqualR ++;
                index++;
            }else {
                index++;
            }
        }
    }
    public static void splitNum2(int[] arr) {
        int N = arr.length;
        int lessR = -1;
        int moreL = N - 1;
        int index = 0;
        // arr[N-1]
        while (index < moreL) {
            if (arr[index] < arr[N - 1]) {
                swap(arr, ++lessR, index++);
            } else if (arr[index] > arr[N - 1]) {
                swap(arr, --moreL, index);
            } else {
                index++;
            }
        }
        swap(arr, moreL, N - 1);
    }

    public static int[] partition(int[] arr,int L,int R){
        int lessR = L - 1;
        int moreL = R;
        int index = L;
        while (index < moreL){
            if (arr[index] < arr[R]){
                swap(arr,index++,++lessR);
            }else if (arr[index] > arr[R]){
                //交换过来的数仍然要比较，所以index不动
                swap(arr,index,--moreL);
            }else {
                index++;
            }
        }
        //将moreL和标准交换，中间放=p的，将最后一个数归位到等于区域
        //和大于区的第一个做交换
        swap(arr,moreL,R);
        //返回两个边界
        return new int[]{lessR + 1,moreL};
    }

    public static void quickSort(int[] arr){
        if (arr.length < 2){
            return;
        }
        process(arr,0,arr.length - 1);
    }

    public static void process(int[] arr,int L,int R){
        if (L >= R){
            return;
        }
        int[] equalE = partition(arr, L, R);
        //equalE[0]是等于区域的第一个数
        //equalE[1]是等于区域的最后一个数
        process(arr, L,equalE[0] - 1);
        process(arr,equalE[1] + 1,R);
    }

    public static class Job {
        public int L;
        public int R;

        public Job(int left, int right) {
            L = left;
            R = right;
        }
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Stack<Job> stack = new Stack<>();
        stack.push(new Job(0, arr.length - 1));
        while (!stack.isEmpty()) {
            Job cur = stack.pop();
            int[] equals = partition(arr, cur.L, cur.R);
            if (equals[0] > cur.L) {
                // 有< 区域
                stack.push(new Job(cur.L, equals[0] - 1));
            }
            if (equals[1] < cur.R) {
                // 有 > 区域
                stack.push(new Job(equals[1] + 1, cur.R));
            }
        }
    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
