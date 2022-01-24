package system.class21;

import java.util.HashMap;
import java.util.Map.Entry;


/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 * @author 张志伟
 * @version v1.0
 */
public class Code04_CoinsWaySameValueSamePapper {
    public static class Info{
        public int[] coins;
        public int[] nums;

        public Info(int[] c, int[] n){
            coins = c;
            nums = n;
        }
    }

    public static Info getInfo(int[] arr){
        HashMap<Integer,Integer> counts = new HashMap<>();
        for (int value : arr) {
            if(!counts.containsKey(value)){
                counts.put(value, 1);
            }else {
                counts.put(value,counts.get(value) + 1);
            }
        }
        int N = counts.size();
        int[] coins = new int[N];
        int[] zhangs = new int[N];
        int index = 0;
        for (Entry<Integer, Integer> entry : counts.entrySet()) {
            coins[index] = entry.getKey();
            zhangs[index++] = entry.getValue();
        }
        return new Info(coins, zhangs);
    }

    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        return process(info.coins, info.nums, 0, aim);
    }

    /**
     *
     * @param coins 面值数组，正数且去重
     * @param nums 每种面值对应的张数
     * @param index
     * @param rest
     * @return
     */
    private static int process(int[] coins, int[] nums, int index, int rest) {
        if (index == coins.length){
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int num = 0; num * coins[index] <= rest && num <= nums[index]; num++) {
            ways += process(coins, nums, index + 1, rest - num * coins[index]);
        }
        return ways;
    }

    public static int dp1(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] nums = info.nums;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int num = 0; num * coins[index] <= rest && num <= nums[index]; num++) {
                    ways += dp[index + 1][rest - (num * coins[index])];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }
    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] nums = info.nums;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                //利用旁边的替代，就要先加上自己独有的，在减去旁边的独有的
                dp[index][rest] = dp[index + 1][rest];
                if (rest - coins[index] >= 0) {
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
                if (rest - coins[index] * (nums[index] + 1) >= 0) {
                    dp[index][rest] -= dp[index + 1][rest - coins[index] * (nums[index] + 1)];
                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
