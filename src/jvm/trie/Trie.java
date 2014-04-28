package trie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Trie {
    int radix;
    Node root;

    public Trie(int radix) {
        this.radix = radix;
        root = new Node();
    }

    Collection<String> search(String w) {
        return search(root, w.toCharArray());
    }

    private static Collection<String> search(Node node, char[] key) {
        Node parent = node;
        for (int i = 0; i < key.length; i++) {
            final Node child = parent.children[radixIndex(key[i])];
            if (child == null) {
                return Collections.emptyList();
            } else {
                parent = child;
            }
        }
        return allwords(parent);
    }

    void insert(String w) {
        Node parent = root;
        final char[] key = w.toCharArray();

        for (int i = 0; i < key.length; i++) {
            Node child = parent.children[radixIndex(key[i])];
            if (child == null) {
                child = new Node();
                child.words.add(new String(key));
                
                parent.children[radixIndex(key[i])] = child; 
            } else {
                parent = child;
            }
        }
    }

    private static int radixIndex(char c) {
        return c - 'a';
    }

    class Node {
        Collection<String> words;
        Node[] children;

        public Node() {
            this.words = new LinkedList<String>();
            this.children = new Node[radix];
        }
        
        @Override
        public String toString() {
            return "Node: " + words; 
        }
    }


    public static void main(String[] args) throws IOException {
        final Trie t = new Trie(26);

        final BufferedReader br = new BufferedReader(new FileReader("/usr/share/dict/words"));
        try {
            String w = null;
            while ((w = br.readLine()) != null) {
                if (valid(w)) {
                    t.insert(w.toLowerCase());
                }
            }
        } finally {
            br.close();
        }
        
        System.out.println(t.search("home"));
    }

    private static boolean valid(String w) {
        for (int i = 0; i < w.length(); i++) {
            final char c = w.charAt(i);
            if (!validChar(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean validChar(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z');
    }
}
