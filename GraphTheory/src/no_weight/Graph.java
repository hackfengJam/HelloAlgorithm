package no_weight;

public interface Graph {
    public int V();

    public int E();

    public void addEdge(int v, int w);

    public boolean hasEdge(int v, int w);

}
