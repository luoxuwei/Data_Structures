public class ArrayStack<E> implements IStack<E> {
    private Array<E> data;

    public ArrayStack(int capacity) {
        data = new Array(capacity);
    }

    public ArrayStack() {
        this(10);
    }


    @Override
    public void push(E e) {
        data.addLast(e);
    }

    @Override
    public E pop() {
        return data.removeLast();
    }

    @Override
    public E peek() {
        return data.getLast();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    public int getCapacity() {
        return data.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack [ ");

        for (int i=0; i<data.getSize(); i++) {
            res.append(data.get(i));
            if (i<data.getSize()-1) {
                res.append(",");
            }
        }
        res.append("] top");
        return res.toString();
    }
}
