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

    private Node<K,V>[] table;

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

    private int hash(int h, int len) {
        return h & 0x7fffffff & (len -1);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(K k, V v) {
        int i = hash(k.hashCode(), table.length);

        for (Node cur = table[i]; cur != null; cur = table[i=nextIndex(i, table.length)]) {
            if (cur.k.equals(k)) {
                cur.v = v;
                return;
            }
        }

        table[i] = new Node(k,v);
        size++;
        if (size >= threshold)
           resize();
    }

    public V remove(K k) {
        int i=hash(k.hashCode(), table.length);

        for(Node<K,V> cur = table[i]; cur!=null; cur = table[i=nextIndex(i, table.length)]) {
            if (cur.k.equals(k)) {
                V ret = cur.v;
                cur.v = null;
                table[i] = null;
                size--;
                for (i = nextIndex(i, table.length);
                     (cur = table[i]) != null;
                     i = nextIndex(i, table.length)) {
                    int h = hash(cur.k.hashCode(), table.length);
                    if (h != i) {
                        table[i] = null;
                        while (table[h] != null)
                            h = nextIndex(h, table.length);
                        table[h] = cur;
                    }
                }
                return ret;
            }
        }
        return null;
    }

    public boolean contains(K k) {
        return getNode(k) != null;
    }

    public V get(K k) {
        Node<K,V> node = getNode(k);
        return node == null?null:node.v;
    }

    private Node getNode(K k) {
        int i=hash(k.hashCode(), table.length);

        for(Node<K,V> cur = table[i]; cur!=null; cur = table[i=nextIndex(i, table.length)]) {
            if (cur.k.equals(k)) {
                return cur;
            }
        }

        return null;
    }

    private void resize() {
        int oldLen = table.length;
        Node<K,V>[] newTable = new Node[2*oldLen];
        Node cur=null;

        for(int i=0; i<oldLen; i++) {
            cur = table[i];
            if (cur != null) {
                int h = hash(cur.k.hashCode(), newTable.length);
                while (newTable[h] != null)
                    h = nextIndex(h, newTable.length);
                newTable[h] = cur;
            }
        }
        table = newTable;
        setThreshold(newTable.length);
    }

    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>();

        String[] test = new String[]{"aaa", "aa", "a", "aa", "aa", "aaaa"};
        for (String s:test) {
            if (hashTable.contains(s)) {
                hashTable.add(s, hashTable.get(s)+1);
            } else {
                hashTable.add(s, 1);
            }
        }
        System.out.println("test add completed");
        for (String s:test) {
            System.out.println("test get "+s+"= "+hashTable.get(s));
        }
        for (String s:test) {
            hashTable.remove(s);
        }
        System.out.println("test remove completed size="+hashTable.getSize());
    }

}
