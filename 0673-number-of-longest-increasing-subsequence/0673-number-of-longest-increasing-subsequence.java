class Solution {
    public int findNumberOfLIS(int[] nums) {

        int n = nums.length;

        int[] dp = new int[n];
        int[] count = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
        }

        int maxLen = 1;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (nums[j] < nums[i]) {

                    if (dp[j] + 1 > dp[i]) {

                        dp[i] = dp[j] + 1;
                        count[i] = count[j];

                    } else if (dp[j] + 1 == dp[i]) {

                        count[i] += count[j];
                    }
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {

            if (dp[i] == maxLen) {
                ans += count[i];
            }
        }

        return ans;
    }
}