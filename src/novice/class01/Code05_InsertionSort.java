package novice.class01;

/**
 * 插入排序
 * @author 张志伟
 * @version v1.0
 */
public class Code05_InsertionSort {
    public static void insertSort1(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        //0~0有序
        //0~1有序
        //0~2有序
        //0~3有序
        int N = arr.length;
        for (int end = 1; end < N; end++){
            int newNumIndex = end;
            while (newNumIndex - 1 >= 0 && arr[newNumIndex - 1] > arr[newNumIndex]){
                swap(arr,newNumIndex - 1,newNumIndex);
                newNumIndex--;
            }
        }
    }
    public static void insertSort2(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        //0~0有序
        //0~1有序
        //0~2有序
        //0~3有序
        int N = arr.length;
        for (int end = 1; end < N; end++){
            for (int pre = end - 1;pre >= 0 && arr[pre] > arr[pre + 1];pre--){
                swap(arr,pre,pre + 1);
            }
        }
    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void printArray(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args){
        int[] arr = {7,1,3,5,1,6,8,1,3,5,7,5,6};
        printArray(arr);
        insertSort2(arr);
        printArray(arr);
    }
}
