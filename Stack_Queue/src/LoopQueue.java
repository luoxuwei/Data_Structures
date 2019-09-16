public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front, tail;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public boolean isEmpty() {
        return tail == front;
    }

    @Override
    public E getFront() {
        return data[front];
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("queue is empty!");
        }
        E res = data[front];
        front = (front+1)%data.length;
        if (getSize()<=(data.length/4) && (data.length/2 > 0)) {
           reSize(data.length/2);
        }
        return res;
    }

    @Override
    public void enqueue(E e) {
        if ((tail+1)%data.length == front) {
            reSize(data.length*2);
        }
        data[tail] = e;
        tail = (tail+1)%data.length;
    }

    private void reSize(int capacity) {
        int size = getSize();
        E[] newData = (E[]) new Object[capacity];
        for (int i=0; i<size; i++) {
            newData[i] = data[(front+i)%data.length];
        }

        front = 0;
        tail = size;
        data = newData;
    }

    @Override
    public int getSize() {
        if (tail > front) {
            return tail - front;
        } else {
            return data.length - front + tail;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue [ ");
        for (int i=0; i<getSize(); i++) {
            res.append(data[(front+i)%data.length]);
            if ((tail - 1) != ((front+i)%data.length)) {
                res.append(", ");
            }
        }
        res.append(" ] tail ");
        res.append("size = "+getSize()+" capacity="+data.length);
        return res.toString();
    }

    public static void main(String[] args) {
        // write your code here
        LoopQueue<Integer> stack = new LoopQueue<>();

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
