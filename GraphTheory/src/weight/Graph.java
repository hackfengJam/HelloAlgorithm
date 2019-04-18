package weight;

public interface Graph<Weight> {
    public int V();

    public int E();

    public void addEdge(int v, int w, Weight weight);

    public boolean hasEdge(int v, int w);

}
