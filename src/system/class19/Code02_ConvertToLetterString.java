package system.class19;

/**
 * 规定1和A对应，2和B对应，3和C对应...26和Z对应
 * 那么一个数字字符串“111”就可以转化为：
 * “AAA”、“KA”和“AK”
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 * @author 张志伟
 * @version v1.0
 */
public class Code02_ConvertToLetterString {
    public static int number(String str){
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    /**
     * 只需要考虑从index到str.length的转化
     * @param str
     * @param index
     * @return
     */
    public static int process(char[] str,int index){
        if (index == str.length){
            //当index来到该位置时，无须对字符串进行转化，得到一种可能就是之前处理的结果
            return 1;
        }
        if (str[index] == '0'){
            //因为单独的0没有对应，只有10,把1和0
            return 0;
        }
        // str[i] != '0'
        // 可能性一，i单转
        int ways = process(str, index + 1);
        //能双转，则添加双转的可能次数
        if (index + 1 < str.length && (str[index] - '0') * 10 + str[index + 1] - '0' < 27) {
            ways += process(str, index + 2);
        }
        return ways;
    }

    /**
     * 从右向左的动态规划
     * @param s
     * @return
     */
    public static int dp(String s){
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = s.length();
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int index = N - 1; index >= 0; index--) {
            if (str[index] != '0'){
                int ways = dp[index + 1];
                if (index + 1 < str.length && (str[index] - '0') * 10 + str[index + 1] - '0' < 27) {
                    ways += dp[index + 2];
                }
                dp[index] = ways;
            }
        }
        return dp[0];
    }

    /**
     * 从左向右的动态规划
     * @param s
     * @return
     */
    public static int dp2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        if (str[0] == '0') {
            return 0;
        }
        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            if (str[i] == '0') {
                // 如果此时str[i]=='0'，那么他是一定要拉前一个字符(i-1的字符)一起拼的，
                // 那么就要求前一个字符，不能也是‘0’，否则拼不了。
                // 前一个字符不是‘0’就够了嘛？不够，还得要求拼完了要么是10，要么是20，如果更大的话，拼不了。
                // 这就够了嘛？还不够，你们拼完了，还得要求str[0...i-2]真的可以被分解！
                // 如果str[0...i-2]都不存在分解方案，那i和i-1拼成了也不行，因为之前的搞定不了。
                if (str[i - 1] == '0' || str[i - 1] > '2' || (i - 2 >= 0 && dp[i - 2] == 0)) {
                    return 0;
                } else {
                    dp[i] = i - 2 >= 0 ? dp[i - 2] : 1;
                }
            } else {
                dp[i] = dp[i - 1];
                if (str[i - 1] != '0' && (str[i - 1] - '0') * 10 + str[i] - '0' <= 26) {
                    dp[i] += i - 2 >= 0 ? dp[i - 2] : 1;
                }
            }
        }
        return dp[N - 1];
    }

    // 为了测试
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = number(s);
            int ans1 = dp(s);
            int ans2 = dp2(s);
            if (ans0 != ans1 || ans0 != ans2) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
