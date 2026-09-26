class Solution {
    public long minOperations(int[] nums1, int[] nums2) {
        int n = nums1.length;

        long ans = 0;

        for (int i = 0; i < n; i++) {
            ans += Math.abs((long) nums1[i] - nums2[i]);
        }

        long target = nums2[n];
        long bestExtra = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            long a = nums1[i];
            long b = nums2[i];

            long l = Math.min(a, b);
            long r = Math.max(a, b);

            long extra;

            if (target < l) {
                extra = l - target;
            } else if (target > r) {
                extra = target - r;
            } else {
                extra = 0;
            }

            bestExtra = Math.min(bestExtra, extra);
        }

        return ans + bestExtra + 1;
    }
}