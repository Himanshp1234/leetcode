class Solution {
    int m;
    int n;
    int[][] dp;
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        m = obstacleGrid.length;
        n = obstacleGrid[0].length;

        dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            java.util.Arrays.fill(dp[i],-1);
        }

        return solve(0,0,obstacleGrid);
    }

    int solve(int i,int j,int[][] grid) {

        if(i >= m || j >= n) {
            return 0;
        }

        if(grid[i][j] == 1) {
            return 0;
        }

        if(i == m - 1 && j == n - 1) {
            return 1;
        }

        if(dp[i][j] != -1) {
            return dp[i][j];

        }
        int down = solve(i + 1,j,grid);
        int right = solve(i , j + 1,grid);

        dp[i][j] = down + right;

        return dp[i][j];
    }

        
    }
