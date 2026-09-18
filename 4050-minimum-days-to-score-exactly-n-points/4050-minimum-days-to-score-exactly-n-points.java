class Solution {
    public int minDays(int n) {

        int INF = (int) 1e9;
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = INF;
        }

        for (int score = 1; score <= n; score++) {

            for (int k = 1; ; k++) {

                int points = k * (k + 1) / 2;

                if (points > score) {
                    break;
                }

                if (points == score) {
                    dp[score] = Math.min(dp[score], k);
                } else {
                    dp[score] = Math.min(
                            dp[score],
                            dp[score - points] + k + 1
                    );
                }
            }
        }

        return dp[n];
    }
}