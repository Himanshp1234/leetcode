import java.util.*;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        int zeroPos = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroPos = i;
                break;
            }
        }

        
        int normalK = zeroPos;
        int reverseK = (n - 1 - zeroPos + n) % n;

        boolean normalValid = true;
        boolean reverseValid = true;

        for (int i = 0; i < n; i++) {
            if (nums[(i + normalK) % n] != i) {
                normalValid = false;
            }

            int idx = (n - 1 - ((i + reverseK) % n) + n) % n;
            if (nums[idx] != i) {
                reverseValid = false;
            }
        }

        if (!normalValid && !reverseValid) {
            return -1;
        }

        
        int[][] dist = new int[2][n];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        dist[0][0] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int type = cur[0];
            int k = cur[1];
            int d = dist[type][k];

            if (type == 0 && k == normalK && normalValid) {
                return d;
            }

            if (type == 1 && k == reverseK && reverseValid) {
                return d;
            }

            
            int nextK = (k + 1) % n;
            if (dist[type][nextK] == -1) {
                dist[type][nextK] = d + 1;
                q.offer(new int[]{type, nextK});
            }

            
            int revK = (n - k) % n;
            int nextType = 1 - type;

            if (dist[nextType][revK] == -1) {
                dist[nextType][revK] = d + 1;
                q.offer(new int[]{nextType, revK});
            }
        }

        return -1;
    }
}