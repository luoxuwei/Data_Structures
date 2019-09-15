public class Array<E> {
    private int size;
    private E[] data;
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public Array() {
        this(10);
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add fail index ivail");
        }

        if (size == data.length) {
            E[] newData = (E[]) new Object[2*data.length];
            for (int i=0; i<size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }

        for (int i=size; i>index; i--) {
            data[i] = data[i-1];
        }

        data[index] = e;
        size++;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    public E get(int index) {
        if (index<0 || index >= size) {
           throw new IllegalArgumentException("index out of bound");
        }
        return data[index];
    }

    public E removeFirst() {
       return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public E remove(int index) {
        if (index<0 || index >= size) {
            throw new IllegalArgumentException("index out of bound");
        }
        E res = data[index];
        for (int i=index; i<size-1; i++) {
            data[i] = data[i+1];
        }
        size--;
        if (size <= data.length/4 && data.length/4 > 0) {
            E[] newData = (E[]) new Object[data.length/4];
            for (int i=0; i<size; i++) {
               newData[i] = data[i];
            }
           data = newData;
        }
        return res;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return  size;
    }

    public int getCapacity() {
        return data.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i=0; i<size; i++) {
            sb.append(data[i]);
            if (i < size-1) {
                sb.append(",");
            }
        }
        sb.append("]");
        sb.append(" size="+size);
        sb.append(" capacity="+data.length);
        return sb.toString();
    }
}
