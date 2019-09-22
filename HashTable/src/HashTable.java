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
}
