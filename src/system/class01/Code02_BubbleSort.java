package system.class01;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author 张志伟
 * @version v1.0
 */
public class Code02_BubbleSort {
    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        for (int e = arr.length - 1; e > 0; e--) {
            // 0 ~ e
            for (int i = 0; i < e; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
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

    public static void main(String[] args) {
        int [] arr = {8,3,5,6,9,3,2,1};
        bubbleSort(arr);
        printArray(arr);
    }
}
