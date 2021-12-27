package novice.class03;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author 张志伟
 * @version v1.0
 */
public class Code05_HashMapTreeMap {

    public static void main(String[] args){
        HashMap<String,String> map = new HashMap<>();
        map.put("zzw","我是张志伟");
        System.out.println(map.containsKey("zzw"));
        System.out.println(map.containsKey("z"));
        System.out.println(map.get("zzw"));
        map.put("zzw","他是张志伟");
        System.out.println(map.get("zzw"));
        map.remove("zzw");
        System.out.println(map.get("zzw"));
        System.out.println(map.containsKey("zzw"));

        TreeMap<Integer,String> treeMap = new TreeMap<>();
        treeMap.put(3,"我是3");
        treeMap.put(0,"我是3");
        treeMap.put(7,"我是3");
        treeMap.put(2,"我是3");
        treeMap.put(5,"我是3");
        treeMap.put(9,"我是3");
        System.out.println(treeMap.containsKey(7));
        System.out.println(treeMap.containsKey(6));
        System.out.println(treeMap.get(3));
        treeMap.put(3,"他是3");
        System.out.println(treeMap.get(3));
        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());
        //<=5离5最近的key
        System.out.println(treeMap.floorKey(5));
        System.out.println(treeMap.floorKey(6));
        //>=5离5最近的key
        System.out.println(treeMap.ceilingKey(5));
        System.out.println(treeMap.ceilingKey(6));
    }

}
