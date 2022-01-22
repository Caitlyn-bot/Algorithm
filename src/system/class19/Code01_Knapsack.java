package system.class19;

/**
 * 动态规划求解背包问题
 * @author 张志伟
 * @version v1.0
 */
public class Code01_Knapsack {
    /**
     *
     * @param w 货物重量
     * @param v 对应价值
     * @param bag 背包最大容量
     * @return 不超重的情况下，能够得到的最大价值
     */
    public static int maxValue(int[] w,int[] v, int bag){
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        return process(w, v, 0, bag);
    }

    /**
     * 当前考虑到了index号货物，后续可以自由选择
     * 这里使用到了判断无效解的小技巧
     * 返回最大价值
     * @param w
     * @param v
     * @param index 当前位置
     * @param rest 背包剩余容量
     * @return
     */
    public static int process(int[] w, int[] v, int index, int rest){
        if (rest < 0){
            //剩余容量不足
            //帮助判断是否为无效解
            return -1;
        }
        if (index == w.length){
            //所有的货物都已经选择完毕
            return 0;
        }
        //不要当前货物
        int p1 = process(w, v, index + 1, rest);
        //要当前货物，需判断当前选择是否有效
        int p2 = 0;
        int next =  process(w, v, index + 1, rest - w[index]);
        if (next != -1){
            //说明当前选择有效
            p2 = v[index] + next;
        }
        return Math.max(p1,p2);
    }

    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        //因为当index = N 时，值全部为0，无需再进行赋值
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                //不要当前位置的value
                int p1 = dp[index + 1][rest];
                //要当前的value，校验是否有效
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1){
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }

}
