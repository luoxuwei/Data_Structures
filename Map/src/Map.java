public interface Map<K, V> {
    void add(K k, V v);
    V remove(K k);
    boolean contains(K key);
    V get(K k);
    int getSize();
    boolean isEmpty();
    void set(K k, V v);
}
