public class LinkedListQueue<E> implements Queue<E> {
    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public E getFront() {
        return head.e;
    }

    @Override
    public E dequeue() {
        if (head == null) {
            throw new IllegalArgumentException("");
        }

        Node res = head;
        head = head.next;
        //第一次漏掉了这个逻辑
        if (head == null)
            tail = null;
        res.next = null;
        size--;
        return res.e;
    }

    @Override
    public void enqueue(E e) {
        if (head == null) {
            tail = head = new Node(e);
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public int getSize() {
        return size;
    }

    private class Node {
        E e;
        Node next;
        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head, tail;
    private int size;

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue [ ");

        for (Node cur = head; cur != null; cur = cur.next)
            res.append(cur+", ");
        res.append(" ] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        // write your code here
        LinkedListQueue<Integer> stack = new LinkedListQueue<>();

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
