package graph_bridges;

public class Edge implements Comparable<Edge> {
    private int a;
    private int b;
    private double weight;

    public Edge() {

    }

    public Edge(int a, int b, double weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    public int v() {
        return a;
    }

    public int w() {
        return b;
    }

    public double wt() {
        return weight;
    }

    public int other(int x) {
        assert (x == a || x == b);
        return x == a ? b : a;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.wt(), o.wt());
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", a, b, wt());
    }
}
