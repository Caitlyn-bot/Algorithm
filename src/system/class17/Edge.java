package system.class17;

/**
 * 边结构
 * @author 张志伟
 * @version v1.0
 */
public class Edge {
    /**
     * 边的权重
     */
    public int weight;
    /**
     * 出发结点
     */
    public Node from;
    /**
     * 目标结点
     */
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
