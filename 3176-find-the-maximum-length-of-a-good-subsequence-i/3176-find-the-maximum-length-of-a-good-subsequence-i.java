class Solution {

    int[][][] dp;
    int[] nums;
    int k;
    int n;

    public int maximumLength(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        this.n = nums.length;

        dp = new int[n][n + 1][k + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return helper(0, n, 0);
    }

    private int helper(int idx, int prev, int changes) {

        if (idx == n) {
            return 0;
        }

        if (dp[idx][prev][changes] != -1) {
            return dp[idx][prev][changes];
        }

    
        int notTake = helper(idx + 1, prev, changes);

        

        
        int take = 0;

        if (prev == n) {
            take = 1 + helper(idx + 1, idx, changes);
        } else {

            if (nums[idx] == nums[prev]) {
                take = 1 + helper(idx + 1, idx, changes);
            } else if (changes < k) {
                take = 1 + helper(idx + 1, idx, changes + 1);
            }
        }

        return dp[idx][prev][changes] = Math.max(take, notTake);
    }
}