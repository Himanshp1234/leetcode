
class Solution {
    static final int MOD = 1000000007;

    public int uniquePaths(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][][] dp = new int[m + 1][n + 1][2];

        // Destination
        dp[m - 1][n - 1][0] = 1;
        dp[m - 1][n - 1][1] = 1;

        for (int i = m - 1; i >= 0; i--) {

            for (int j = n - 1; j >= 0; j--) {

                if (i == m - 1 && j == n - 1) {
                    continue;
                }

                if (grid[i][j] == 1) {

                   
                    dp[i][j][0] = dp[i + 1][j][1];

                    
                    dp[i][j][1] = dp[i][j + 1][0];

                } else {

                    int ans = (
                        dp[i][j + 1][0] +
                        dp[i + 1][j][1]
                    ) % MOD;

                    dp[i][j][0] = ans;
                    dp[i][j][1] = ans;
                }
            }
        }

        
        return dp[0][0][0];
    }
}
