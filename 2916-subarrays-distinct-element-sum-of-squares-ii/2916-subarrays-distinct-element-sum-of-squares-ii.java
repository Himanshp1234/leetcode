class Solution {
    static final long MOD = 1_000_000_007L;

    int n;
    long[] sum, sq, lazy;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        java.util.HashMap<Integer, Integer> last = new java.util.HashMap<>();

        long ans = 0;

        for (int r = 0; r < n; r++) {
            int p = last.getOrDefault(nums[r], -1);

            update(1, 0, n - 1, p + 1, r);

            ans = (ans + sq[1]) % MOD;

            last.put(nums[r], r);
        }

        return (int) ans;
    }

    void apply(int node, int l, int r, long delta) {
        long len = r - l + 1;
        long oldSum = sum[node];

        sq[node] = (sq[node]
                + 2 * delta * oldSum
                + delta * delta * len) % MOD;

        sum[node] = (oldSum + delta * len) % MOD;
        lazy[node] += delta;
    }

    void push(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) {
            return;
        }

        int mid = (l + r) / 2;
        long delta = lazy[node];

        apply(node * 2, l, mid, delta);
        apply(node * 2 + 1, mid + 1, r, delta);

        lazy[node] = 0;
    }

    void update(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) {
            return;
        }

        if (ql <= l && r <= qr) {
            apply(node, l, r, 1);
            return;
        }

        push(node, l, r);

        int mid = (l + r) / 2;

        update(node * 2, l, mid, ql, qr);
        update(node * 2 + 1, mid + 1, r, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }
}