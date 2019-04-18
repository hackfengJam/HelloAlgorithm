package no_weight;

import java.util.Random;

public class Main {
    private void adjIteratorMain() {
        int N = 10;
        int M = 10;
        Random random = new Random();

        // no_weight.SparseGraph
        SparseGraph sparseGraph = new SparseGraph(N, false);
        for (int i = 0; i < M; i++) {
            int a = random.nextInt(N);
            int b = random.nextInt(N);
            sparseGraph.addEdge(a, b);
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

        // no_weight.DenseGraph
        DenseGraph denseGraph = new DenseGraph(N, false);
        for (int i = 0; i < M; i++) {
            int a = random.nextInt(N);
            int b = random.nextInt(N);
            denseGraph.addEdge(a, b);
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

        // no_weight.SparseGraph
        SparseGraph sparseGraph = new SparseGraph(N, false);
        for (int i = 0; i < M; i++) {
            int a = random.nextInt(N);
            int b = random.nextInt(N);
            sparseGraph.addEdge(a, b);
        }
        System.out.println(sparseGraph);


        // O(E)
        for (int v = 0; v < N; v++) {
            System.out.print(v + " : ");
            Iterable<Integer> iterator = sparseGraph.adj(v);
            for (Integer w : iterator) {
                System.out.print(w + " ");
            }
            System.out.println();
        }

        System.out.println("----------分割线----------");

        // no_weight.DenseGraph
        DenseGraph denseGraph = new DenseGraph(N, false);
        for (int i = 0; i < M; i++) {
            int a = random.nextInt(N);
            int b = random.nextInt(N);
            denseGraph.addEdge(a, b);
        }
        System.out.println(denseGraph);

        // O(V^2)
        for (int v = 0; v < N; v++) {
            System.out.print(v + " : ");
            Iterable<Integer> iterator = denseGraph.adj(v);
            for (Integer w : iterator) {
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
//        main.adjIteratorMain();
        main.iteratorMain();

    }
}
