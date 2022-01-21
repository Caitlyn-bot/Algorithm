package system.class18;

/**
 * 机器人走路：从暴力递归到动态规划的第一步
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 * @author 张志伟
 * @version v1.0
 */
public class Code01_RobotWalk {
    /**
     * 初代暴力递归
     * @param N 总位置数
     * @param start 初始位置
     * @param aim 目标位置
     * @param K 限制要走的步数
     * @return
     */
    public static int ways1(int N, int start, int aim, int K){
        //排除无效参数
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        return process1(start, K, aim, N);
    }

    /**
     * 递归实现
     * @param cur 当前位置
     * @param rest 剩余要走的步数
     * @param aim 目标位置
     * @param N 总位置数
     * @return
     */
    public static int process1(int cur, int rest, int aim, int N){
        if (rest == 0){
            return cur == aim ? 1 : 0;
        }
        if (cur == 1){
            return process1(cur + 1, rest - 1, aim, N);
        }
        if (cur == N){
            return process1(cur - 1, rest - 1, aim, N);
        }
        return process1(cur - 1,rest - 1, aim, N) + process1(cur + 1, rest - 1, aim, N);
    }

    /**
     * 二代傻缓存
     * 从顶向下的动态规划
     * 也叫记忆化搜索
     * 二维的动态规划就是会撞墙的杨辉三角
     * @param N 总位置数
     * @param start 初始位置
     * @param aim 目标位置
     * @param K 限制要走的步数
     * @return
     */
    public static int ways2(int N, int start, int aim, int K){
        //排除无效参数
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        //按照关键要素的范围创建dp数组，储存结果，避免重复计算
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
        //如等于-1说明该位置未计算，反之说明该位置已经计算过，可直接提取使用
        return process2(start, K, aim, N, dp);
    }

    /**
     * 递归过程中的关键参数为cur和rest
     * cur的范围为1-N
     * rest的范围为0-k
     * @param cur
     * @param rest
     * @param aim
     * @param N
     * @param dp 缓存表
     * @return
     */
    public static int process2(int cur, int rest, int aim, int N, int[][] dp){
        if (dp[cur][rest] != -1){
            //如果计算过，直接返回
            return dp[cur][rest];
        }
        //如果没算过，开始算
        int ans = 0;
        if (rest == 0){
            ans = cur == aim ? 1 : 0;
        }else if (cur == 1){
            ans = process2(cur + 1, rest - 1, aim, N, dp);
        }else if (cur == N){
            ans = process2(cur - 1, rest - 1, aim, N, dp);
        }else {
            ans = process2(cur - 1,rest - 1, aim, N, dp) + process2(cur + 1, rest - 1, aim, N, dp);
        }
        //返回之前，记录缓存
        dp[cur][rest] = ans;
        return ans;
    }

    /**
     * 动态规划实现
     * @param N 总位置数
     * @param start 初始位置
     * @param aim 目标位置
     * @param K 限制要走的步数
     * @return
     */
    public static int ways3(int N, int start, int aim, int K){
        //排除无效参数
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        //按照关键要素的范围创建dp数组，储存结果，避免重复计算
        // 该题的dp就是会撞墙的杨辉三角,
        int[][] dp = new int[N + 1][K + 1];
        //首先只需要依赖baseCase写出最初的一列
        dp[aim][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        //如等于-1说明该位置未计算，反之说明该位置已经计算过，可直接提取使用
        return dp[start][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }

}
