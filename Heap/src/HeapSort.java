public class HeapSort<Key extends Comparable<Key>> {

    private boolean less(Key[] data, int i, int j) {
        return data[i].compareTo(data[j]) < 0;
    }

    private void exch(Key[] data, int i, int j) {
        Key tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public void swin(Key[] data, int k) {
        while (k > 1 && less(data, k / 2, k)) {
            exch(data, k / 2, k);
            k = k / 2;
        }
    }

    public void sink(Key[] data, int k, int count) {
        while (k * 2 + 1 < count) {
            int j = k * 2 + 1;
            if (j + 1 < count && less(data, j, j + 1)) j++;
            if (!less(data, k, j)) break;
            exch(data, k, j);
            k = j;
        }
    }


    private void sort(Key[] data) {
        // 建堆
        for (int i = (data.length - 1 - 1) / 2; i >= 0; i--) {
            sink(data, i, data.length);
        }

        // 堆排序
        for (int i = data.length - 1; i >= 0; i--) {
            exch(data, i, 0);
            sink(data, 0, i);
        }
    }

    public static void main(String[] args) {
        Integer[] nums = {7, 4, 5, 6, 3, 8};
        HeapSort<Integer> heapSort = new HeapSort<>();
        heapSort.sort(nums);
        for (int i = 0; i < nums.length; i++)
            System.out.print(nums[i] + " ");
    }

}
