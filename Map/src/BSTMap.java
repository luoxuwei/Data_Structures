import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        K k;
        V v;
        Node left;
        Node right;
        public Node(K k, V v) {
            this.k = k;
            this.v = v;
            left = null;
            right = null;
        }
    }

    private int size;
    private Node root;

    @Override
    public void add(K k, V v) {
        if (root == null) {
            root = new Node(k, v);
            return;
        }
        Node parent = root;
        Node cur = root;
        while (cur != null && cur.k.compareTo(k) != 0) {
            parent = cur;
            if (cur.k.compareTo(k) < 0)
                cur = cur.right;
            else if (cur.k.compareTo(k) > 0)
                cur = cur.left;
        }

        if (cur != null) {
            cur.v = v;
            return;
        }
        size++;

        Node node = new Node(k, v);
        if (parent.k.compareTo(k) > 0) {
            parent.left = node;
        } else {
            parent.right = node;
        }
    }

    @Override
    public V remove(K k) {
        if (size == 0) {
            return null;
        }
        Node parent = root;
        Node cur = root;
        while (cur != null && cur.k.compareTo(k) != 0) {
            parent = cur;
            if (cur.k.compareTo(k) < 0)
                cur = cur.right;
            else if (cur.k.compareTo(k) > 0)
                cur = cur.left;
        }
        Node next = null;
        if (cur != null) {
            if (cur == root) {
                size--;
                root = null;
                return root.v;
            }
            if (cur.left == null) {
                next = cur.right;
            } else if (cur.right == null) {
                next = cur.left;
            } else {
                next = removeRightMiximum(cur);
            }
            if (next != null) {
                next.right = cur.right;
                next.left = cur.left;
            }

            if (parent.k.compareTo(k) > 0) {
                parent.left = next;
            } else {
                parent.right = next;
            }
            size--;
            return cur.v;
        }

        return null;
    }

    private Node removeRightMiximum(Node rootParent) {
        Node dummyRoot = new Node(null, null);
        dummyRoot.left = rootParent.right;
        Node parent = dummyRoot;
        Node cur = dummyRoot;
        while (cur.left != null) {
            parent = cur;
            cur = cur.left;
        }
        if (parent == rootParent) {
            parent.right = cur.right;
        } else {
            parent.left = cur.right;
        }

        return cur;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K k) {
        Node node = getNode(k);
        return node == null?null:node.v;
    }

    private Node getNode(K k) {
        Node cur = root;
        while (cur != null && cur.k.compareTo(k) != 0) {
            if (cur.k.compareTo(k) < 0)
                cur = cur.right;
            else if (cur.k.compareTo(k) > 0)
                cur = cur.left;
        }
        return cur;
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
            node.v = v;
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
