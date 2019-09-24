import java.util.*;

public class BST<E extends Comparable<E>> {

    private class Node {
        E e;
        Node left,right, parent;
        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }

        public E getValue() {
            return e;
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
        if (root == null) {
            root = new Node(e);
            size++;
            return;
        }
        add(root, e);
    }

    private void add(Node root, E e) {

        if (root.e.compareTo(e) < 0) {
            if (root.right == null) {
                root.right = new Node(e);
                root.right.parent = root;
                size++;
            } else {
                add(root.right, e);
            }
        } else if (root.e.compareTo(e) > 0) {
            if (root.left == null) {
                root.left = new Node(e);
                root.left.parent = root;
                size++;
            } else {
                add(root.left, e);
            }
        }
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
        Stack<Node> stack = new Stack<>();
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
        LinkedList<Node> queue = new LinkedList<>();
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

    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("size == 0");
        return minimum(root).e;
    }

     private Node minimum(Node root) {
        if (root.left == null) {
            return root;
        }
        return minimum(root.left);
    }

    public E maximum() {
        if (size == 0)
            throw new IllegalArgumentException("size == 0");
        return maximum(root).e;
    }

    private Node maximum(Node root) {
        if (root.right == null)
            return root;

        return maximum(root.right);
    }

    public E minimumNR() {
        if (size == 0)
            throw new IllegalArgumentException("size == 0");
        Node cur = root;
        while (cur.left != null)
            cur = cur.left;
        return cur.e;
    }

    public E maximumNR() {
        if (size == 0)
            throw new IllegalArgumentException("size == 0");
        Node cur = root;
        while (cur.right != null)
            cur = cur.right;
        return cur.e;
    }

    public E removeMinimum() {
        Node res = minimum(root);
        root = removeMinimum(root);
        return res.e;
    }

    public Node removeMinimum(Node root) {
        if (root.left == null) {
            Node right = root.right;
            root.right = null;
            size--;
            return right;
        }

        root.left = removeMinimum(root.left);
        return root;
    }

    public E removeMaximum() {
        E res = maximum();
        root = removeMaximum(root);
        return res;
    }

    public Node removeMaximum(Node root) {
        if (root.right == null) {
            Node left = root.left;
            root.left = null;
            size--;
            return left;
        }

        root.right = removeMaximum(root.right);
        return root;
    }

    public E removeMinimumNR() {
        E res = minimum();
        Node dummyRoot = new Node(null);
        dummyRoot.left = root;
        Node cur = dummyRoot;
        Node parent = dummyRoot;
        while (cur.left != null) {
            parent = cur;
            cur = cur.left;
        }

        parent.left = cur.right;
        cur.right = null;
        root = dummyRoot.left;
        size--;
        return res;
    }

    public E removeMaximumNR() {
        E res = maximum();
        Node dummyRoot = new Node(null);
        dummyRoot.right = root;
        Node cur = dummyRoot;
        Node parent = dummyRoot;
        while (cur.right != null) {
            parent = cur;
            cur = cur.right;
        }

        parent.right = cur.left;
        cur.left = null;
        root = dummyRoot.right;
        size--;
        return res;
    }

    public void remove(E e) {
        remove(root,e);
    }

    private Node remove(Node root, E e) {
        if (root.e.compareTo(e) == 0) {
            if (root.left == null) {
                size--;
                return root.right;
            }
            else if (root.right == null) {
                size--;
                return root.left;
            }
            else {
                Node next = minimum(root.right);
                next.right = removeMinimum(root.right);
                next.left = root.left;
                return next;
            }
        } else if (root.e.compareTo(e) < 0) {
            root.right = remove(root.right, e);
            return root;
        } else {
            root.left = remove(root.left, e);
            return root;
        }
    }

    public void removeNR(E e) {
        Node parent = root, cur = root;
        while (cur.e.compareTo(e) != 0) {
            parent = cur;
            if (cur.e.compareTo(e) < 0) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }

        Node next;

        if (cur.left == null) {
            next = cur.right;
        } else if (cur.right == null) {
            next = cur.left;
        }

        next = minimum(cur.right);
        next.right = removeMinimum(cur.right);
        next.left = cur.left;
        cur.left = null;
        cur.right = null;

        if (parent == root)
            root = next;
        else {
            if (cur == parent.left)
                parent.left = next;
            else if (cur == parent.right)
                parent.right = next;
        }
    }

    private Node succ(Node node) {
        if (node.right != null)
            return minimum(node.right);
        while (isRightChild(node))
            node = node.parent;
        return node.parent;
    }

    private boolean isRightChild(Node node) {
        if (node.parent != null)
            return node == node.parent.right;

        return false;
    }

    private boolean isLeftChild(Node node) {
        if (node.parent != null)
            return node == node.parent.left;
        return false;
    }

    public void inOrderNR() {
        Node cur = root;
        while (true) {
            if (cur.left != null) {
                cur = cur.left;
            } else {
                System.out.println(cur.e+",");
                while ((cur = succ(cur)) != null)
                    System.out.println(cur.e+",");
                return;
            }

        }
    }
}
