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
        if (root.k.compareTo(k) < 0) {
            root.right = add(root.right, k, v);
        } else if (root.k.compareTo(k) > 0) {
            root.left = add(root.left, k, v);
        } else {
            root.v = v;
            return root;
        }
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
        if (getBlanceFactor(root) > 1 && getBlanceFactor(root.left) >= 0)
            return rightRotate(root);

        if (getBlanceFactor(root) < -1 && getBlanceFactor(root.right) <= 0)
            return leftRotate(root);

        if (getBlanceFactor(root) > 1 && getBlanceFactor(root.left) < 0)
            return lrRotate(root);

        if (getBlanceFactor(root) < -1 && getBlanceFactor(root.right) > 0)
            return rlRotate(root);

        return root;
    }

    public boolean isBalance() {
        return isBalance(root);
    }

    private boolean isBalance(Node node) {
        if (node == null)
            return true;

        if (Math.abs(getBlanceFactor(node)) > 1)
            return false;

        return isBalance(node.left) && isBalance(node.right);
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
        if (node == null || node.left == null)
            return node;
        Node left = node.left;
        Node leftRigt = left.right;
        left.right = node;
        node.left = leftRigt;

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        left.height = Math.max(getHeight(left.left), getHeight(left.right)) + 1;

        return left;
    }

    private Node leftRotate(Node node) {
        if (node == null || node.right == null)
            return node;

        Node right = node.right;
        Node rightLeft = right.left;
        right.left = node;
        node.right = rightLeft;
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        right.height = Math.max(getHeight(right.left), getHeight(right.right)) + 1;
        return right;
    }

    private Node lrRotate(Node node) {
        if (node == null || node.left == null)
            return node;
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    private Node rlRotate(Node node) {
        if (node == null || node.right == null)
            return node;
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    @Override
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
        }
        if (retNode == null)
            return null;
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        if (getBlanceFactor(retNode) > 1 && getBlanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);

        if (getBlanceFactor(retNode) < -1 && getBlanceFactor(retNode.right) <= 0)
            return leftRotate(retNode);

        if (getBlanceFactor(retNode) > 1 && getBlanceFactor(retNode.left) < 0)
            return lrRotate(retNode);

        if (getBlanceFactor(retNode) < -1 && getBlanceFactor(retNode.right) > 0)
            return rlRotate(retNode);
        return retNode;
    }

    private Node minimum(Node rootParent) {
        if (rootParent.left == null)
            return rootParent;
        return minimum(rootParent.left);
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
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

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
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

            for (String word:words) {
                map.remove(word);
                if (!map.isBST())
                    System.out.println("remove !bst");
                if (!map.isBalance())
                    System.out.println("remove !balance");
            }

            System.out.println("after remove Total different words: " + map.getSize());
        }

        System.out.println();
    }
}
