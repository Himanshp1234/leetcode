class Solution {

    public long func(int[] nums, long[][][][] dp, int i, int s, int sg, int p) {
        if (i >= nums.length) {
            if (s == 0) {
                return (long) -1e17;
            }

            return 0;
        }

        if (dp[i][s][sg + 1][p] != Long.MIN_VALUE) {
            return dp[i][s][sg + 1][p];
        }

        long curr = 1L * nums[i] * sg;
        long m = (long) -1e17;

        if (s == 0) {
            long c1 = func(nums, dp, i + 1, s, sg, p);
            long c2 = curr + func(nums, dp, i + 1, 1, -sg, p);

            m = Math.max(c1, c2);
        } else {
            long c1 = curr + func(nums, dp, i + 1, 1, -sg, p);

            m = Math.max(m, 0L);

            if (p == 1) {
                long c2 = func(nums, dp, i + 1, s, sg, 0);
                m = Math.max(m, c2);
            }

            m = Math.max(m ,c1);
        }

        return dp[i][s][sg + 1][p] = m;
    }

    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;

        long[][][][] dp = new long[n][2][3][2];

        for (int i = 0; i < n; i++) {
            for (int s = 0; s < 2; s++) {
                for (int sg = 0; sg < 3; sg++) {
                    for (int p = 0; p < 2; p++) {
                        dp[i][s][sg][p] = Long.MIN_VALUE;
                    }
                }
            }
        }

        return func(nums, dp, 0, 0, 1, 1);
    }
}