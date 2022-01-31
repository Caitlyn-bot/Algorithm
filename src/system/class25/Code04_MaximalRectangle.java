package system.class25;

import java.util.Stack;

/**
 * 给定一个二维数组matrix，其中的值不是0就是1，返回全部由1组成的最大子矩形内部有多少个1（面积）
 * https://leetcode-cn.com/problems/maximal-rectangle/
 * @author 张志伟
 * @version v1.0
 */
public class Code04_MaximalRectangle {
    public static int maximalRectangle(char[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0){
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == '0' ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }

    private static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0){
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]){
                int index = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                maxArea = Math.max(maxArea, (i - left - 1) * height[index]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int index = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            maxArea = Math.max(maxArea, (height.length - left - 1) * height[index]);
        }
        return maxArea;
    }
}
