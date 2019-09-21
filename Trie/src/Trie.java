import java.util.TreeMap;

public class Trie {
    private class Node {
        boolean isWord;
        TreeMap<Character, Node> next;
        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public void add(String word) {
        Node cur = root;

        for (int i=0; i<word.length(); i++) {
            if (cur.next.get(word.charAt(i)) == null) {
                cur.next.put(word.charAt(i), new Node());
            }
            cur = cur.next.get(word.charAt(i));
        }

        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    public void addR(String word) {
        add(word, root);
    }

    private void add(String word, Node root) {
        if (word.length() == 0 && !root.isWord) {
            root.isWord = true;
            size++;
            return;
        }
        if (root.next.get(word.charAt(0)) == null)
            root.next.put(word.charAt(0), new Node());
        add(word.substring(1), root.next.get(word.charAt(0)));
    }

}
