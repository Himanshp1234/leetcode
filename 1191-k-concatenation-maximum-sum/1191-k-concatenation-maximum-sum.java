
class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        long MOD = 1_000_000_007L;

        long totalSum = 0;
        long current = 0;
        long maxOne = 0;

        
        for (int num : arr) {
            totalSum += num;
            current = Math.max(0, current + num);
            maxOne = Math.max(maxOne, current);
        }

        
        if (k == 1) {
            return (int) (maxOne % MOD);
        }


        current = 0;
        long maxTwo = 0;

        for (int i = 0; i < 2 * arr.length; i++) {
            int num = arr[i % arr.length];

            current = Math.max(0, current + num);
            maxTwo = Math.max(maxTwo, current);
        }

        
        long answer = maxTwo;

        if (totalSum > 0) {
            answer += (long) (k - 2) * totalSum;
        }

        return (int) (answer % MOD);
    }
}
