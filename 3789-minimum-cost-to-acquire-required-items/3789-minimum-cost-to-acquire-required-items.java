
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth,
                            int need1, int need2) {

       
        long costA = (long) need1 * cost1
                   + (long) need2 * cost2;

        
        int common = Math.min(need1, need2);

       
        long costB = (long) common * costBoth
                   + (long) (need1 - common) * cost1
                   + (long) (need2 - common) * cost2;

        
        long costC = (long) Math.max(need1, need2) * costBoth;

        return Math.min(costA, Math.min(costB, costC));
    }
}
