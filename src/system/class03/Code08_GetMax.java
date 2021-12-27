package system.class03;

/**
 * @author 张志伟
 * @version v1.0
 */
public class Code08_GetMax {

    public static int getMax(int[] arr){
        return process(arr,0,arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R){
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int leftMax = process(arr,L,mid);
        int rightMax = process(arr,mid,R);
        return Math.max(leftMax,rightMax);
    }

}
