class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] prefMax = new int[n + 1];
        int[] suffMin = new int[n + 1];

        prefMax[0] = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            prefMax[i + 1] = Math.max(prefMax[i], nums[i]);
        }

        suffMin[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            suffMin[i] = Math.min(suffMin[i + 1], nums[i]);
        }

        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean sortable = true;

            for (int start = 0; start < n; start += k) {

                
                if (start > 0 &&
                    prefMax[start] > suffMin[start]) {
                    sortable = false;
                    break;
                }

                
                int descents = 0;

                for (int i = start; i < start + k; i++) {
                    int next = (i + 1 == start + k)
                            ? start : i + 1;

                    if (nums[i] > nums[next]) {
                        descents++;
                    }

                    if (descents > 1) {
                        sortable = false;
                        break;
                    }
                }

                if (!sortable) {
                    break;
                }
            }

            if (sortable) {
                ans += k;
            }
        }

        return ans;
    }
}