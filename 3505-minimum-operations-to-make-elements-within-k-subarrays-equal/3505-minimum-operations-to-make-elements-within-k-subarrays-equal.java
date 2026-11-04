
import java.util.*;

class Solution {

    TreeMap<Integer, Integer> left = new TreeMap<>();
    TreeMap<Integer, Integer> right = new TreeMap<>();

    long sumLeft = 0;
    long sumRight = 0;

    int leftSize = 0;
    int rightSize = 0;

    void addLeft(int val) {
        left.put(val, left.getOrDefault(val, 0) + 1);
        sumLeft += val;
        leftSize++;
    }

    void addRight(int val) {
        right.put(val, right.getOrDefault(val, 0) + 1);
        sumRight += val;
        rightSize++;
    }

    void removeLeft(int val) {
        if (left.get(val) == 1) {
            left.remove(val);
        } else {
            left.put(val, left.get(val) - 1);
        }

        sumLeft -= val;
        leftSize--;
    }

    void removeRight(int val) {
        if (right.get(val) == 1) {
            right.remove(val);
        } else {
            right.put(val, right.get(val) - 1);
        }

        sumRight -= val;
        rightSize--;
    }

    void balance() {

        while (leftSize > rightSize + 1) {
            int val = left.lastKey();
            removeLeft(val);
            addRight(val);
        }

        while (leftSize < rightSize) {
            int val = right.firstKey();
            removeRight(val);
            addLeft(val);
        }
    }

    void add(int val) {
        if (left.isEmpty() || val <= left.lastKey()) {
            addLeft(val);
        } else {
            addRight(val);
        }

        balance();
    }

    void remove(int val) {
        if (left.containsKey(val)) {
            removeLeft(val);
        } else {
            removeRight(val);
        }

        balance();
    }

    long getCost() {
        int median = left.lastKey();

        return (long) median * leftSize - sumLeft
             + sumRight - (long) median * rightSize;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] cost = new long[n - x + 1];

        for (int i = 0; i < x; i++) {
            add(nums[i]);
        }

        cost[0] = getCost();

        for (int i = x; i < n; i++) {
            remove(nums[i - x]);
            add(nums[i]);

            cost[i - x + 1] = getCost();
        }

        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= k; j++) {

                dp[i][j] = dp[i - 1][j];

                if (i >= x && dp[i - x][j - 1] != INF) {

                    long take = dp[i - x][j - 1]
                              + cost[i - x];

                    dp[i][j] = Math.min(dp[i][j], take);
                }
            }
        }

        return dp[n][k];
    }
}
