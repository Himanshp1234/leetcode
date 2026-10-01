import java.util.*;

class Solution {

    public int longestSubarray(int[] nums, int k) {

        int n = nums.length;

        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int ans = 0;

        for (int l = 0; l < n; l++) {

            HashSet<Integer> seen = new HashSet<>();

            for (int r = l; r < n; r++) {

                int sum = prefix[r + 1] - prefix[l];

                int rem = ((sum % k) + k) % k;

                
                if (rem == 0) {
                    ans = Math.max(ans, r - l + 1);
                }

                
                int value = ((2 * nums[r]) % k + k) % k;
                seen.add(value);

                
                if (seen.contains(rem)) {
                    ans = Math.max(ans, r - l + 1);
                }
            }
        }

        return ans;
    }
}