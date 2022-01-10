package system.class17;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图结点
 * @author 张志伟
 * @version v1.0
 */
public class Graph {
    /**
     * 结点
     */
    public HashMap<Integer, Node> nodes;
    /**
     * 边
     */
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
