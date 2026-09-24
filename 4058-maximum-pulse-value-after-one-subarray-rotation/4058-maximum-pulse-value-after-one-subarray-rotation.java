class Solution {
    public long maxValue(int[] nums) {
        int n = nums.length;

        long basePulse = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                basePulse += nums[i];
            } else {
                basePulse -=nums[i];
            }
        }

        if (n < 2) {
            return basePulse;
        }

        long[] d = new long[n];
        for (int k = 0; k < n; k++) {
            if (k % 2 == 0) {
                d[k] = -2L * nums[k];
            } else {
                d[k] = 2L * nums[k];
            }
        }

        long maxGain = 0;
        long[] minP = new long[2];
        minP[0] = 0;
        minP[1] = Long.MAX_VALUE;

        long currentP = 0;
        long prevP = 0;

        for (int m = 1; m <= n; m++) {
            currentP += d[m - 1];

            int parity = m % 2;
            if (minP[parity] != Long.MAX_VALUE) {
                maxGain = Math.max(maxGain, currentP - minP[parity]);
            }

            int prevParity = (m - 1) % 2;
            minP[prevParity] = Math.min(minP[prevParity], prevP);

            prevP = currentP;
        }

        return basePulse + maxGain;
    }
}