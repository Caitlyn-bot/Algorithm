package system.class16;


import java.util.ArrayList;

/**
 * 点结构
 * @author 张志伟
 * @version v1.0
 */
public class Node {
    /**
     * 值
     */
    public int value;
    /**
     * 入度：指向该结点的边数
     */
    public int in;
    /**
     * 出度：该结点指向其他结点的边数
     */
    public int out;
    /**
     * 该结点的邻居结点：从该结点出发能找到的结点
     */
    public ArrayList<Node> nexts;
    /**
     * 该结点相关的边：从该结点出发的边
     */
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }

}
