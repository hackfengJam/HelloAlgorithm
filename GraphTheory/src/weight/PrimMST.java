package weight;

import java.util.ArrayList;
import java.util.List;

public class PrimMST {
    /*
     * 使用索引堆
     * 优化后的 Prim 算法的实现
     *
     * */
    private Graph G;
    IndexMinHeap<Double> ipq;

    List<Edge> edgeTo;

    // 标记是否已经访问过
    boolean[] marked;

    // 最小生成树
    List<Edge> mst;
    // 最小生成树对应总权值
    double mstWeight;

    public PrimMST(Graph G) {
        this.G = G;
        this.ipq = new IndexMinHeap<>(this.G.V());

        this.edgeTo = new ArrayList<>();

        marked = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            marked[i] = false;
            edgeTo.add(null);
        }
        mst = new ArrayList<>();

        // Lazy Prim
        visit(0);
        while (!ipq.isEmpty()) {
            int v = ipq.delMinIndex();
            assert (edgeTo.get(v) != null);
            mst.add(edgeTo.get(v));
            visit(v);
        }

        // 计算最小生成树总权值
        for (Edge e : mst) {
            mstWeight += e.wt();
        }
    }

    private void visit(int v) {
        assert (!marked[v]);
        marked[v] = true;

        Iterable<Edge> iterable = G.adj(v);
        for (Edge e : iterable) {
            int w = e.other(v);
            if (!marked[w]) {
                if (edgeTo.get(w) == null) {
                    ipq.insert(w, e.wt());
                    edgeTo.set(w, e);
                } else if (e.wt() < edgeTo.get(w).wt()) {
                    edgeTo.set(w, e);
                    ipq.change(w, e.wt());
                }
            }
        }
    }

    public List<Edge> mstEdges() {
        return mst;
    }

    public double reuslt() {
        return mstWeight;
    }
}
