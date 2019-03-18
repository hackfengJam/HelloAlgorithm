public class Array<E> {

    private E[] data;
    private int size;

    /**
     * 构造函数，传入数组的容量 capacity 构造 Array
     *
     * @param capacity
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity]; // new E[capacity] 不支持该语法，历史遗留问题
        size = 0;
    }

    // 无参数的构造函数，默认数组的容量 capacity=10
    public Array() {
        this(10);
    }

    // 获取数组中的元素个数
    public int getSize() {
        return size;
    }

    // 获取数组的容量
    public int getCapacity() {
        return data.length;
    }

    // 返回数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 向所有元素后添加一个新元素
    public void addLast(E e) {
        /*if (size == data.length) {
            throw new IllegalArgumentException("AddLast failed. Array is full.");
        }

        data[size] = e;
        size++;*/
        add(size, e);
    }

    // 在所有元素前添加一个新元素
    public void addFirst(E e) {
        add(0, e);
    }

    // 在第 index 个位置插入一个新元素 e
    public void add(int index, E e) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed. Require index >=0 and index < size.");
        }
        if (size == data.length) {
            resize(2 * data.length);
        }


        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    //获取 index 索引位置的元素
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    // 修改 index 索引位置的元素
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }
        data[index] = e;
    }

    // 查找数组中是否有元素 e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    // 查找数组中元素 e 所在的索引，如果不存在元素 e，则返回 -1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    // 从数组中删除 index 位置的元素，返回删除的元素
    public E remove(int index) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("remove failed. Index is illegal.");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;

        // 该行可选
        data[size] = null; // loitering objects != memory leak

        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return ret;
    }


    // 从数组中删除第一个元素，返回删除的元素
    public E removeFirst(int index) {

        return remove(0);
    }

    // 从数组中删除最后元素，返回删除的元素
    public E removeLast(int index) {

        return remove(size - 1);
    }

    // 从数组中删除元素e
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }


    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
