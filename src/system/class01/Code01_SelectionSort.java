package system.class01;

import java.util.Arrays;

/**
 * 选择排序
 * @author 张志伟
 * @version v1.0
 */
public class Code01_SelectionSort {
    /**
     * 选择排序
     * @param arr
     */
    public static void selectionSort(int[] arr){
        if (arr == null || arr.length < 2){
            return ;
        }
        int length = arr.length;
        for (int i = 0 ; i < length; i++){
            int minIndex = i;
            for (int j = i; j < length; j++) {
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            //交换当前位置与当前剩余数中最大值的位置
            swap(arr,i,minIndex);
        }
    }

    /**
     * 交换数组的两数
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j){
        if (arr == null || arr.length == 1|| i == j){
            return;
        }
        if (i > arr.length || j > arr.length || i < 0 || j < 0){
            throw new RuntimeException("数组下标越界，无法交换");
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(int[] arr){
        if (arr == null || arr.length == 0){
            System.out.println("该数组为空");
            return;
        }
        for (int i = 0; i < arr.length ;i++){
            System.out.print(arr[i] + "\t");
        }
    }

    public static void comparator(int[] arr){
        Arrays.sort(arr);
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

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


    public static int[] copyArray(int[] arr){
        if (arr == null){
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int [] arr = {8,3,5,6,9,3,2,1};
        try {
            swap(arr,-1,2);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        selectionSort(arr);
        printArray(arr);
    }

}
