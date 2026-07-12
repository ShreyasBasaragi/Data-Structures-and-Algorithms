class SegmentTree {
    int[] tree, arr;
    int n;

    public SegmentTree(int[] nums) {
        n = nums.length;
        arr = nums;
        tree = new int[4 * n]; // Allocating 4*N space
        build(0, 0, n - 1);
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(2 * node + 1, start, mid);
            build(2 * node + 2, mid + 1, end);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public int query(int left, int right) {
        return queryUtil(0, 0, n - 1, left, right);
    }

    private int queryUtil(int node, int start, int end, int left, int right) {
        if (right < start || left > end) return 0; // Out of range
        if (left <= start && end <= right) return tree[node]; // Fully in range
        int mid = (start + end) / 2;
        return queryUtil(2 * node + 1, start, mid, left, right) +
               queryUtil(2 * node + 2, mid + 1, end, left, right);
    }

    public void update(int idx, int newValue) {
        updateUtil(0, 0, n - 1, idx, newValue);
    }

    private void updateUtil(int node, int start, int end, int idx, int newValue) {
        if (start == end) {
            arr[idx] = newValue;
            tree[node] = newValue;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) updateUtil(2 * node + 1, start, mid, idx, newValue);
            else updateUtil(2 * node + 2, mid + 1, end, idx, newValue);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }
}
