
class Solution {
    static final int MOD = 1000000007;

    int[][][] dp;

    int solve(int i, int j, int dir, int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        if (i >= m || j >= n) {
            return 0;
        }

        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        if (dp[i][j][dir] != -1) {
            return dp[i][j][dir];
        }

        int ans = 0;

        if (grid[i][j] == 1) {

            if (dir == 0) {
                ans = solve(i + 1, j, 1, grid);
            } else {
                ans = solve(i, j + 1, 0, grid);
            }

        } else {

            int right = solve(i, j + 1, 0, grid);
            int down = solve(i + 1, j, 1, grid);

            ans = (right + down) % MOD;
        }

        return dp[i][j][dir] = ans;
    }

    public int uniquePaths(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        dp = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
            }
        }

        return solve(0, 0, 0, grid);
    }
}
