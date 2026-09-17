class Solution {
    public int longestCommonSubsequence(String text1, String text2) {

        int[][] dp = new int[text1.length()][text2.length()];

        for (int[] e : dp) {
            Arrays.fill(e, -1);
        }

        return helper(0, 0, text1, text2, dp);
    }

    public int helper(int i, int j, String s, String t, int[][] dp) {

        if (i >= s.length() || j >= t.length()) {
            return 0;
        }

        if (dp[i][j] != -1)
            return dp[i][j];

        if (s.charAt(i) == t.charAt(j)) {
            dp[i][j] = 1 + helper(i + 1, j + 1, s, t, dp);
        } else {
            int c1 = helper(i + 1, j, s, t, dp);
            int c2 = helper(i, j + 1, s, t, dp);
            dp[i][j] = Math.max(c1, c2);
        }

        return dp[i][j];
    }
}