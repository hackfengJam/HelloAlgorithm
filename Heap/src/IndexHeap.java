public class IndexHeap<Key extends Comparable<Key>> {
    private Key[] data;
    private int capacity;
    int[] indexes;
    private int count;

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public IndexHeap(int capacity) {
        data = (Key[]) new Comparable[capacity + 1];
        indexes = new int[capacity + 1];
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
        while (k > 1 && less(indexes[k / 2], indexes[k])) {
            exch(indexes[k / 2], indexes[k]);
            k = k / 2;
        }
    }

    public void sink(int k) {
        while (k * 2 <= count) {
            int j = k * 2;
            if (j < count && less(indexes[j], indexes[j + 1])) j++;
            if (!less(indexes[k], indexes[j])) break;
            exch(indexes[k], indexes[j]);
            k = j;
        }
    }

    // 传入的i对用户而言，是从0索引的
    public void insert(int i, Key key) {
        assert (count + 1 <= capacity);
        assert (i + 1 >= 1 && i + 1 <= capacity);

        i += 1;
        data[i] = key;
        indexes[count + 1] = i;

        count++;
        swin(count);
    }

    public Key delMax() {
        assert (count > 0);

        Key key = data[indexes[1]];
        exch(indexes[1], indexes[count]);
        data[count--] = null;
        sink(1);
        return key;
    }

    public int delMaxIndex() {
        assert (count > 0);

        int ret = indexes[1] - 1;
        exch(indexes[1], indexes[count]);
        data[count--] = null;
        sink(1);
        return ret;
    }

    public Key getKey(int i) {
        return data[i + 1];
    }

    private void sort(Comparable a[]) {

    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 5, 6};
        IndexHeap<Integer> maxHeap = new IndexHeap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            maxHeap.insert(i, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(maxHeap.delMax());
        }
    }

}
