class Solution {

    int m, n;
    Boolean[][][] dp;

    public boolean hasValidPath(char[][] grid) {

        m = grid.length;
        n = grid[0].length;

        
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        dp = new Boolean[m][n][m + n];

        return solve(grid, 0, 0, 0);
    }

    boolean solve(char[][] grid, int r, int c, int balance) {

        
        if (balance < 0) {
            return false;
        }

    
        int remaining = (m - r) + (n - c) - 1;

        
        if (balance > remaining) {
            return false;
        }

        
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        
        if (balance < 0) {
            return false;
        }

        
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        
        if (dp[r][c][balance] != null) {
            return dp[r][c][balance];
        }

        boolean ans = false;

        
        if (r + 1 < m) {
            ans = solve(grid, r + 1, c, balance);
        }

        
        if (!ans && c + 1 < n) {
            ans = solve(grid, r, c + 1, balance);
        }

        return dp[r][c][balance] = ans;
    }
}