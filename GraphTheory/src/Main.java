import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int N = 10;
        int M = 10;
        Random random = new Random();

        SparseGraph sparseGraph = new SparseGraph(N, false);
        for (int i = 0; i < M; i++) {
            int a = random.nextInt(N);
            int b = random.nextInt(N);
            sparseGraph.addEdge(a, b);
        }
        System.out.println(sparseGraph);

        for (int v = 0; v < N; v++) {
            System.out.print(v + " : ");
            SparseGraph.adjIterator iterator = sparseGraph.new adjIterator(sparseGraph, v);
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }
            System.out.println();
        }
    }
}
