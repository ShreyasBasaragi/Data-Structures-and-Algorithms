class Node{
    int maxlen, prefix, suffix;
    char leftchar, rightchar;
}

public class SegmentTree {

    Node[] segtree;
    public void buildsegtree(int i, int l, int r, String s){
        int mid = (l + r)/2;
        buildsegtree(2*i + 1, l, mid, s);;
        buildsegtree(2*i + 2, mid + 1, r, s);
        segtree[i] = merge(segtree[2*i + 1], segtree[2*i + 2], mid - l + 1, r - mid);
    }

    public Node merge(Node l, Node r, int leftlen, int rightlen){
        Node result = new Node();
        result.leftchar = l.leftchar;
        result.rightchar = r.rightchar;

        // update prefix
        result.prefix = l.prefix;
        // handling the case where prefix could have been extended while merging due to same characters
        if (l.prefix == leftlen && l.rightchar == r.leftchar) {
            result.prefix = l.prefix + r.prefix;
        }

        // update suffix
        result.suffix = r.suffix;
        if (r.suffix == rightlen && l.rightchar == r.leftchar) {
            result.suffix = r.suffix + l.suffix;
        }

        // update maxlen
        result.maxlen = Math.max(l.maxlen, r.maxlen);
        // also check the middle part for maxlen as well
        if (l.rightchar == r.leftchar) {
            result.maxlen = Math.max(result.maxlen, l.suffix + r.prefix);
        }

        return result;
    }

    public void update(int i, int l, int r, int pos, char c){
        if (l == r) {
            segtree[i].maxlen = 1;
            segtree[i].prefix = 1;
            segtree[i].suffix = 1;
            segtree[i].leftchar = c;
            segtree[i].rightchar = c;
            return ;
        }

        int mid = (l + r)/2;
        if (pos <= mid) {
            update(2*1 + 1, l, mid, pos, c);
        }else{
            update(2*1 + 2, mid + 1, r, pos, c);
        }
        
        segtree[i] = merge(segtree[2*1 + 1], segtree[2*i + 2], mid - l + 1, r - mid);
        return ;
    }
}
