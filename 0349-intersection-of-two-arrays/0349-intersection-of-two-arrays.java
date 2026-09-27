import java.util.*;

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> ansSet = new HashSet<>();

        for (int num : nums1) {
            set1.add(num);
        }

        for (int num : nums2) {
            if (set1.contains(num)) {
                ansSet.add(num);
            }
        }

        int[] ans = new int[ansSet.size()];
        int i = 0;

        for (int num : ansSet) {
            ans[i++] = num;
        }

        return ans;
    }
}