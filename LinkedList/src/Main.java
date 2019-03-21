public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        LinkedListDummyHead<Integer> linkedListDummyHead = new LinkedListDummyHead<>();

        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            linkedListDummyHead.addFirst(i);
            System.out.println("LinkedList: " + linkedList);
            System.out.println("linkedListDummyHead: " + linkedListDummyHead);
        }

        linkedList.add(2, 666);
        linkedListDummyHead.add(2, 666);
        System.out.println("LinkedList: " + linkedList);
        System.out.println("linkedListDummyHead: " + linkedListDummyHead);

        linkedListDummyHead.remove(2);
        System.out.println(linkedListDummyHead);
    }
}
