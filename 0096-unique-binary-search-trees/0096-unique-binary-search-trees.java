
class Solution {
    public int numTrees(int n) {
        if (n <= 1) {
            return 1;
        }

        int count = 0;

        for (int i = 1; i <= n; i++) {
            int left = numTrees(i - 1);
            int right = numTrees(n - i);

            count += left * right;
        }

        return count;
    }
}
