package system.class01;

import java.util.Arrays;

/**
 * @author 张志伟
 * @version v1.0
 */
public class Code04_BSExist {
    /**
     * 查找一个有序数组中是否存在一个数
     * @param sortedArr 升序数组
     * @param num
     * @return
     */
    public static boolean exist(int[] sortedArr, int num){
        if (sortedArr == null || sortedArr.length == 0){
            return false;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        while (L < R){
            //采用+是因为R+L可能会溢出，使用位移比使用除二的常数时间更短
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] == num){
                return true;
            }else if (sortedArr[mid] > num){
                R = mid - 1;
            }else {
                L = mid + 1;
            }
        }
        //当退出循环时，L = R;
        return sortedArr[L] == num;
    }

    public static boolean test(int[] sortedArr, int num) {
        for(int cur : sortedArr) {
            if(cur == num) {
                return true;
            }
        }
        return false;
    }


    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != exist(arr, value)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
