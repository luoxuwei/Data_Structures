import java.util.ArrayList;
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
        if (word.length() == 0) {
            if (!root.isWord) {
                root.isWord = true;
                size++;
            }
            return;
        }
        if (root.next.get(word.charAt(0)) == null)
            root.next.put(word.charAt(0), new Node());
        add(word.substring(1), root.next.get(word.charAt(0)));
    }

    public boolean contains(String word) {
        Node cur = root;
        for (int i=0; i<word.length(); i++) {
            cur = cur.next.get(word.charAt(i));
            if (cur == null)
                return false;
        }
        return cur.isWord;
    }

    public boolean containsR(String word) {
        return containsR(word, root);
    }

    private boolean containsR(String word, Node root) {
        if (root == null)
            return false;
        if (word.length() == 0) {
            return root.isWord;
        }

        return containsR(word.substring(1), root.next.get(word.charAt(0)));
    }

    public int getSize() {
        return size;
    }


    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {

            Trie trie = new Trie();
            for (String word : words)
                trie.add(word);
            System.out.println("Total different words: " + trie.getSize());
            System.out.println("contains "+words.get(2)+": " + trie.contains(words.get(2)));

            System.out.println("-----------------");
            trie = new Trie();
            for (String word : words)
                trie.addR(word);
            System.out.println("Total different words: " + trie.getSize());
            System.out.println("contains "+words.get(2)+": " + trie.containsR(words.get(2)));
        }
    }

}
