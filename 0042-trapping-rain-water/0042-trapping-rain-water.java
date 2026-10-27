
class Solution {
    public int trap(int[] height) {
        int n = height.length;

        if (n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        leftMax[0] = height[0];

        
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int rightMax = 0;
        int water = 0;

        
        for (int i = n - 1; i > 0; i--) {
            rightMax = Math.max(rightMax, height[i]);

            int waterLevel = Math.min(leftMax[i], rightMax);
            water += waterLevel - height[i];
        }

        return water;
    }
}
