import java.util.Arrays;

class Solution {
    private int[] totalProd;
    private int[][] count;
    private int K, N;

    private void merge(int parent, int left, int right) {
        totalProd[parent] = (totalProd[left] * totalProd[right]) % K;
        System.arraycopy(count[left], 0, count[parent], 0, K);
        for (int x = 0; x < K; x++) {
            if (count[right][x] > 0) {
                count[parent][(totalProd[left] * x) % K] += count[right][x];
            }
        }
    }

    private void build(int[] nums, int node, int start, int end) {
        if (start == end) {
            totalProd[node] = nums[start] % K;
            count[node][totalProd[node]] = 1;
            return;
        }
        int mid = (start + end) >> 1;
        build(nums, 2 * node + 1, start, mid);
        build(nums, 2 * node + 2, mid + 1, end);
        merge(node, 2 * node + 1, 2 * node + 2);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            totalProd[node] = val % K;
            Arrays.fill(count[node], 0);
            count[node][totalProd[node]] = 1;
            return;
        }
        int mid = (start + end) >> 1;
        if (idx <= mid) update(2 * node + 1, start, mid, idx, val);
        else update(2 * node + 2, mid + 1, end, idx, val);
        merge(node, 2 * node + 1, 2 * node + 2);
    }

    private int[] query(int node, int start, int end, int l, int r) {
        if (l <= start && end <= r) return count[node];
        int mid = (start + end) >> 1;
        if (r <= mid) return query(2 * node + 1, start, mid, l, r);
        if (l > mid) return query(2 * node + 2, mid + 1, end, l, r);

        // Merge results on the fly for overlapping ranges
        int[] leftC = query(2 * node + 1, start, mid, l, mid);
        int[] rightC = query(2 * node + 2, mid + 1, end, mid + 1, r);
        
        int leftProd = 1; // Find the total product of the left query slice
        for (int i = 0; i < K; i++) if (leftC[i] > 0) leftProd = (leftProd * totalProd[2 * node + 1]) % K; 
        // A cleaner way to get actual slice product is tracking it, but since we only need the final target count:
        return combine(leftC, rightC, getSliceProd(2 * node + 1, start, mid, l, mid));
    }

    private int getSliceProd(int node, int start, int end, int l, int r) {
        if (l <= start && end <= r) return totalProd[node];
        int mid = (start + end) >> 1;
        if (r <= mid) return getSliceProd(2 * node + 1, start, mid, l, r);
        if (l > mid) return getSliceProd(2 * node + 2, mid + 1, end, l, r);
        return (getSliceProd(2 * node + 1, start, mid, l, mid) * getSliceProd(2 * node + 2, mid + 1, end, mid + 1, r)) % K;
    }

    private int[] combine(int[] leftC, int[] rightC, int leftProd) {
        int[] res = new int[K];
        System.arraycopy(leftC, 0, res, 0, K);
        for (int x = 0; x < K; x++) {
            res[(leftProd * x) % K] += rightC[x];
        }
        return res;
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.N = nums.length;
        this.K = k;
        this.totalProd = new int[4 * N];
        this.count = new int[4 * N][K];

        build(nums, 0, 0, N - 1);

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            update(0, 0, N - 1, queries[i][0], queries[i][1]);
            result[i] = query(0, 0, N - 1, queries[i][2], N - 1)[queries[i][3]];
        }
        return result;
    }
}
