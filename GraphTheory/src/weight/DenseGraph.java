package weight;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 稠密图 - 邻接矩阵
public class DenseGraph<Weight> implements Graph<Weight> {
    private int n, m; // n 为顶点 V，m 为边 E

    // 指定是否为有向图
    private boolean directed;

    List<List<Edge<Weight>>> g;

    public DenseGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Edge<Weight>> line = new ArrayList<>();
            for (int j = 0; j < n; j++)
                line.add(null);
            g.add(line);
        }
    }

    public int V() {
        return n;
    }

    public int E() {
        return m;
    }

    public void addEdge(int v, int w, Weight weight) {
        assert (v >= 0 && v < n);
        assert (w >= 0 && w < n);

        if (hasEdge(v, w)) {
            // 采取覆盖策略，先设为 null
            g.get(v).set(w, null);
            if (!directed)
                g.get(w).set(v, null);
        }

        g.get(v).set(w, new Edge<>(v, w, weight));
        if (!directed)
            g.get(w).set(v, new Edge<>(w, v, weight));

        m++;
    }

    public boolean hasEdge(int v, int w) {
        assert (v >= 0 && v < n);
        assert (w >= 0 && w < n);

        return g.get(v).get(w) != null;
    }

    public Iterable<Integer> adj(int v) {
        List<Integer> iter = new ArrayList<>();
        for (int index = 0; index < g.size(); index++)
            if (g.get(v).get(index) != null)
                iter.add(index);
        return iter;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("weight.DenseGraph\n");
        stringBuilder.append("-----------\n");
        for (int i = 0; i < g.size(); i++) {
            List<Edge<Weight>> line = g.get(i);
            stringBuilder.append(i);
            stringBuilder.append(" : ");
            for (int j = 0; j < line.size(); j++) {
//                boolean v = line.get(j);
                if (line.get(j) != null) {
                    stringBuilder.append(j);
                    if (j < line.size() - 1)
                        stringBuilder.append(", ");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    class adjIterator implements Iterator {
        private DenseGraph<Weight> G;
        private int v;
        int index;

        public adjIterator(DenseGraph<Weight> graph, int v) {
            this.G = graph;
            this.v = v;
            this.index = -1;
        }

        @Override
        public boolean hasNext() {
            return index + 1 < G.V();
        }

        @Override
        public Object next() {
            for (index += 1; index < G.V(); index++)
                if (G.g.get(v).get(index) != null)
                    return index;
            return -1;
        }
    }

}
