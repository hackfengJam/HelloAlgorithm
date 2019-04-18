package weight;

public class MinHeap<Key extends Comparable<Key>> {
    private Key[] data;
    private int capacity;
    private int count;

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public MinHeap(int capacity) {
        data = (Key[]) new Comparable[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    private boolean more(int i, int j) {
        return data[i].compareTo(data[j]) > 0;
    }

    private void exch(int i, int j) {
        Key tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public void swin(int k) {
        while (k > 1 && more(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    public void sink(int k) {
        while (k * 2 <= count) {
            int j = k * 2;
            if (j < count && more(j, j + 1)) j++;
            if (!more(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public void insert(Key key) {
        data[++count] = key;
        swin(count);
    }

    public Key delMin() {
        Key key = data[1];
        exch(1, count);
        data[count--] = null;
        sink(1);
        return key;
    }

    public static void main(String[] args) {
        int[] nums = {3, 6, 2, 4, 5, 1};
        MinHeap<Integer> minHeap = new MinHeap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            minHeap.insert(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(minHeap.delMin());
        }
    }

}
