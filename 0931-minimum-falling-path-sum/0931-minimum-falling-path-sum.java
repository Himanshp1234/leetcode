class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];

        
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
        }

        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {

                int top = dp[i - 1][j];

                int leftDiagonal = Integer.MAX_VALUE;
                int rightDiagonal = Integer.MAX_VALUE;

                if (j > 0) {
                    leftDiagonal = dp[i - 1][j - 1];
                }

                if (j < n - 1) {
                    rightDiagonal = dp[i - 1][j + 1];
                }

                dp[i][j] = matrix[i][j]
                        + Math.min(top,
                        Math.min(leftDiagonal, rightDiagonal));
            }
        }

        
        int ans = dp[n - 1][0];

        for (int j = 1; j < n; j++) {
            ans = Math.min(ans, dp[n - 1][j]);
        }

        return ans;
    }
}