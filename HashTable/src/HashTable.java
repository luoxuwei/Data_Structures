import java.util.TreeMap;

public class HashTable<K, V> {
    private TreeMap<K,V>[] hashTable;
    private int M;
    private int size;

    public HashTable(int size) {
        hashTable = (TreeMap<K, V>[]) new Object[size];
        M = size;
        this.size = 0;
    }

    public HashTable() {
        this(97);
    }

    private int hash(K k) {
        return k.hashCode() & 0x7fffffff % M;
    }

    public void add(K k, V v) {
        int hash = hash(k);
        TreeMap<K,V> map = hashTable[hash];
        if (map == null) {
            map = new TreeMap<>();
            hashTable[hash] = map;
        }

        if (map.containsKey(k))
            map.put(k,v);
        else {
            map.put(k,v);
            size++;
        }
    }

    public V remove(K k) {
        int hash = hash(k);
        TreeMap<K,V> map = hashTable[hash];
        if (map == null || !map.containsKey(k)) {
            return null;
        }
        size--;
        return map.remove(k);
    }

    public void set(K k, V v) {
        int hash = hash(k);
        TreeMap<K,V> map = hashTable[hash];
        if (map == null || !map.containsKey(k)) {
           throw new IllegalArgumentException("");
        }
        map.put(k,v);
    }

    public V get(K k) {
        int hash = hash(k);
        TreeMap<K,V> map = hashTable[hash];
        if (map == null || !map.containsKey(k)) {
            return null;
        }
        return map.get(k);
    }

    public boolean contains(K k) {
        int hash = hash(k);
        TreeMap<K,V> map = hashTable[hash];
        if (map == null || !map.containsKey(k)) {
            return false;
        }
        return true;
    }
}
