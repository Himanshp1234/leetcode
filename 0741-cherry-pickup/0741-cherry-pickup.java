class Solution {

    public int cherryPickup(int[][] grid) {

        int n = grid.length;

        int[][][] dp = new int[n][n][n];

        // Fill with -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        dp[0][0][0] = grid[0][0];

        for (int r1 = 0; r1 < n; r1++) {

            for (int c1 = 0; c1 < n; c1++) {

                for (int r2 = 0; r2 < n; r2++) {

                    int c2 = r1 + c1 - r2;

                    
                    if (c2 < 0 || c2 >= n) {
                        continue;
                    }

                    
                    if (grid[r1][c1] == -1 || grid[r2][c2] == -1) {
                        continue;
                    }

                         
                    if (r1 == 0 && c1 == 0 && r2 == 0) {
                        continue;
                    }

                    int best = -1;

                    

                    
                    if (r1 > 0 && r2 > 0) {
                        best = Math.max(best,
                                dp[r1 - 1][c1][r2 - 1]);
                    }

                
                    if (r1 > 0 && c2 > 0) {
                        best = Math.max(best,
                                dp[r1 - 1][c1][r2]);
                    }

                    
                    if (c1 > 0 && r2 > 0) {
                        best = Math.max(best,
                                dp[r1][c1 - 1][r2 - 1]);
                    }

                    
                    if (c1 > 0 && c2 > 0) {
                        best = Math.max(best,
                                dp[r1][c1 - 1][r2]);
                    }

                    if (best == -1) {
                        continue;
                    }

                    int cherries = best;

                
                    cherries += grid[r1][c1];

                    
                    if (r1 != r2) {
                        cherries += grid[r2][c2];
                    }

                    dp[r1][c1][r2] = cherries;
                }
            }
        }

        return Math.max(0, dp[n - 1][n - 1][n - 1]);
    }
}