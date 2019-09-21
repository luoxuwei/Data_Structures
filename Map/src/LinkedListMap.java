import java.util.ArrayList;

public class LinkedListMap<K,V> implements Map<K, V> {

    private class Node {
        K key;
        V value;
        Node next;
        public Node(K k, V v, Node n) {
            key = k;
            value = v;
            next = n;
        }

        @Override
        public String toString(){
            return key.toString() + " : " + value.toString();
        }
    }

    private int size;
    private Node dummyHead;

    public LinkedListMap() {
        dummyHead = new Node(null, null, null);
        size = 0;
    }

    private Node getNode(K k) {
        return getNode(dummyHead.next, k);
    }

    private Node getNode(Node head, K k) {
        if (head == null)
            return null;
        if (head.key.equals(k))
            return head;
        return getNode(head.next, k);
    }

    @Override
    public void add(K k, V v) {
        Node node = getNode(k);
        if (node != null)
            node.value = v;
        else {
            dummyHead.next = new Node(k,v,dummyHead.next);
            size++;
        }
    }

    @Override
    public V remove(K k) {
        Node node = getNode(k);
        if (node != null) {
            dummyHead.next = remove(dummyHead.next, k);
            size--;
            return node.value;
        }
        return null;
    }

    private Node remove(Node head, K k) {
        if (head == null)
            return null;
        if (head.key.equals(k))
            return head.next;
         head.next = remove(head.next, k);
         return head;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K k) {
        Node node = getNode(k);
        return node == null? null:node.value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void set(K k, V v) {
        Node node = getNode(k);
        if (node != null)
            node.value = v;
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            LinkedListMap<String, Integer> map = new LinkedListMap<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}
