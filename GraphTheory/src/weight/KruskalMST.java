package weight;

import java.util.ArrayList;
import java.util.List;

public class KruskalMST {
    /*
     * UnionFind + MinHeap
     * UnionFind 判断当加入一个边时，是否存在环，MinHeap 找到当前图中最小边
     *
     * ElogE + ElogV
     * */
    private List<Edge> mst;
    private double mstWeight;

    public KruskalMST(Graph g) {
        mst = new ArrayList<>();
        MinHeap<Edge> pq = new MinHeap<>(g.E());

        for (int i = 0; i < g.V(); i++) {
            Iterable<Edge> iterable = g.adj(i);
            for (Edge edge : iterable) {
                if (edge.v() < edge.w())
                    // 有向图边
                    pq.insert(edge);
            }
        }


        UnionFind uf = new UnionFind(g.V());
        while (!pq.isEmpty() && mst.size() < g.V() - 1) {
            Edge e = pq.delMin();
            if (uf.isConnected(e.v(), e.w()))
                continue;

            mst.add(e);
            uf.unionElements(e.v(), e.w());
        }

        // 计算最小生成树总权值
        for (Edge e : mst) {
            mstWeight += e.wt();
        }

    }


    public List<Edge> mstEdges() {
        return mst;
    }

    public double reuslt() {
        return mstWeight;
    }
}
