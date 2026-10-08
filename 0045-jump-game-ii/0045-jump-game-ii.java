class Solution {
    public int jump(int[] nums) {
        
        int jumps = 0;
        int currentEnd = 0;
        int far = 0;

        for(int i = 0; i < nums.length - 1; i++) {

            far = Math.max(far,i + nums[i]);

            if(i == currentEnd) {
                jumps++;
                currentEnd = far;
            }
        }
        return jumps;
    }
        
    }

