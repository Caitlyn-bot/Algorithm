package system.class25;

import java.util.Stack;

/**
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 * @author 张志伟
 * @version v1.0
 */
public class Code02_AllTimesMinToMax {
    /**
     * 暴力
     * @param arr
     * @return
     */
    public static int max1(int[] arr){
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MIN_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, sum * minNum);
            }
        }
        return max;
    }

    public static int max2(int[] arr){
        int size = arr.length;
        //前缀和数组
        int[] sum = new int[size];
        sum[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        int max = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                int index = stack.pop();
                max = Math.max(max,(stack.isEmpty() ? sum[i - 1] : (sum[i - 1] - sum[stack.peek()])) * arr[index]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int index = stack.pop();
            max = Math.max(max,(stack.isEmpty() ? sum[size - 1] : (sum[size - 1] - sum[stack.peek()])) * arr[index]);
        }
        return max;
    }
    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }
}
