package system.class24;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 * @author 张志伟
 * @version v1.0
 */
public class Code02_AllLessNumSubArray {
    /**
     * 暴力实现对数器
     * @param arr
     * @param sum
     * @return
     */
    public static int right(int[] arr, int sum){
        if (arr == null || arr.length == 0 || sum < 0){
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R ; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 如果在L->R上达标，则其内部子数组一定达标
     * 如果在L->R上不达标，再向右扩也是不达标
     * @param arr
     * @param sum
     * @return
     */
    public static int num(int[] arr, int sum){
        if (arr == null || arr.length == 0 || sum < 0){
            return 0;
        }
        int N = arr.length;
        int count = 0;
        LinkedList<Integer> maxWindow = new LinkedList<Integer>();
        LinkedList<Integer> minWindow = new LinkedList<Integer>();
        int R = 0;
        for (int L = 0; L < N; L++) {
            //R向右扩，扩到第一次不达标为止
            while (R < N){
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[R]){
                    maxWindow.pollLast();
                }
                maxWindow.addLast(R);
                //将最小的放到最左
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[R]){
                    minWindow.pollLast();
                }
                minWindow.addLast(R);
                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > sum){
                    break;
                }else {
                    R++;
                }
            }
            count += R - L;
            //去掉maxWindow和minWindow的即将过期的最大值
            if (maxWindow.peekFirst() == L){
                maxWindow.pollFirst();
            }
            if (minWindow.peekFirst() == L){
                minWindow.pollFirst();
            }
        }
        return count;
    }

    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = num(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }
}
