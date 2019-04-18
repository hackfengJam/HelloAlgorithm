package weight;

import java.util.List;

public interface Graph {
    public int V();

    public int E();

    public void addEdge(int v, int w, double weight);

    public boolean hasEdge(int v, int w);

    public Iterable<Edge> adj(int v);
}
