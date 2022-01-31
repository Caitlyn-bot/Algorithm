package system.class25;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 * 给定一个非负数组arr，代表直方图，返回直方图的最大长方形面积
 * @author 张志伟
 * @version v1.0
 */
public class Code03_LargestRectangleInHistogram {
    public static int largestRectangleArea1(int[] height) {
        if(height == null || height.length == 0){
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

    public static int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int N = height.length;
        int[] stack = new int[N];
        int si = -1;
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            while (si != -1 && height[i] <= height[stack[si]]) {
                int j = stack[si--];
                int k = si == -1 ? -1 : stack[si];
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack[++si] = i;
        }
        while (si != -1) {
            int j = stack[si--];
            int k = si == -1 ? -1 : stack[si];
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }
}
