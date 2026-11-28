import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxF = 0;
        int minP = Integer.MAX_VALUE;

        for (int[] item : items) {
            maxF = Math.max(maxF, item[0]);
            minP = Math.min(minP, item[1]);
        }

        int[] freq = new int[maxF + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        int[] gain = new int[maxF + 1];

        for (int i = 1; i <= maxF; i++) {
            for (int j = i; j <= maxF; j += i) {
                gain[i] += freq[j];
            }
            gain[i]--;
        }

        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        long rem = budget;
        long ans = 0;

        for (int[] item : items) {
            int f = item[0];
            int p = item[1];

            if (p >= 2L * minP) {
                break;
            }

            if (gain[f] <= 0) {
                continue;
            }

            long take = Math.min(rem / p, gain[f]);

            ans += take * 2;
            rem -= take * p;
        }

        ans += rem / minP;

        return (int) ans;
    }
}