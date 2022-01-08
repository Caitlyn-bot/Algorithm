package system.class15;

/**
 * 给定一个二维数组，1代表两人互相认识，给出该人群有几个朋友圈（相互认识既可以连）
 * @author 张志伟
 * @version v1.0
 */
public class Code01_FriendCircles {

    public static int findCircleNum(int[][] M) {
        int N = M.length;
        //{0}{1}{2}{3}{N-1}
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (M[i][j] == 1) {
                    // i和j互相认识
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets();
    }

    public static class UnionFind{
        /**
         * parent[i] = k ： i的父亲是k
         */
        private int[] parent;
        /**
         * size[i] = k ： 如果i是代表节点，size[i]才有意义，否则无意义
         * i所在的集合大小是多少
         */
        private int[] size;
        /**
         * 辅助结构:栈
         */
        private int[] help;
        /**
         * 一共有多少个集合
         */
        private int sets;

        public UnionFind(int N){
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = i;
            }
        }

        private int find(int i){
            int hi = 0;
            while(i != parent[i]){
                help[hi++] = i;
                i = parent[i];
            }
            //扁平化
            for (hi--; hi >= 0; hi--){
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(int i, int j){
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2){
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }
        }

        public int sets() {
            return sets;
        }
    }


}
