package system.class04;

/**
 * 分别使用递归和迭代实现
 * 归并排序
 * @author 张志伟
 * @version v1.0
 */
public class Code01_MergeSort {
    /**
     * 使用递归实现归并排序
     * @param arr
     */
    public static void mergeSort(int[] arr){
        if (arr == null || arr.length == 0){
            return;
        }
        process(arr,0,arr.length - 1);
    }

    /**
     * 使arr在L到R范围上有序
     * @param arr
     * @param L
     * @param R
     */
    public static void process(int[] arr,int L,int R){
        if (L == R){
            return;
        }
        int mid = L + ((R - L) >> 1);
        //向左递归
        process(arr,L,mid);
        //向右递归
        process(arr,mid + 1,R);
        merge(arr,L,mid,R);
    }

    public static void merge(int[] arr, int L, int M, int R){
        int [] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //要么p1越界，要么p2越界
        //即下面的两个while循环只会有一个执行
        while (p1 <= M){
            help[i++] = arr[p1++];
        }
        while (p2 <= R){
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    /**
     * 使用迭代实现归并排序
     * @param arr
     */
    public static void mergeSortIteration(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int N = arr.length;
        //定义步长
        int mergeSize = 1;
        while (mergeSize < N){
            //当前左组的第一个位置
            int L = 0;
            while (L < N){
                if (mergeSize >= N - L){
                    break;
                }
                int M = L + mergeSize - 1;
                //要考虑右边界
                int R = M + Math.min(mergeSize,N - M - 1);
                merge(arr,L,M,R);
                L = R + 1;
            }
            // 防止溢出
            if (mergeSize > N / 2) {
                break;
            }
            //左移1位等于乘2
            mergeSize <<= 1;
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
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

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort(arr1);
            mergeSortIteration(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }


}
