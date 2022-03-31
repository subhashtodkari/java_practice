package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.*;

public class SortFeaturesByPopularity_1772 {

    public static void main(String[] args) {
        SortFeaturesByPopularity_1772 solution = new SortFeaturesByPopularity_1772();
        String [] features = {"cooler","lock","touch", "locks"};
        String [] responses = {"i like cooler cooler","lock touch cool","locker like touch"};
        String [] expected = {"touch","cooler","lock", "locks"};
        Assertions.assertArrayEquals(expected, solution.sol2(features, responses));

        features = new String [] {"a","aa","b","c"};
        responses = new String [] {"a","a aa","a a a a a","b a"};
        expected = new String [] {"a","aa","b","c"};
        Assertions.assertArrayEquals(expected, solution.sol2(features, responses));
    }

    public String[] sortFeatures(String[] features, String[] responses) {
        return sol2(features, responses);
    }


    static class TrieNode {
        Character c;
        boolean isWord;
        int wordIdx;
        TrieNode [] next;

        public TrieNode(Character c) {
            this.c = c;
            this.wordIdx = -1;
            this.next = new TrieNode[26];
        }
    }

    static class Trie {
        TrieNode [] root = new TrieNode[26];

        void addWords(String [] words) {
            for (int i = 0; i < words.length; i++) {
                addWord(words[i], i);
            }
        }

        void addWord(String word, int wordIdx) {
            TrieNode [] trav = root;
            TrieNode node = null;
            int charIdx;
            for(int i = 0; i < word.length(); i++) {
                charIdx = word.charAt(i) - 'a';
                node = trav[charIdx];
                if(node == null) {
                    node = new TrieNode(word.charAt(i));
                    trav[charIdx] = node;
                }
                trav = node.next;
            }
            assert node != null;
            node.isWord = true;
            node.wordIdx = wordIdx;
        }

        TrieNode current = null;
        boolean findNext(char c) {
            if(current == null) {
                current = root[c - 'a'];
            } else {
                current = current.next[c - 'a'];
            }
            return current != null;
        }

        void resetSearch() {
            current = null;
        }
    }

    public String[] sol2(String[] features, String[] responses) {
        Trie trie = new Trie();
        //trie.addWords(features);
        final Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < features.length; i++) {
            indexMap.put(features[i], i);
            trie.addWord(features[i], i);
        }

        final int [] countMap = new int [features.length];
        final int [] lastRespIdx = new int [features.length];

        Arrays.fill(lastRespIdx, -1);

        int j , lastMatchedIdx;
        for(int i = 0; i <responses.length; i++) {
            j = 0;
            while(j < responses[i].length()) {
                lastMatchedIdx = j-1;
                while(j < responses[i].length() && responses[i].charAt(j) != ' ') {
                    if(lastMatchedIdx == j-1 && trie.findNext(responses[i].charAt(j))) {
                        lastMatchedIdx = j;
                    }
                    j++;
                }

                if(j == lastMatchedIdx + 1) {
                    if(trie.current.isWord && lastRespIdx[trie.current.wordIdx] != i) {
                        countMap[trie.current.wordIdx]++;
                        lastRespIdx[trie.current.wordIdx] = i;
                    }
                }
                j++;
                trie.resetSearch();
            }
        }

        Arrays.sort(features, (f1, f2) -> {
            int cd =  Integer.compare(countMap[indexMap.get(f2)], countMap[indexMap.get(f1)]);
            if(cd == 0)
                return Integer.compare(indexMap.get(f1), indexMap.get(f2));
            return cd;
        });
        return features;
    }

    public String[] sol1(String[] features, String[] responses) {
        final Map<String, Integer> countMap = new HashMap<>();
        final Map<String, Integer> indexMap = new HashMap<>();
        int i = 0;
        for(String s : features) {//O(m)
            countMap.put(s, 0);//O(1)
            indexMap.put(s, i++);//O(1)
        }

        for(String r : responses) {//O(n)
            Set<String> tokens = new HashSet<>(Arrays.asList(r.split(" ")));
            for(String t : tokens) {
                if(countMap.containsKey(t))
                    countMap.put(t, countMap.get(t)+1);
            }
        }

        Arrays.sort(features, (f1, f2) -> {//O(n Log n)
            int cd =  Integer.compare(countMap.get(f2), countMap.get(f1));
            if(cd == 0)
                return Integer.compare(indexMap.get(f1), indexMap.get(f2));
            return cd;
        });
        return features;
    }
}
