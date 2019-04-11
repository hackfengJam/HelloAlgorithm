public class MaxHeap<Key extends Comparable<Key>> {
    private Key[] data;
    private int capacity;
    private int count;

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public MaxHeap(int capacity) {
        data = (Key[]) new Comparable[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    private boolean less(int i, int j) {
        return data[i].compareTo(data[j]) < 0;
    }

    private void exch(int i, int j) {
        Key tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public void swin(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    public void sink(int k) {
        while (k * 2 <= count) {
            int j = k * 2;
            if (j < count && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public void insert(Key key) {
        data[++count] = key;
        swin(count);
    }

    public Key delMax() {
        Key key = data[1];
        exch(1, count);
        data[count--] = null;
        sink(1);
        return key;
    }

    private void sort(Comparable a[]) {

    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 5, 6};
        MaxHeap<Integer> maxHeap = new MaxHeap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            maxHeap.insert(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(maxHeap.delMax());
        }
    }

}
