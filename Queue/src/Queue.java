public interface Queue<E> {
    // 获取队列大小
    int getSize();

    // 队列是否为空
    boolean isEmpty();

    // 元素e入队
    void enqueue(E e);

    // 队首元素出队
    E dequeue();

    // 查看队首元素
    E getFront();
}
