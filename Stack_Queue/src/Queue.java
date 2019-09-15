public interface Queue<E> {
    boolean isEmpty();
    E getFont();
    E dequeue();
    void enqueue(E e);
    int getSize();
}
