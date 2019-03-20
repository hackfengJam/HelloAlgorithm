public interface Stack<E> {

    // 获取栈大小
    int getSize();

    // 栈是否为空
    boolean isEmpty();

    // 向栈顶添加元素e
    void push(E e);

    // 弹出栈顶元素
    E pop();

    // 查看栈顶元素
    E peek();
}
