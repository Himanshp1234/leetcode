class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int count = 0;

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                count++;
                dfs(i, isConnected, visited);
            }
        }

        return count;
    }

    void dfs(int node, int[][] graph, boolean[] visited) {
        visited[node] = true;

        for(int i = 0; i < graph.length; i++) {
            if(graph[node][i] == 1 && !visited[i]) {
                dfs(i, graph, visited);
            }
        }
    }
}