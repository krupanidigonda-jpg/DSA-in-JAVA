class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] curr = new long[k];

            curr[num % k]++;

            for (int i = 0; i < k; i++) {
                int r = (int)((long)i * num % k);
                curr[r] += dp[i];
            }

            for (int i = 0; i < k; i++) {
                result[i] += curr[i];
            }

            dp = curr;
        }

        return result;
    }
}