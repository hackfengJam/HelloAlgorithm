import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 稀疏图 - 邻接表
public class SparseGraph {
    private int n, m; // n 为顶点 V，m 为边 E

    // 指定是否为有向图
    private boolean directed;

    private List<List<Integer>> g;

    public SparseGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> line = new ArrayList<>();
            g.add(line);
        }
    }

    public int V() {
        return n;
    }

    public int E() {
        return m;
    }

    public void addEdge(int v, int w) {
        assert (v >= 0 && v < n);
        assert (w >= 0 && w < n);

        if (hasEdge(v, w))
            return;

        g.get(v).add(w);

        // v != w 排除自环
        if (v != w && !directed)
            g.get(w).add(v);

        m++;
    }

    public boolean hasEdge(int v, int w) {
        assert (v >= 0 && v < n);
        assert (w >= 0 && w < n);

        for (int i = 0; i < g.get(v).size(); i++) {
            if (g.get(v).get(i) == w)
                return true;
        }
        return false;
    }

    public class AdjIterator implements Iterator {
        private SparseGraph G;
        private int v;
        int index;

        public AdjIterator(SparseGraph graph, int v) {
            this.G = graph;
            this.v = v;
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index >= G.g.get(v).size();
        }

        @Override
        public Object next() {
            index++;
            if (index < G.g.get(v).size())
                return G.g.get(v).get(index);
            return -1;
        }
    }


}
