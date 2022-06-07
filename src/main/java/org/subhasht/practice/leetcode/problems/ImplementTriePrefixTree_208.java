package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

/*
A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.


Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True


Constraints:

1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters.
At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 */
public class ImplementTriePrefixTree_208 {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("subhash");
        trie.insert("subject");
        trie.insert("todkari");
        Assertions.assertTrue(trie.search("subhash"));
        Assertions.assertTrue(trie.search("subject"));
        Assertions.assertTrue(trie.search("todkari"));
        Assertions.assertFalse(trie.search("subordinate"));
        Assertions.assertTrue(trie.startsWith("subhash"));
        Assertions.assertTrue(trie.startsWith("sub"));
        Assertions.assertTrue(trie.startsWith("t"));
        Assertions.assertFalse(trie.startsWith("asubhash"));
    }


    static class Trie {

        static class Node {
            char c;
            Map<Character, Node> map;
            boolean word;

            public Node(char ch) {
                c = ch;
                map = new HashMap<>();
            }

            void add(char [] word, int i) {
                if(!map.containsKey(word[i])) {
                    map.put(word[i], new Node(word[i]));
                }
                if(i == word.length-1)
                    map.get(word[i]).word = true;
                else
                    map.get(word[i]).add(word, i+1);
            }
        }

        Node root;

        public Trie() {
            root = new Node('#');
        }

        public void insert(String word) {
            root.add(word.toCharArray(), 0);
        }

        public boolean search(String word) {
            Node node = root;
            for(char c : word.toCharArray()) {
                node = node.map.get(c);
                if(node == null)
                    return false;
            }
            return node.word;
        }

        public boolean startsWith(String prefix) {
            Node node = root;
            for(char c : prefix.toCharArray()) {
                node = node.map.get(c);
                if(node == null)
                    return false;
            }
            return true;
        }
    }
}
