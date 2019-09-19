import java.util.*;

public class BST<E extends Comparable<E>> {

    private class Node<E extends Comparable<E>> {
        E e;
        Node left,right;
        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return e.toString();
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
            root.right = add(root.right, e);
        } else if (root.e.compareTo(e) > 0) {
            root.left = add(root.left, e);
        }
        return root;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node root, E e) {
        if (root == null) {
            return false;
        }
        if (root.e.compareTo(e) > 0) {
            return contains(root.left, e);
        } else if (root.e.compareTo(e) < 0) {
            return contains(root.right, e);
        } else {
            return true;
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node root) {
        if (root == null)
            return;

        System.out.println(root.e);
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);
        System.out.println(root.e);
        inOrder(root.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node root) {
        if (root == null)
            return;

        postOrder(root);
        postOrder(root);
        System.out.println(root.e);
    }

    public void preOrder1() {
        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);
        Node cur = null;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    public void levelOrder() {
        LinkedList<Node<E>> queue = new LinkedList<>();
        queue.addLast(root);
        Node cur = null;
        while (!queue.isEmpty()) {
            cur = queue.removeFirst();
            System.out.println(cur.e);
            if (cur.left != null)
                queue.addLast(cur.left);
            if (cur.right != null)
                queue.addLast(cur.right);
        }
    }

    public Node minimum() {
        if (size == 0)
            throw new IllegalArgumentException("size == 0");
        return minimum(root);
    }

     private Node minimum(Node root) {
        if (root.left == null) {
            return root;
        }
        return minimum(root.left);
    }

    public Node maximum() {
        if (size == 0)
            throw new IllegalArgumentException("size == 0");
        return maximum(root);
    }

    private Node maximum(Node root) {
        if (root.right == null)
            return root;

        return maximum(root.right);
    }

    public Node minimumNR() {
        if (size == 0)
            throw new IllegalArgumentException("size == 0");
        Node cur = root;
        while (cur.left != null)
            cur = cur.left;
        return cur;
    }

    public Node maximumNR() {
        if (size == 0)
            throw new IllegalArgumentException("size == 0");
        Node cur = root;
        while (cur.right != null)
            cur = cur.right;
        return cur;
    }
}
