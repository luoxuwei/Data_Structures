import java.util.TreeMap;

public class HashTable<K, V> {
    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private static final int initCapacity = 7;

    private TreeMap<K,V>[] hashTable;
    private int M;
    private int size;

    public HashTable(int size) {
        hashTable = (TreeMap<K, V>[]) new Object[size];
        M = size;
        this.size = 0;
    }

    public HashTable() {
        this(initCapacity);
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

        if (size >= upperTol*M)
            resize(M*2);
    }

    public V remove(K k) {
        int hash = hash(k);
        TreeMap<K,V> map = hashTable[hash];
        if (map == null || !map.containsKey(k)) {
            return null;
        }
        size--;
        if (size <= lowerTol*M && M>=initCapacity*2)
            resize(M/2);
        return map.remove(k);
    }

    //哈希表的复杂度与哈希碰撞的元素的数量有关，平均的数量是n/m，在数组中找到对应哈希值的位置的复杂度是O1级别，所以主要是在treemap中操作的复杂度，是Oh级别
    //h=logn， n=n/m，所以哈希表的复杂度是logn/m，要优化，就需要随着元素的增加动态扩容数组，m变大，复杂度就降低了。
    private void resize(int m) {
        TreeMap<K,V>[] newTreemap = (TreeMap<K, V>[]) new Object[m];
        for(int i=0; i<m; i++)
            newTreemap[i] = new TreeMap<>();
        M = m;
        TreeMap<K,V> map = null, map1 = null;
        for (int i=0; i<hashTable.length; i++) {
            map = hashTable[i];
            if (map == null)
                continue;
            for (K k:map.keySet()) {
                map1 = newTreemap[hash(k)];
                if (map1 == null) {
                    map1 = new TreeMap<>();
                    newTreemap[hash(k)] = map1;
                }

                map1.put(k,map.get(k));
            }
        }
        hashTable = newTreemap;
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
