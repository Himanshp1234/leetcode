class Solution {

    boolean check(String s1, String s2) {

        if (s1.length() + 1 != s2.length()) {
            return false;
        }

        int i = 0;
        int j = 0;

        while (j < s2.length()) {

            if (i < s1.length() && s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }

        return i == s1.length();
    }

    int solve(int ind, String[] words, int[] dp) {

        if (dp[ind] != -1) {
            return dp[ind];
        }

        int ans = 1;

        for (int prev = 0; prev < ind; prev++) {

            if (check(words[prev], words[ind])) {
                ans = Math.max(ans, 1 + solve(prev, words, dp));
            }
        }

        return dp[ind] = ans;
    }

    public int longestStrChain(String[] words) {

        Arrays.sort(words, (a, b) -> a.length() - b.length());

        int n = words.length;

        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        int max = 1;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, solve(i, words, dp));
        }

        return max;
    }
}