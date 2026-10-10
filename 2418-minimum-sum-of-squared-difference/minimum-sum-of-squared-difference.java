
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1 + k2;
        int n = nums1.length;
        long[] diff = new long[n];
        long max = 0;
        long sum = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            max = Math.max(max, diff[i]);
            sum += diff[i];
        }

        if (sum <= k) return 0;

        long l = 0, r = max;

        while (l < r) {
            long mid = (l + r) / 2;
            long need = 0;

            for (long d : diff) {
                if (d > mid) need += d - mid;
            }

            if (need <= k) r = mid;
            else l = mid + 1;
        }

        long ans = 0;
        long used = 0;

        for (int i = 0; i < n; i++) {
            if (diff[i] > l) {
                used += diff[i] - l;
                diff[i] = l;
            }
            ans += diff[i] * diff[i];
        }

        long rem = k - used;

        for (int i = 0; i < n && rem > 0; i++) {
            if (diff[i] == l && l > 0) {
                ans -= 2 * l - 1;
                rem--;
            }
        }

        return ans;
    }
}
