package system.class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 宽度优先遍历
 * @author 张志伟
 * @version v1.0
 */
public class Code01_BFS {
    /**
     * 从指定结点出发，进行宽度优先遍历
     * @param start
     */
    public static void bfs(Node start){
        if (start == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        //set能够防止结点重复遍历
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)){
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }

}
