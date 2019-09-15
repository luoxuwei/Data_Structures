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
    public E getFont() {
        return data[front];
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("queue is empty!");
        }
        E res = data[front];
        front = (front+1)%data.length;
        if (getSize()<=(data.length/4) && (data.length/4 > 0)) {
           reSize(data.length/4);
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
        E[] newData = (E[]) new Object[capacity];
        int tempTail=0;
        if (front<tail) {
            for (int i=front; i<tail; i++) {
                newData[i-front] = data[i];
                tempTail++;
            }
        } else {
            for (int i=front; i<data.length; i++) {
                newData[i-front] = data[front];
                tempTail++;
            }
            for (int i=0; i<tail; i++) {
                newData[data.length-front+i] = data[i];
                tempTail++;
            }
        }

        front = 0;
        tail = tempTail++;
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
        if (front<tail) {
            for (int i=front; i<tail; i++) {
                res.append(data[i]);
                if (i < tail-1) {
                    res.append(",");
                }
            }
        } else {
            for (int i=front; i<data.length; i++) {
                res.append(data[i]);
            }
            for (int i=0; i<tail; i++) {
                res.append(data[i]);
                if (i < tail-1) {
                    res.append(",");
                }
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
