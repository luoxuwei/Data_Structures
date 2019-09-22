import java.util.ArrayList;
import java.util.List;

public class AVLTree<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        K k;
        V v;
        Node left;
        Node right;
        int height;
        public Node(K k, V v) {
            this.k = k;
            this.v = v;
            left = null;
            right = null;
            height = 1;
        }
    }

    private int size;
    private Node root;

    private int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int getBlanceFactor(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    @Override
    public void add(K k, V v) {
        root = add(root, k, v);
    }

    private Node add(Node root, K k, V v) {
        if (root == null) {
            size++;
            return new Node(k, v);
        }
        if (root.k.compareTo(k)<0) {
            root.right = add(root.right, k, v);
        } else if (root.k.compareTo(k)>0) {
            root.left = add(root.left, k,v);
        } else {
            root.v = v;
        }
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        if (Math.abs(getBlanceFactor(root)) > 1)
            System.out.println("unbalanced "+getBlanceFactor(root));

        return root;
    }

    public boolean isBalance() {
        return isBalance(root);
    }

    private boolean isBalance(Node node) {
        if (node == null)
            return true;

        if (Math.abs(getBlanceFactor(node))>1)
            return false;

        return isBalance(node.left) && isBalance(node.right);
    }

    public boolean isBST() {
        ArrayList<K> ks = new ArrayList<>();
        inOrder(root, ks);
        for (int i=1; i<ks.size(); i++) {
            if (ks.get(i-1).compareTo(ks.get(i))>0) {
                return false;
            }
        }

        return true;
    }

    private void inOrder(Node root, List<K> ks) {
        if (root == null)
            return;
        inOrder(root.left, ks);
        ks.add(root.k);
        inOrder(root.right, ks);
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

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
            System.out.println("is balance: " + map.isBalance());
            System.out.println("is bst: " + map.isBST());
        }

        System.out.println();
    }
}
