public interface Queue<E> {
    boolean isEmpty();
    E getFront();
    E dequeue();
    void enqueue(E e);
    int getSize();
}
