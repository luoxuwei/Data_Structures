public class LinkedList<E> {
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

    private Node head;
    private Node dummyHead;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
        dummyHead = new Node(null);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("");
        }
        Node prv = dummyHead;
        int pos = 0;
        while (pos < index) {
            prv = prv.next;
            pos++;
        }
        prv.next = new Node(e, prv.next);
        size++;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        if (index < 0 || index >=size) {
            throw new IllegalArgumentException("");
        }
        Node cur = dummyHead.next;
        for (int i=0; i<index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index) {
        Node prv = dummyHead;
        int i = 0;
        while (i<index) {
            prv = prv.next;
            i++;
        }
        Node cur = prv.next;
        prv.next = cur.next;
        cur.next = null;
        size--;
        return cur.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Node cur = dummyHead.next; cur != null; cur = cur.next)
            res.append(cur.e).append("-->");
        res.append("null");
        return res.toString();
    }
}
