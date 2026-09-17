class Solution {
    int helper(int i ,int j, String s,int[][]dp){

        int n = s.length();


        if(i >= n || j < 0) return 0;
         if(dp[i][j] != -1) return dp[i][j];
        int ans = 0;
       

        if(s.charAt(i) == s.charAt(j)){
            int c1 =1+ helper(i+1,j-1,s,dp);
            ans += c1;

        }
        else{
            int c2= helper(i+1,j,s,dp);
            int c3 = helper(i,j-1,s,dp);

            ans += Math.max(c2,c3);
        }
        return dp[i][j] = ans;
    }
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int [][] dp = new int[1001][1001];

        for(int i = 0;i< dp.length;i++){
            java.util.Arrays.fill(dp[i],-1);
        }
        
      return  helper(0,n-1,s,dp);
    }
}