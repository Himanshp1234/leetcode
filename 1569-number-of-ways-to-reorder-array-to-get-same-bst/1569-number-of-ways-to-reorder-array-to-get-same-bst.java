import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;
    long[][] dp;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        dp = new long[n + 1][n + 1];

        for (long[] row : dp) {
            Arrays.fill(row, -1);
        }

        List<Integer> arr = new ArrayList<>();

        for (int num : nums) {
            arr.add(num);
        }

        return (int) ((solve(arr) - 1 + MOD) % MOD);
    }

    private long solve(List<Integer> nums) {
        int n = nums.size();

        if (n <= 2) {
            return 1;
        }

        int root = nums.get(0);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }

        long leftWays = solve(left);
        long rightWays = solve(right);

        long combinations = nCr(n - 1, left.size());

        return (((combinations * leftWays) % MOD)
                * rightWays) % MOD;
    }

    private long nCr(int n, int r) {
        if (r == 0 || r == n) {
            return 1;
        }

        if (dp[n][r] != -1) {
            return dp[n][r];
        }

        dp[n][r] = (nCr(n - 1, r - 1)
                  + nCr(n - 1, r)) % MOD;

        return dp[n][r];
    }
}