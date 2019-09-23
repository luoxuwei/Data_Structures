public class HashTable<K, V> {
    private class Node<K, V> {
        K k;
        V v;
        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }

    private static final int INITIAL_CAPACITY = 16;

    private Node[] table;

    private int size = 0;

    private int threshold;

    private void setThreshold(int len) {
        threshold = len * 2 / 3;
    }

    private static int nextIndex(int i, int len) {
        return ((i + 1 < len) ? i + 1 : 0);
    }

    private static int prevIndex(int i, int len) {
        return ((i - 1 >= 0) ? i - 1 : len - 1);
    }

    public HashTable() {
        table = new Node[INITIAL_CAPACITY];
        size = 0;
        setThreshold(INITIAL_CAPACITY);
    }

    private int hash(int h) {
        return h & 0x7fffffff & (table.length -1);
    }

    public void add(K k, V v) {
        int i = hash(k.hashCode());

        for (Node cur = table[i]; cur != null; cur = table[i=nextIndex(i, table.length)]) {
            if (cur.k.equals(k)) {
                cur.v = v;
                return;
            }
        }

        table[i] = new Node(k,v);
        size++;
    }
    

}
