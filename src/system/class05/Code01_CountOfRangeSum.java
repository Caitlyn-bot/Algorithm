package system.class05;

/**
 * https://leetcode-cn.com/problems/count-of-range-sum/
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。
 * 求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * @author 张志伟
 * @version v1.0
 */
public class Code01_CountOfRangeSum {
    public static int countRangeSum(int[] nums,int lower,int upper){
        if (nums == null || nums.length == 0){
            return 0;
        }
        //sum为nums数组的前缀和数组
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1;i < nums.length;i++){
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum,0,sum.length - 1,lower,upper);
    }

    public static int process(long[] sum,int L,int R,int lower,int upper){
        if (L == R){
            return sum[L] >= lower && sum[R] <= upper ? 1 : 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(sum,L,mid,lower,upper) + process(sum, mid + 1, R, lower, upper)
                + merge(sum,L,mid,R,lower,upper);
    }

    public static int merge(long[] arr,int L,int M,int R,int lower,int upper){
        int ans = 0;
        //windowL和windowR分别代表符合范围的左边界和右边界
        int windowL = L;
        int windowR = L;
        //[windowL,windowR)
        //从右半区逐个查看是否左半区有符合条件的
        for (int i = M + 1; i <= R ; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            //min和max是判断左半区是否符合标准的边界
            //注意：左半区是有序的，所以只需要判断一边即可
            while (windowR <= M && arr[windowR] <= max){
                windowR++;
            }
            while (windowL <= M && arr[windowL] < min){
                windowL++;
            }
            //这个范围有多大，就有几个数符合
            ans += windowR - windowL;
        }
        //传统递归的排序
        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M){
            help[i++] = arr[p1++];
        }
        while (p2 <= R){
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

}
