class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        Arrays.fill(dp,1000000);

        dp[n - 1] = 0;

        for(int i = n - 2; i >= 0; i--) {

            for(int j = 1; j <= nums[i]; j++) {
                int next = i + j;

                if(next < n) {
                    dp[i] =  Math.min(dp[i],1 + dp[next]);

                
                }
            }
        }
        return dp[0];
        
        
    }
        
    }

