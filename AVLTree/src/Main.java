import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        long startTIme;
        long endTIme;
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("AVLTree/pride-and-prejudice.txt", words)) {

            Collections.sort(words);// 对于BST 来说

            // Test BST
            startTIme = System.nanoTime();

            BST<String, Integer> bst = new BST<>();
            for (String word : words) {
                if (bst.contains(word))
                    bst.set(word, bst.get(word) + 1);
                else
                    bst.add(word, 1);
            }

            // 查询操作
            for (String word : words) {
                bst.contains(word);
            }

            endTIme = System.nanoTime();

            double time = (endTIme - startTIme) / 1000000000.0;
            System.out.println("BST: " + time + " s");


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
        }
    }
}
