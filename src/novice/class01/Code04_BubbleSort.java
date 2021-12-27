package novice.class01;

/**
 * 冒泡排序
 * @author 张志伟
 * @version v1.0
 */
public class Code04_BubbleSort {

    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        //0~n-1
        //0~n-2
        //0~n-3
        int N = arr.length;
        for (int end = N - 1; end >= 0; end--){
            //0~end间处理
            for(int second = 1; second <= end; second++){
                if (arr[second - 1] > arr[second]){
                    swap(arr,second - 1,second);
                }
            }
        }
    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args){
        int[] arr = {7,1,3,5,1,6,8,1,3,5,7,5,6};
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
    }
}
