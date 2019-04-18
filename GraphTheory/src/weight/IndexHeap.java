package weight;

public class IndexHeap<Key extends Comparable<Key>> {
    // 最小索引堆
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

    private boolean more(int i, int j) {
        return data[i].compareTo(data[j]) > 0;
    }

    private void exch(int i, int j) {
        Key tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    private void exchIndex(int i, int j) {
        int tmp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = tmp;
    }

    public void swin(int k) {
        while (k > 1 && more(indexes[k / 2], indexes[k])) {
//            exch(k / 2, k);
            exchIndex(k / 2, k);
            k = k / 2;
        }
    }

    public void sink(int k) {
        while (k * 2 <= count) {
            int j = k * 2;
            if (j < count && more(indexes[j], indexes[j + 1])) j++;
            if (!more(indexes[k], indexes[j])) break;
//            exch(indexes[k], indexes[j]);
            exchIndex(k, j);
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

    public Key delMin() {
        assert (count > 0);

        Key key = data[indexes[1]];
//        exch(indexes[1], indexes[count]);
        exchIndex(1, count);
        count--;
        sink(1);
        return key;
    }

    public void change(int i, Key newKey) {
        i += 1;
        data[i] = newKey;

        // 找到 indexes[j] = i, j表示data[i]在堆中的位置
        // 找到之后 swin(j)，再 sink(j)
        for (int j = 1; j <= count; j++) {
            if (indexes[j] == i) {
                swin(j); // 上移
                sink(j); // 下沉
                return;
            }
        }
    }

    public int delMinIndex() {
        assert (count > 0);

        int ret = indexes[1] - 1;
//        exch(indexes[1], indexes[count]);
        exchIndex(1, count);
        count--;
        sink(1);
        return ret;
    }

    public Key getKey(int i) {
        return data[i + 1];
    }

    private void sort(Comparable a[]) {

    }

    public static void main(String[] args) {
        int[] nums = {3, 6, 2, 4, 5, 1};
        IndexHeap<Integer> minHeap = new IndexHeap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            minHeap.insert(i, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(minHeap.delMin());
//            System.out.println(minHeap.delMinIndex());
        }
    }

}
