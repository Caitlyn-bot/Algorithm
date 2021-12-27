package novice.class01;

/**
 * @author 张志伟
 * @version v1.0
 */
public class Code01_PrintB {
    public static void main(String[] args){
        //4个字节,32个二进制位
        int num = 83928328;
        print(num);
        print(1^3);
        int min = Integer.MIN_VALUE;
        print(min);
        //带符号右移，用符号位来补
        print(min >> 1);
        //不带符号右移，用0来补
        print(min >>> 1);

        int c = 5;
        //求相反数，取反+1
        int d = (~c + 1);
        System.out.println(d);


    }
    public static void print(int num){
        for (int i = 31; i >= 0 ; i--) {
            System.out.print(  (num & (1<<i)) == 0 ? "0" : "1"  );
        }
        System.out.println();
    }
}
