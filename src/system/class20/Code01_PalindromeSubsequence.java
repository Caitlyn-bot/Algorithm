package system.class20;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 注意：子序列可以是不连续的，字串是连续的
 * @author 张志伟
 * @version v1.0
 */
public class Code01_PalindromeSubsequence {

    public static int longestPalindromeSubseq1(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        char[] str = s.toCharArray();
        return f(str, 0, str.length - 1);
    }

    /**
     * 在L到R范围上的最长回文子序列
     * @param str
     * @param L
     * @param R
     * @return
     */
    public static int f(char[] str, int L, int R){
        if (L == R){
            return 1;
        }
        if (L == R - 1){
            return str[L] == str[R] ? 2 : 1;
        }
        //最长既不以L开头，也不以R结尾
        int p1 = f(str, L + 1, R - 1);
        //最长以L开头，不以R结尾
        int p2 = f(str, L , R - 1);
        //最长不以L开头，以R结尾
        int p3 = f(str, L + 1, R );
        //以L开头，以R结尾
        int p4 = str[L] != str[R] ? 0 : (2 + f(str, L + 1, R - 1));

        return Math.max(Math.max(p1,p2),Math.max(p3,p4));
    }

    public static int dp(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }

        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                dp[L][R] = Math.max(dp[L][R - 1], dp[L + 1][R]);
                if (str[L] == str[R]) {
                    dp[L][R] = Math.max(dp[L][R], 2 + dp[L + 1][R - 1]);
                }
            }
        }

        return dp[0][N - 1];
    }

}
