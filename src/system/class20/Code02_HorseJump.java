package system.class20;

/**
 * 请同学们自行搜索或者想象一个象棋的棋盘，
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个 参数 x，y，k
 * 返回“马”从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 * @author 张志伟
 * @version v1.0
 */
public class Code02_HorseJump {
    public static int jump(int a, int b, int k) {
        return process(0, 0, k, a, b);
    }

    /**
     * 来到当前位置(x,y)，还剩下rest步，要跳到(a,b)的方法有多少种
     * @param x
     * @param y
     * @param rest
     * @param a
     * @param b
     * @return
     */
    public static int process(int x, int y, int rest, int a, int b) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            //防止越界
            return 0;
        }
        if (rest == 0){
            return (x == a && y == b) ? 1 : 0;
        }
        //马在棋盘中有八种跳法
        int ways = process(x + 2, y + 1, rest - 1, a, b);
        ways += process(x + 1, y + 2, rest - 1, a, b);
        ways += process(x - 1, y + 2, rest - 1, a, b);
        ways += process(x - 2, y + 1, rest - 1, a, b);
        ways += process(x - 2, y - 1, rest - 1, a, b);
        ways += process(x - 1, y - 2, rest - 1, a, b);
        ways += process(x + 1, y - 2, rest - 1, a, b);
        ways += process(x + 2, y - 1, rest - 1, a, b);
        return ways;
    }

    public static int dp(int a, int b, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[a][b][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    int ways = pick(dp, x + 2, y + 1, rest - 1);
                    ways += pick(dp, x + 1, y + 2, rest - 1);
                    ways += pick(dp, x - 1, y + 2, rest - 1);
                    ways += pick(dp, x - 2, y + 1, rest - 1);
                    ways += pick(dp, x - 2, y - 1, rest - 1);
                    ways += pick(dp, x - 1, y - 2, rest - 1);
                    ways += pick(dp, x + 1, y - 2, rest - 1);
                    ways += pick(dp, x + 2, y - 1, rest - 1);
                    dp[x][y][rest] = ways;
                }
            }
        }
        return dp[0][0][k];
    }
    public static int pick(int[][][] dp, int x, int y, int rest) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][rest];
    }

    public static int ways(int a, int b, int step) {
        return f(0, 0, step, a, b);
    }

    public static int f(int i, int j, int step, int a, int b) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        if (step == 0) {
            return (i == a && j == b) ? 1 : 0;
        }
        return f(i - 2, j + 1, step - 1, a, b) + f(i - 1, j + 2, step - 1, a, b) + f(i + 1, j + 2, step - 1, a, b)
                + f(i + 2, j + 1, step - 1, a, b) + f(i + 2, j - 1, step - 1, a, b) + f(i + 1, j - 2, step - 1, a, b)
                + f(i - 1, j - 2, step - 1, a, b) + f(i - 2, j - 1, step - 1, a, b);

    }

    public static int waysdp(int a, int b, int s) {
        int[][][] dp = new int[10][9][s + 1];
        dp[a][b][0] = 1;
        for (int step = 1; step <= s; step++) { // 按层来
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    dp[i][j][step] = getValue(dp, i - 2, j + 1, step - 1) + getValue(dp, i - 1, j + 2, step - 1)
                            + getValue(dp, i + 1, j + 2, step - 1) + getValue(dp, i + 2, j + 1, step - 1)
                            + getValue(dp, i + 2, j - 1, step - 1) + getValue(dp, i + 1, j - 2, step - 1)
                            + getValue(dp, i - 1, j - 2, step - 1) + getValue(dp, i - 2, j - 1, step - 1);
                }
            }
        }
        return dp[0][0][s];
    }

    // 在dp表中，得到dp[i][j][step]的值，但如果(i，j)位置越界的话，返回0；
    public static int getValue(int[][][] dp, int i, int j, int step) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        return dp[i][j][step];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(ways(x, y, step));
        System.out.println(dp(x, y, step));

        System.out.println(jump(x, y, step));
    }
}
