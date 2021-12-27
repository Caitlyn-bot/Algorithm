package novice.class02;

/**
 * 利用01发生器获取任意范围内的等概率数据
 * @author 张志伟
 * @version v1.0
 */
public class Code02_RandToRand {


    public static void main(String[] args) {
        System.out.println("测试开始");
        //Math.random() -> double -> [0,1)

        int testTimes = 10000000;
        int count = 0;
        for (int i = 0; i < testTimes; i++){
            if (Math.random() < 0.3){
                count ++;
            }
        }
        System.out.println((double)count/(double)testTimes);
        System.out.println("=============================");
        count = 0;
        //[0,1)->[0,8)
        for (int i = 0; i < testTimes; i++){
            if (Math.random() * 8 < 4){
                count ++;
            }
        }
        System.out.println((double)count/(double)testTimes);

        int K = 9;
        //[0,K)->[0,K-1]

        //counts[0]表示0出现的次数
        int[] counts = new int[9];
        for (int i = 0; i < testTimes; i++){
            int ans = (int)(Math.random() * K);
            counts[ans]++;
        }
        for (int i = 0; i < K; i++) {
            System.out.println(i + "这个数出现了" +counts[i] + "次");
        }
        System.out.println("=============================");
        count = 0;
        double x = 0.7;
        for (int i = 0; i < testTimes; i++){
            if (xToXPower2() < x){
                count++;
            }
        }
        System.out.println((double)count/(double)testTimes);
        System.out.println(Math.pow(x,2));
        System.out.println("=============================");
        count = 0;
        for (int i = 0; i < testTimes; i++){
            if (f2() == 0){
                count++;
            }
        }
        System.out.println((double)count/(double)testTimes);
        counts = new int[8];
        for (int i = 0; i < testTimes; i++){
            int num = f4();
            counts[num]++;
        }
        for (int i = 0; i < 8; i++) {
            System.out.println(i + "这个数出现了" +counts[i] + "次");
        }
    }

    /**
     * 返回[0,1)上的任意一个小数
     * 任意的x，x属于[0,1),[0,x)范围上的数出现的概率由原本的x调整成x方
     * @return
     */
    public static double xToXPower2(){
        return Math.max(Math.random(),Math.random());
    }

    /**
     * 黑盒函数，可等概率返回1-5
     * @return
     */
    public static int f1(){
        return (int)(Math.random() * 5) + 1;
    }

    /**
     * 随机机制只能用f1，等概率返回0和1
     * @return
     */
    public static int f2(){
        int ans = 0;
        do {
            ans = f1();
        }while (ans == 3);
        return ans < 3 ? 0 : 1;
    }

    /**
     * 要得到某一范围的数，可将其转为二进制进行随机
     * 得到几位就是几位的01随机
     * 0-7等概率返回
     * @return
     */
    public static int f3(){
        int ans = (f2() << 2) + (f2() << 1) + (f2() << 0);
        return ans;
    }

    /**
     * 1-7等概率返回
     * @return
     */
    public static int f4(){
        int ans = 0;
        do {
            ans = f3();
        }while (ans == 0);
        return ans;
    }

    /**
     * 该发生器会以固定概率产生零一，但不等概率，如何得到等概率的零一发生器
     * @return
     */
    public static int x(){
        return Math.random() < 0.84 ? 0 : 1;
    }

    public static int y(){
        int ans = 0;
        //当第一次等于第二次时重新开始
        //ans只会在产生01和产生10时分别返回0和1，该事件是等概率的
        do {
            ans = x();
        }while (ans == x());
        return ans;
    }

}
