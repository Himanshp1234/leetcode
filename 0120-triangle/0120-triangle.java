class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        Integer[][] dp = new Integer[n][n];

        return solve(0, 0, triangle, dp);
    }

    private int solve(int row, int col,
                      List<List<Integer>> triangle,
                      Integer[][] dp) {

        int n = triangle.size();

        
        if (row == n - 1) {
            return triangle.get(row).get(col);
        }

    
        if (dp[row][col] != null) {
            return dp[row][col];
        }

        int down = solve(row + 1, col, triangle, dp);
        int diagonal = solve(row + 1, col + 1, triangle, dp);

        dp[row][col] = triangle.get(row).get(col)
                     + Math.min(down, diagonal);

        return dp[row][col];
    }
}