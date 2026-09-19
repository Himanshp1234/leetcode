class Solution {
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> tails = new ArrayList<>();

    for(int num : nums) {
        int left = 0;
        int right = tails.size();

        while(left < right) {
            int mid = left + (right - left) / 2;

            if(tails.get(mid) < num) {
             left = mid + 1;
            } else {
                right = mid;
            }
        }
        if( left == tails.size()) {
            tails.add(num);
        } else {
            tails.set(left,num);
        }
    }
    return tails.size();

        }
    }
