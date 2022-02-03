package system.class28;

/**
 * 给定一个字符串str，只能在str的后面添加字符，想让str整体变成回文串，返回至少要添加几个字符
 * 求出添加字符后的最长字串
 * 即是求包含最后一位的回文串
 * 求出最后一位的
 * @author 张志伟
 * @version v1.0
 */
public class Code02_AddShortestEnd {
    public static String shortestEnd(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int C = -1;
        int R = -1;
        int maxContainsEnd = -1;
        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1){
                if (str[i + pArr[i]] == str[i - pArr[i]]){
                    pArr[i]++;
                }else {
                    break;
                }
            }
            //更新R和C
            if (i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }
            if (R == str.length){
                //当包含最后一位时，即可结束循环
                maxContainsEnd = pArr[i];
                break;
            }
        }
        //求出添加字符后的最长字串
        char[] res = new char[s.length() - maxContainsEnd + 1];
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = str[i * 2 + 1];
        }
        return String.valueOf(res);
    }

    private static char[] manacherString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append("#");
            sb.append(s.charAt(i));
        }
        sb.append("#");
        return sb.toString().toCharArray();
    }

    public static void main(String[] args) {
        String str1 = "abcd123321";
        System.out.println(shortestEnd(str1));
    }
}
