package graph_bridges;

import java.util.ArrayList;

public class FindBridges {

    private Graph G;
    private boolean[] visited;

    private int ord[];
    private int low[];
    private int cnt;

    private ArrayList<Edge> res;

    public FindBridges(Graph G) {

        this.G = G;
        visited = new boolean[G.V()];
        ord = new int[G.V()];
        low = new int[G.V()];
        cnt = 0;
        res = new ArrayList<>();

        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                dfs(v, v);
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        ord[v] = cnt;
        low[v] = ord[v];
        cnt++;

        for (int w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] > ord[v]) {
                    // v-w 是桥
                    res.add(new Edge(v, w, 1));
                }
            } else if (w != parent) {
                low[v] = Math.min(low[v], low[w]);
            }
        }
    }

    public ArrayList<Edge> result() {
        return res;
    }

    public static void main(String[] args) {

        Graph g = new Graph("./GraphTheory/src/graph_bridges/g.txt");
        FindBridges fb = new FindBridges(g);
        System.out.println(fb.result());

        Graph g2 = new Graph("./GraphTheory/src/graph_bridges/g2.txt");
        FindBridges fb2 = new FindBridges(g2);
        System.out.println(fb2.result());

        Graph tree = new Graph("./GraphTheory/src/graph_bridges/tree.txt");
        FindBridges fb3 = new FindBridges(tree);
        System.out.println(fb3.result());
    }
}
