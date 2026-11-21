
import java.util.*;

class Solution {

    public int[] findSubtreeSizes(int[] parent, String s) {

        int n = parent.length;

        List<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        
        int[] newParent = parent.clone();

       
        int[] last = new int[26];
        Arrays.fill(last, -1);

        
        int[] oldLast = new int[n];

        
        int[] stack = new int[2 * n + 1];
        int top = 0;

        stack[top++] = 0;

        while (top > 0) {

            int event = stack[--top];

           
            if (event >= n) {

                int node = event - n;
                int ch = s.charAt(node) - 'a';

                last[ch] = oldLast[node];

                continue;
            }

            
            int node = event;
            int ch = s.charAt(node) - 'a';

            oldLast[node] = last[ch];

           
            if (node != 0 && last[ch] != -1) {
                newParent[node] = last[ch];
            }

           
            last[ch] = node;

            
            stack[top++] = node + n;

            
            for (int i = tree[node].size() - 1; i >= 0; i--) {
                stack[top++] = tree[node].get(i);
            }
        }

        
        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        
        List<Integer>[] newTree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            newTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        int[] order = new int[n];
        int[] st = new int[n];

        int size = 0;
        int top2 = 0;

        st[top2++] = 0;

        while (top2 > 0) {

            int node = st[--top2];

            order[size++] = node;

            for (int child : newTree[node]) {
                st[top2++] = child;
            }
        }

        
        for (int i = size - 1; i > 0; i--) {

            int node = order[i];

            ans[newParent[node]] += ans[node];
        }

        return ans;
    }
}
