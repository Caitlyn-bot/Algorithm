package system.class27;

/**
 * 判断str1和str2是否互为旋转字符串
 * 拼接同一个，看另一个是否是拼接后的字串
 * @author 张志伟
 * @version v1.0
 */
public class Code03_IsRotation {
    public static boolean isRotation(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }
        String b2 = b + b;
        return getIndexOf(b2, a) != -1;
    }

    /**
     * 判断m是否在s中
     * @param s
     * @param m
     * @return
     */
    private static int getIndexOf(String s, String m) {
        if (s.length() < m.length()){
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);

        return 0;
    }

    private static int[] getNextArray(char[] ms) {
        if (ms.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int p = 2;
        int cn = 0;
        while (p < ms.length){
            if (ms[p - 1] == ms[cn]){
                next[p++] = ++cn;
            }else if (cn > 0){
                //如果不相等，且cn>0，说明上一个相等
                cn = next[cn];
            }else {
                next[p++] = 0;
            }
        }
        return next;
    }
    public static void main(String[] args) {
        String str1 = "yunzuocheng";
        String str2 = "zuochengyun";
        System.out.println(isRotation(str1, str2));

    }
}
