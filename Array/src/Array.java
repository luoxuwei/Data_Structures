public class Array {
    private int size;
    private int[] data;
    public Array(int capacity) {
        data = new int[capacity];
    }

    public Array() {
        this(10);
    }

    public void add(int val) {
        if (size == data.length) {
           int[] newData = new int[2*data.length];
           for (int i=0; i<size; i++) {
               newData[i] = data[i];
           }
           data = newData;
        }
        data[size] = val;
        size++;
    }

    public int get(int index) {
        if (index<0 || index >= size) {
           throw new IllegalArgumentException("index out of bound");
        }
        return data[index];
    }

    public int removeFirst() {
       return remove(0);
    }

    public int remove(int index) {
        if (index<0 || index >= size) {
            throw new IllegalArgumentException("index out of bound");
        }
        int res = data[index];
        for (int i=index; i<size-1; i++) {
            data[i] = data[i+1];
        }
        size--;
        if (size <= data.length/4 && data.length/4 > 0) {
            int[] newData = new int[data.length/4];
            for (int i=0; i<size; i++) {
               newData[i] = data[i];
            }
           data = newData;
        }
        return res;
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
