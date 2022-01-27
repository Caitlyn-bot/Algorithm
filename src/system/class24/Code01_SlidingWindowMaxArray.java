package system.class24;

import java.util.LinkedList;

/**
 * 滑动窗口:窗口内最大值或最小值的更新结构
 * 用题目来学习窗口内最大值或最小值的更新结构提供的便利性
 * 窗口内最大值或最小值更新结构的实现
 * 假设一个固定大小为W的窗口，依次划过arr，
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 * @author 张志伟
 * @version v1.0
 */
public class Code01_SlidingWindowMaxArray {
    /**
     * 暴力对数器
     * @param arr
     * @param w
     * @return
     */
    public static int[] right(int[] arr, int w){
        if (arr == null || w < 1 || arr.length < w){
            return null;
        }
        int N = arr.length;
        int[] res = new int[N - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < N) {
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);
            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }

    public static int[] getMaxWindow(int[] arr, int w){
        if (arr == null || w < 1 || arr.length < w){
            return null;
        }
        //qMax窗口最大值的更新结构
        LinkedList<Integer> qMax = new LinkedList<Integer>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int R = 0; R < arr.length; R++) {
            //将队列中小于等于当前数全部丢掉
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[R]){
                qMax.pollLast();
            }
            //将当前数的下标放入队列
            qMax.addLast(R);
            //如果现在的最大值超出了窗口范围
            if (qMax.peekFirst() == R - w){
                qMax.pollFirst();
            }
            if (R >= w - 1){
                //说明窗口已经准备就绪，可以开始取最大值了
                res[index++] = arr[qMax.peekFirst()];
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxWindow(arr, w);
            int[] ans2 = right(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
