class Solution {
    public boolean canReach(int[] arr, int start) {
        boolean[] visit = new boolean[arr.length];
        return solve(arr, start, visit);
    }

    public boolean solve(int[] arr, int i, boolean[] visit) {

        
        if (i < 0 || i >= arr.length) {
            return false;
        }

    
        if (visit[i]) {
            return false;
        }

        
        if (arr[i] == 0) {
            return true;
        }

        visit[i] = true;

        
        return solve(arr, i - arr[i], visit)
            || solve(arr, i + arr[i], visit);
    }
}