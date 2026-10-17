class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return solve(nums,0,0,target);


    }
    private int solve(int[] nums,int index,int currsum,int target) {
        if(index == nums.length) {
            if(currsum == target) {
                return 1;
            }
            return 0;
        }
        int plus = solve(nums,index + 1,currsum + nums[index],target);
        int minus = solve(nums,index + 1,currsum - nums[index],target);

        return plus + minus;
    }
}