import java.util.ArrayList;
import java.util.List;

class DisjointSet {
    List<Integer> rank = new ArrayList<>();
    List<Integer> size = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    public DisjointSet(int n) {
        for (int i = 0; i < n; i++) {
            rank.add(0);
            size.add(1);
            parent.add(i);
        }
    }

    public int findUPar(int node){
        if (node == parent.get(node)) {
            return node;
        }

        int uParent = findUPar(parent.get(node));
        parent.set(node, uParent);
        return uParent;
    }

    public boolean find(int u, int v) {
        int uPar_u = findUPar(u);
        int uPar_v = findUPar(v);

        return uPar_u == uPar_v;
    }

    public void unionByRank(int u, int v) {
        int uPar_u = findUPar(u);
        int uPar_v = findUPar(v);

        if (uPar_u == uPar_v) {
            return;
        }

        if (rank.get(uPar_u) < rank.get(uPar_v)) {
            parent.set(uPar_u, uPar_v);
        }else if(rank.get(uPar_u) > rank.get(uPar_v)){
            parent.set(uPar_v, uPar_u);
        }else{
            parent.set(uPar_v, uPar_u);
            rank.set(uPar_u,rank.get(uPar_u) + 1);
        }
    }

    public void unionBySize(int u, int v) {
        int uPar_u = findUPar(u);
        int uPar_v = findUPar(v);

        if (uPar_u == uPar_v) {
            return;
        }

        if (size.get(uPar_u) < size.get(uPar_v)) {
            parent.set(uPar_u, uPar_v);
            size.set(uPar_v, size.get(uPar_v) + size.get(uPar_u));
        }else{
            parent.set(uPar_v, uPar_u);
            size.set(uPar_u, size.get(uPar_u) + size.get(uPar_v));
        }
    }
}
