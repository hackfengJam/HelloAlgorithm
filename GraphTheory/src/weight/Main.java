package weight;

import java.util.Random;

public class Main {
    private void adjIteratorMain() {
        int N = 10;
        int M = 10;
        Random random = new Random();

        // weight.SparseGraph
        SparseGraph sparseGraph = new SparseGraph(N, false);
        for (int i = 0; i < M; i++) {
            int a = random.nextInt(N);
            int b = random.nextInt(N);
            sparseGraph.addEdge(a, b, 1);
        }
        System.out.println(sparseGraph);


        // O(E)
        for (int v = 0; v < N; v++) {
            System.out.print(v + " : ");
            SparseGraph.adjIterator iterator = sparseGraph.new adjIterator(sparseGraph, v);
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }
            System.out.println();
        }

        System.out.println("----------分割线----------");

        // weight.DenseGraph
        DenseGraph denseGraph = new DenseGraph(N, false);
        for (int i = 0; i < M; i++) {
            int a = random.nextInt(N);
            int b = random.nextInt(N);
            denseGraph.addEdge(a, b, 1);
        }
        System.out.println(denseGraph);

        // O(V^2)
        for (int v = 0; v < N; v++) {
            System.out.print(v + " : ");
            DenseGraph.adjIterator iterator = denseGraph.new adjIterator(denseGraph, v);
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }
            System.out.println();
        }
    }


    private void iteratorMain() {
        int N = 10;
        int M = 10;
        Random random = new Random();

        // weight.SparseGraph
        SparseGraph sparseGraph = new SparseGraph(N, false);
        for (int i = 0; i < M; i++) {
            int a = random.nextInt(N);
            int b = random.nextInt(N);
            sparseGraph.addEdge(a, b, 1);
        }
        System.out.println(sparseGraph);


        // O(E)
        for (int v = 0; v < N; v++) {
            System.out.print(v + " : ");
            Iterable<Edge> iterator = sparseGraph.adj(v);
            for (Edge w : iterator) {
                System.out.print(w + " ");
            }
            System.out.println();
        }

        System.out.println("----------分割线----------");

        // weight.DenseGraph
        DenseGraph denseGraph = new DenseGraph(N, false);
        for (int i = 0; i < M; i++) {
            int a = random.nextInt(N);
            int b = random.nextInt(N);
            denseGraph.addEdge(a, b, 1);
        }
        System.out.println(denseGraph);

        // O(V^2)
        for (int v = 0; v < N; v++) {
            System.out.print(v + " : ");
            Iterable<Edge> iterator = denseGraph.adj(v);
            for (Edge w : iterator) {
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }

    public void lazyPrimMst() {
        int N = 10;
        int M = 10;
        Random random = new Random();

        // weight.SparseGraph
        SparseGraph sparseGraph = new SparseGraph(N, false);
        for (int i = 0; i < M; i++) {
            int a = random.nextInt(N);
            int b = random.nextInt(N);
            double weight = Math.random();
            sparseGraph.addEdge(a, b, weight);
        }
        System.out.println(sparseGraph);


        // O(E)
        // 打印所有边
        for (int v = 0; v < N; v++) {
            System.out.print(v + " : ");
            Iterable<Edge> iterator = sparseGraph.adj(v);
            for (Edge w : iterator) {
                System.out.print(w + " ");
            }
            System.out.println();
        }

        // 打印最小生成树
        LazyPrimMST lazySparseGraphPrimMST = new LazyPrimMST(sparseGraph);
        Iterable<Edge> sparseGraphIterator = lazySparseGraphPrimMST.mstEdges();
        for (Edge w : sparseGraphIterator) {
            System.out.print(w + " ");
        }
        System.out.println();
        System.out.println(lazySparseGraphPrimMST.reuslt());


        System.out.println("----------分割线----------");

        // weight.DenseGraph
        DenseGraph denseGraph = new DenseGraph(N, false);
        for (int i = 0; i < M; i++) {
            int a = random.nextInt(N);
            int b = random.nextInt(N);
            double weight = Math.random();
            denseGraph.addEdge(a, b, weight);
        }
        System.out.println(denseGraph);

        // O(V^2)
        // 打印所有边
        for (int v = 0; v < N; v++) {
            System.out.print(v + " : ");
            Iterable<Edge> iterator = denseGraph.adj(v);
            for (Edge w : iterator) {
                System.out.print(w + " ");
            }
            System.out.println();
        }

        // 打印最小生成树
        LazyPrimMST lazyDenseGraphPrimMST = new LazyPrimMST(denseGraph);
        Iterable<Edge> denseGraphIterator = lazyDenseGraphPrimMST.mstEdges();
        for (Edge w : denseGraphIterator) {
            System.out.print(w + " ");
        }
        System.out.println();
        System.out.println(lazyDenseGraphPrimMST.reuslt());

    }


    public static void main(String[] args) {
        Main main = new Main();
//        main.adjIteratorMain();
//        main.iteratorMain();
        main.lazyPrimMst();

    }
}
