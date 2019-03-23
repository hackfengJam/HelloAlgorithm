import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        long startTIme;
        long endTIme;
        double time;
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("AVLTree/pride-and-prejudice.txt", words)) {

            Collections.sort(words);// 对于BST 来说



            // Test AVL Tree
            startTIme = System.nanoTime();

            AVLTree<String, Integer> avl = new AVLTree<>();
            for (String word : words) {
                if (avl.contains(word))
                    avl.set(word, avl.get(word) + 1);
                else
                    avl.add(word, 1);
            }

            // 查询操作
            for (String word : words) {
                avl.contains(word);
            }

            endTIme = System.nanoTime();

            time = (endTIme - startTIme) / 1000000000.0;
            System.out.println("AVLTree: " + time + " s");

            // Test HashTable
            startTIme = System.nanoTime();

            HashTable<String, Integer> ht = new HashTable<>();
            for (String word : words) {
                if (ht.contains(word))
                    ht.set(word, ht.get(word) + 1);
                else
                    ht.add(word, 1);
            }

            // 查询操作
            for (String word : words) {
                ht.contains(word);
            }

            endTIme = System.nanoTime();

            time = (endTIme - startTIme) / 1000000000.0;
            System.out.println("HashTable: " + time + " s");
        }
    }
}
