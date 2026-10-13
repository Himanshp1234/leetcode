class Solution {
    public int uniquePathsIII(int[][] grid) {
        int sr = 0, sc = 0, empty = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    sr = i;
                    sc = j;
                }
                if (grid[i][j] == 0) {
                    empty++;
                }
            }
        }

        return solve(grid, sr, sc, empty + 1);
    }

    private int solve(int[][] grid, int r, int c, int remaining) {
        if (r < 0 || c < 0 || r >= grid.length ||
            c >= grid[0].length || grid[r][c] == -1) {
            return 0;
        }

        if (grid[r][c] == 2) {
            return remaining == 0 ? 1 : 0;
        }

        int temp = grid[r][c];
        grid[r][c] = -1;

        int paths =
            solve(grid, r + 1, c, remaining - 1) +
            solve(grid, r - 1, c, remaining - 1) +
            solve(grid, r, c + 1, remaining - 1) +
            solve(grid, r, c - 1, remaining - 1);

        grid[r][c] = temp;

        return paths;
    }
}