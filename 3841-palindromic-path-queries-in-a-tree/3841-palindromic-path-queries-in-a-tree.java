
import java.util.*;

class Solution {

    List<Integer>[] adj;

    int[] parent, depth, size, heavy;
    int[] head, pos;
    int timer;

    char[] chars;
    SegmentTree seg;

    // Segment tree: stores XOR masks
    class SegmentTree {
        int size;
        int[] tree;

        SegmentTree(int[] arr) {
            size = 1;

            while (size < arr.length) {
                size <<= 1;
            }

            tree = new int[2 * size];

            for (int i = 0; i < arr.length; i++) {
                tree[size + i] = arr[i];
            }

            for (int i = size - 1; i >= 1; i--) {
                tree[i] = tree[2 * i] ^ tree[2 * i + 1];
            }
        }

        // Update one position
        void update(int index, int value) {
            int p = size + index;
            tree[p] = value;

            p >>= 1;

            while (p >= 1) {
                tree[p] = tree[2 * p] ^ tree[2 * p + 1];
                p >>= 1;
            }
        }

        
        int query(int left, int right) {
            left += size;
            right += size;

            int ans = 0;

            while (left <= right) {

                if ((left & 1) == 1) {
                    ans ^= tree[left];
                    left++;
                }

                if ((right & 1) == 0) {
                    ans ^= tree[right];
                    right--;
                }

                left >>= 1;
                right >>= 1;
            }

            return ans;
        }
    }

    
    void buildTree(int n) {
        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];

        Arrays.fill(parent, -2);
        Arrays.fill(heavy, -1);
        Arrays.fill(size, 1);

        int[] order = new int[n];
        int[] stack = new int[n];

        int top = 0;
        int count = 0;

        stack[top++] = 0;
        parent[0] = -1;

        while (top > 0) {
            int u = stack[--top];
            order[count++] = u;

            for (int v : adj[u]) {
                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

       
        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            int p = parent[u];

            size[p] += size[u];

            if (heavy[p] == -1 ||
                size[u] > size[heavy[p]]) {
                heavy[p] = u;
            }
        }

        
        head = new int[n];
        pos = new int[n];
        timer = 0;

        int[] chainStack = new int[n];
        int chainTop = 0;

        chainStack[chainTop++] = 0;

        while (chainTop > 0) {
            int start = chainStack[--chainTop];

            for (int u = start; u != -1; u = heavy[u]) {
                head[u] = start;
                pos[u] = timer++;

                for (int v : adj[u]) {
                    if (v != parent[u] && v != heavy[u]) {
                        chainStack[chainTop++] = v;
                    }
                }
            }
        }
    }

   
    int pathMask(int u, int v) {
        int mask = 0;

        while (head[u] != head[v]) {

            
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }

            mask ^= seg.query(pos[head[u]], pos[u]);

            u = parent[head[u]];
        }

        
        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        mask ^= seg.query(pos[u], pos[v]);

        return mask;
    }

    public List<Boolean> palindromePath(
            int n,
            int[][] edges,
            String s,
            String[] queries) {

        
        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj[u].add(v);
            adj[v].add(u);
        }

        chars = s.toCharArray();

        
        buildTree(n);

        
        int[] base = new int[n];

        for (int u = 0; u < n; u++) {
            base[pos[u]] = 1 << (chars[u] - 'a');
        }

        
        seg = new SegmentTree(base);

        List<Boolean> answer = new ArrayList<>();

        
        for (String query : queries) {
            String[] parts = query.trim().split("\\s+");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

               
                chars[u] = c;

                
                int mask = 1 << (c - 'a');
                seg.update(pos[u], mask);

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

               
                int mask = pathMask(u, v);

                
                boolean possible =
                    (mask & (mask - 1)) == 0;

                answer.add(possible);
            }
        }

        return answer;
    }
}
