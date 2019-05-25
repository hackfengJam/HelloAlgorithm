public class IndexHeapReverse<Key extends Comparable<Key>> {
    // 最大索引堆
    /*
     * indexes[i] = j
     * reverse[j] = i
     *
     * indexes[reverse[i]] = i
     * reverse[indexes[i]] = i
     *
     * */
    private Key[] data;
    private int capacity;
    int[] indexes;
    int[] reverse;
    private int count;

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public IndexHeapReverse(int capacity) {
        data = (Key[]) new Comparable[capacity + 1];
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];
        for (int i = 0; i <= capacity; i++) {
            // 从 1 开始，因此 0 为非法索引
            reverse[i] = 0;
        }

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

    private void exchIndex(int i, int j) {
        int tmp = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = tmp;

        /*// 此时 indexes i和j 已经发生了交换，所以只需要如下编写即可
        reverse[indexes[i]] = i;
        reverse[indexes[j]] = j;*/
    }

    public void swin(int k) {
        // 上浮
        while (k > 1 && less(indexes[k / 2], indexes[k])) {
//            exch(k / 2, k);
            exchIndex(k / 2, k);

            // reverse 维护
            // 此时 indexes k和k/2 已经发生了 改变，所以只需要如下编写即可
            reverse[indexes[k / 2]] = k / 2;
            reverse[indexes[k]] = k;


            k = k / 2;
        }
    }

    public void sink(int k) {
        // 下沉
        while (k * 2 <= count) {
            int j = k * 2;
            if (j < count && less(indexes[j], indexes[j + 1])) j++;
            if (!less(indexes[k], indexes[j])) break;
//            exch(indexes[k], indexes[j]);
            exchIndex(k, j);

            // reverse 维护
            reverse[indexes[k]] = k;
            reverse[indexes[j]] = j;


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

        // reverse 维护
        reverse[i] = count + 1;

        count++;
        swin(count);
    }

    public Key delMax() {
        assert (count > 0);

        Key key = data[indexes[1]];
//        exch(indexes[1], indexes[count]);
        exchIndex(1, count);

        // reverse 维护
        reverse[indexes[1]] = 1;
        reverse[indexes[count]] = 0; // 因为是删除操作

        count--;
        sink(1);
        return key;
    }

    public int delMaxIndex() {
        assert (count > 0);

        int ret = indexes[1] - 1;
//        exch(indexes[1], indexes[count]);
        exchIndex(1, count);

        // reverse 维护
        reverse[indexes[1]] = 1;
        reverse[indexes[count]] = 0; // 因为是删除操作


        count--;
        sink(1);
        return ret;
    }

    // 从 1 开始，因此 0 为非法索引
    private boolean contain(int i) {
        if (!(i + 1 >= 1 && i + 1 <= capacity))
            throw new RuntimeException("Error");

        return reverse[i + 1] != 0;
    }

    public Key getKey(int i) {

        // assert( contain(i) )
        if (!contain(i))
            throw new RuntimeException("Error");

        return data[i + 1];
    }

    public void change(int i, Key newKey) {

        // assert( contain(i) )
        if (!contain(i))
            throw new RuntimeException("Error");

        i += 1;
        data[i] = newKey;

        // 找到 indexes[j] = i, j表示data[i]在堆中的位置
        // 找到之后 swin(j)，再 sink(j)
//        for (int j = 1; j <= count; j++) {
//            if (indexes[j] == i) {
//                swin(j); // 上移
//                sink(j); // 下沉
//                return;
//            }
//        }

        int j = reverse[i];
        swin(j);
        sink(j);

    }

    private void sort(Comparable a[]) {

    }

    public static void main(String[] args) {
        int[] nums = {3, 6, 2, 4, 5, 1};
        IndexHeapReverse<Integer> maxHeap = new IndexHeapReverse<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            maxHeap.insert(i, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(maxHeap.delMax());
//            System.out.println(maxHeap.delMaxIndex());
        }
    }

}
