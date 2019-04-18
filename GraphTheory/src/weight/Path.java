package weight;

import java.util.List;

public class Path {
    private Graph G;
    private int s;
    private List<Boolean> visited;
    private List<Integer> from;

    public Path(Graph graph, int s) {
        this.G = graph;
        assert (s >= 0 && s < G.V());

    }
}
