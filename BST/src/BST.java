public class BST<E extends Comparable<E>> {

    private class Node {
        E e;
        Node left,right;
        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node root, E e) {
        if (root == null) {
            size++;
            return new Node(e);
        }
        if (root.e.compareTo(e) < 0) {
            root.left = add(root.left, e);
        } else if (root.e.compareTo(e) > 0) {
            root.right = add(root.right, e);
        }
        return root;
    }
}
