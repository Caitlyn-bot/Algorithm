package coding.class01;

import java.util.Arrays;

/**
 * 给定一个有序数组arr，代表坐落在X轴上的点，给定一个正数K，代表绳子的长度，返回绳子最多压中几个点？
 * 即使绳子边缘处盖住点也算盖住
 * @author 张志伟
 * @version v1.0
 */
public class Code01_CardCoverMaxPoint {
    /**
     * 使用二分解决，复杂度O(N*logN)
     * @param arr
     * @param L
     * @return
     */
    public static int maxPoint1(int[] arr, int L){
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearestIndex(arr,i,arr[i] - L);
            res = Math.max(res,i - nearest + 1);
        }
        return res;
    }

    /**
     * 查找数组中最左的大于value的位置，采用二分
     * @param arr
     * @param R
     * @param value
     * @return
     */
    private static int nearestIndex(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        while (L <= R){
            int mid = L + ((R - L) >> 1);
            if(arr[mid] >= value){
                index = mid;
                R = index - 1;
            }else {
                L = mid + 1;
            }
        }
        return index;
    }

    /**
     * 采用滑动窗口，复杂度O(N)
     * @param arr
     * @param L
     * @return
     */
    public static int maxPoint2(int[] arr, int L){
        int left = 0;
        int right = 0;
        int N = arr.length;
        int max = 0;
        while (left < N){
            if (right < N && arr[right] - arr[left] <= L){
                right++;
            }else {
                left++;
            }
            max = Math.max(max,right - left);
        }
        return max;
    }

    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoint1(arr, L);
            int ans2 = maxPoint2(arr, L);
            int ans3 = test(arr, L);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("oops!");
                break;
            }
        }
        System.out.println("测试结束");

    }

}
