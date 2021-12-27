package novice.class02;

/**
 * 对数器
 * @author 张志伟
 * @version v1.0
 */
public class Code03_Comp {

    /**
     * 返回arr数组，长度在[0,maxLen]
     * 值在[0,maxValue]
     * @param maxLen
     * @param maxValue
     * @return
     */
    public static int[] lenRandomValueRandom(int maxLen,int maxValue){
        int len = (int)(Math.random() * maxLen);
        int[] ans = new int[len];
        for (int i = 0; i < len; i++){
            ans[i] = (int) (Math.random() * maxValue);
        }
        return ans;
    }

    /**
     * 拷贝数组
     * @param arr
     * @return
     */
    public static int[] copyArray(int[] arr){
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++){
            ans[i] = arr[i];
        }
        return ans;
    }

    /**
     * arr1和arr2等长
     * @param arr1
     * @param arr2
     * @return
     */
    public static boolean equalsArray(int[] arr1,int[] arr2){
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }

    public static boolean isSorted(int[] arr){
        if (arr.length < 2){
            return true;
        }
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max > arr[i]){
                return false;
            }
            max = Math.max(max,arr[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        int maxLen = 50;
        int maxValue = 1000;
        int testTime = 10000;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = lenRandomValueRandom(maxLen,maxValue);
            int[] temp = copyArray(arr1);
            selectSort(arr1);
            if (!isSorted(arr1)){
                for (int j = 0; j < temp.length; j++) {
                    System.out.print(temp[j] + "");
                }
                System.out.println("选择排序错误");
            }
        }
    }

    public static void selectSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int N = arr.length;
        //0~n-1
        //1~n-1
        //2~n-1
        for (int i = 0; i < N; i++){
            //i~n-1
            int minValueIndex = i;
            for (int j = i + 1; j < N; j++){
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr,i,minValueIndex);
        }
    }

    public static void insertSort(int[] arr){
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

}
