import java.util.ArrayList;
import java.util.List;

public class RBTree<K extends Comparable<K>, V> {

    private class Node {
        K k;
        V v;
        Node left;
        Node right;
        boolean isRed;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
            left = null;
            right = null;
            isRed = true;
        }
    }

    private int size;
    private Node root;

    public void add(K k, V v) {
        root = add(root, k, v);
        root.isRed = false;
    }

    private Node add(Node root, K k, V v) {
        if (root == null) {
            size++;
            return new Node(k, v);
        }
        if (root.k.compareTo(k) < 0) {
            root.right = add(root.right, k, v);
        } else if (root.k.compareTo(k) > 0) {
            root.left = add(root.left, k, v);
        } else {
            root.v = v;
            return root;
        }

        if (isRed(root.right) && !isRed(root.left))
           root = leftRotate(root);
        if (isRed(root.left) && isRed(root.left.left))
            root = rightRotate(root);
        if (isRed(root.left) && isRed(root.right))
            flipColor(root);

        return root;
    }

    private boolean isRed(Node node) {
        if (node == null)
            return false;
        return node.isRed;
    }

    public boolean isBST() {
        ArrayList<K> ks = new ArrayList<>();
        inOrder(root, ks);
        for (int i = 1; i < ks.size(); i++) {
            if (ks.get(i - 1).compareTo(ks.get(i)) > 0) {
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

    private Node rightRotate(Node node) {
       Node left = node.left;
       node.left = left.right;
       left.right = node;
       left.isRed = node.isRed;
       node.isRed = true;
       return left;
    }

    private Node leftRotate(Node node) {
        if (node == null || node.right == null)
            return node;

        Node right = node.right;
        Node rightLeft = right.left;
        right.left = node;
        node.right = rightLeft;
        right.isRed = node.isRed;
        node.isRed = true;
        return right;
    }


    private void flipColor(Node node) {
        node.left.isRed = false;
        node.right.isRed = false;
        node.isRed = true;
    }

    public V remove(K k) {
        Node node = getNode(k);
        root = remove(root, k);
        return node == null?null:node.v;
    }

    private Node remove(Node node, K e) {
        if (node == null)
            return node;
        Node retNode;
        if (node.k.compareTo(e) > 0) {
            node.left = remove(node.left, e);
            retNode = node;
        } else if (node.k.compareTo(e) < 0) {
            node.right = remove(node.right, e);
            retNode = node;
        } else {
            if (node.right == null) {
                retNode = node.left;
                size--;
            } else if (node.left == null) {
                retNode = node.right;
                size--;
            } else {
                Node next = minimum(node.right);
                node.right = remove(node.right, next.k);
                next.right = node.right;
                next.left = node.left;
                node.left = null;
                node.right = null;
                retNode = next;
            }
            if (retNode == null)
                return null;
        }
        return retNode;
    }

    private Node minimum(Node rootParent) {
        if (rootParent.left == null)
            return rootParent;
        return minimum(rootParent.left);
    }

    public boolean contains(K key) {
        return getNode(key) != null;
    }

    public V get(K k) {
        Node node = getNode(k);
        return node == null ? null : node.v;
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


    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void set(K k, V v) {
        Node node = getNode(k);
        if (node != null)
            node.v = v;
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            RBTree<String, Integer> map = new RBTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("is bst: " + map.isBST());

            for (String word:words) {
                map.remove(word);
                if (!map.isBST())
                    System.out.println("remove !bst");
            }

            System.out.println("after remove Total different words: " + map.getSize());
        }

        System.out.println();
    }
}
