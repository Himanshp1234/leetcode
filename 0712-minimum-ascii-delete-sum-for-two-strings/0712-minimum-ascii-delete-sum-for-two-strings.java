class Solution {

    public int func(int i, int j, String s1, String s2, int[][] dp){
        int n = s1.length();
        int m = s2.length();

        if (i >= n) {
            int sum = 0;

            for (int k = j; k < m; k++) {
                sum += s2.charAt(k);
            }

            return sum;
        }

        if (j >= m) {
            int sum = 0;

            for (int k = i; k < n; k++) {
                sum += s1.charAt(k);
            }

            return sum;
        }

        if (dp[i][j] != -1) return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            dp[i][j] = func(i + 1, j + 1, s1, s2, dp);
        } else {
            int c1 = s1.charAt(i) + func(i + 1, j , s1, s2, dp);
            int c2 = s2.charAt(j) + func(i, j + 1, s1, s2, dp);

            dp[i][j] = Math.min(c1, c2);
        }

        return dp[i][j];
    }

    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length()][s2.length()];

        for (int[] e : dp) {
            Arrays.fill(e, -1);
        }

        return func(0, 0, s1, s2, dp);
    }
}