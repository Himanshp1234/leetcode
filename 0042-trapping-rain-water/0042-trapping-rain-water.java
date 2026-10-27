
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int water = 0;

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty()
                    && height[i] > height[stack.peek()]) {

                int bottom = stack.pop();

                if (stack.isEmpty()) {
                    break;
                }

                int left = stack.peek();
                int width = i - left - 1;

                int boundedHeight =
                    Math.min(height[left], height[i])
                    - height[bottom];

                water += width * boundedHeight;
            }

            stack.push(i);
        }

        return water;
    }
}
