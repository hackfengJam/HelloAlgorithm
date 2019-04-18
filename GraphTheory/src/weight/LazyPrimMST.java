package weight;

import java.util.ArrayList;
import java.util.List;

public class LazyPrimMST {
    /*
     * 最小生成树问题 Minimum Span Tree
     * 应用：
            - 电缆布线设计
            - 网络设计
            - 电路设计
     * 针对带权无向图
     * 针对连通图
     *
     * 找 V-1 条变
     * 链接 V 个顶点
     * 总权值最小
     *
     * 把图中的节点分为两部分，成为一个切分（Cut）。
     * 如果一个边的两个端点，属于切分（Cut）的不同两边，这个边称为横切边（Crossing Edge）。
     *
     * 切分定理（Cut Property）：
     *  给定任意切分，横切边中最小的边，必然属于最小生成树。
     *
     *  时间复杂度 O(ElogE)
     * */
    private Graph G;
    MinHeap<Edge> pq;

    // 标记是否已经访问过
    boolean[] marked;

    // 最小生成树
    List<Edge> mst;
    // 最小生成树对应总权值
    double mstWeight;

    public LazyPrimMST(Graph G) {
        this.G = G;
        this.pq = new MinHeap<>(this.G.E());

        marked = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            marked[i] = false;
        }
        mst = new ArrayList<>();

        // Lazy Prim
        visit(0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();

//            if (marked[e.v()] == marked[e.w()]) continue;
            if (marked[e.v()] && marked[e.w()]) continue;

            mst.add(e);
//            if (!marked[e.v()])
//                visit(e.v());
//            else
//                visit(e.w());
            if (!marked[e.v()]) visit(e.v());
            if (!marked[e.w()]) visit(e.w());
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
            if (!marked[e.other(v)])
                pq.insert(e);
        }


    }

    public List<Edge> mstEdges() {
        return mst;
    }

    public double reuslt() {
        return mstWeight;
    }
}
