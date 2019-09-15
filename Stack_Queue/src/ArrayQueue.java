public class ArrayQueue<E> implements Queue<E> {
    private Array<E> data;
    public ArrayQueue() {
        data=new Array<>();
    }
    public ArrayQueue(int capacity) {
        data=new Array<>(capacity);
    }
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public E getFont() {
        return data.getFirst();
    }

    @Override
    public E dequeue() {
        return data.removeFirst();
    }

    @Override
    public void enqueue(E e) {
        data.addLast(e);
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
        res.append("Queue [ ");
        for (int i=0; i<data.getSize(); i++) {
            res.append(data.get(i));
            if (i < data.getSize() - 1) {
                res.append(",");
            }
        }
        res.append(" ] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        // write your code here
        ArrayQueue<Integer> stack = new ArrayQueue<>();

        for(int i = 0 ; i < 10 ; i ++){
            stack.enqueue(i);
            System.out.println(stack);
            if (i%3==2) {
                stack.dequeue();
                System.out.println(stack);
            }
        }

    }
}
