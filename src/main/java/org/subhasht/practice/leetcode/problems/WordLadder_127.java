package org.subhasht.practice.leetcode.problems;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;

import java.util.*;

public class WordLadder_127 {

    public static void main(String[] args) {
        WordLadder_127 solution = new WordLadder_127();
        List<String> wordList = toList("hot","dot","dog","lot","log","cog", "fog", "xxx", "hix", "xix", "mmy", "mny", "xny", "xxy", "xmm", "xmx");
        Assertions.assertEquals(5, solution.ladderLength("hit", "cog", wordList));
        Assertions.assertEquals(0, solution.ladderLength("hit", "yyy", wordList));
        Assertions.assertEquals(2, solution.ladderLength("hit", "hot", wordList));
        Assertions.assertEquals(5, solution.ladderLength("hit", "fog", wordList));
        Assertions.assertEquals(4, solution.ladderLength("mmm", "xxx", wordList));

        wordList = toList("b");
        Assertions.assertEquals(0, solution.ladderLength("a", "z", wordList));

        wordList = toList("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar",
                "ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn",
                "au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga",
                "li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc",
                "ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye");
        Assertions.assertEquals(5, solution.ladderLength("qa", "sq", wordList));

    }

    static List<String> toList(String... str) {
        List<String> list = new LinkedList<>();
        Collections.addAll(list, str);
        return list;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return sol3(beginWord, endWord, wordList);
    }


    public int sol3(String beginWord, String endWord, List<String> wordList) {
        System.out.println("beginWord: " + beginWord + " --> endWord: " + endWord);
        Map<String, Set<String>> nbrsMap = new HashMap<>();
        Map<String, List<String>> oneDiffMap = new HashMap<>();
        for(String str : wordList) {//O(N)
            Set<String> oneDiffs = getNDiffStrings(str, 1);
            nbrsMap.put(str, oneDiffs);
            for(String oneDiff : oneDiffs) {//O(str.length() - max 10)
                oneDiffMap.putIfAbsent(oneDiff, new ArrayList<>());
                oneDiffMap.get(oneDiff).add(str);
            }
        }

        //BFS
        int level = 1;
        int currentLevelCount = 1, nextLevelCount = 0;
        Set<String> visited = new HashSet<>();
        Deque<String> q = new LinkedList<>();
        q.add(beginWord);
        visited.add(beginWord);
        boolean found = false;
        outer: while(!q.isEmpty()) {
            nextLevelCount = 0;
            level++;
            for(int i = 0; i < currentLevelCount; i++) {
                String from = q.remove();
                System.out.println(level + " : " + from);
                Set<String> oneDiffs = nbrsMap.get(from);
                if(oneDiffs == null) {
                    oneDiffs = getNDiffStrings(from, 1);
                }
                for(String oneDiff : oneDiffs) {
                    if(oneDiffMap.containsKey(oneDiff)) {
                        for (String str : oneDiffMap.get(oneDiff)) {
                            System.out.println("1diff : " + oneDiff + ", str: " + str);
                            if (str.equals(endWord)) {
                                found = true;
                                break outer;
                            }
                            if (!visited.contains(str)) {
                                q.add(str);
                                visited.add(str);
                                nextLevelCount++;
                            }
                        }
                    }
                }
            }
            currentLevelCount = nextLevelCount;
        }

        return found ? level : 0;
    }

    //O(str.length()) = constant
    Set<String> getNDiffStrings(String str, int n) {
        Set<String> oneDiffs = new HashSet<>();
        for(int i = 0; i < str.length() - n + 1; i++) {
            oneDiffs.add(str.substring(0,i) + "_" + str.substring(i+1));
        }
        return oneDiffs;
    }

    //DFS - bad choice
    public int sol2(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> adjListMap = buildAdjListMap(wordList);
        Set<String> visited = new HashSet<>();
        int mn = Integer.MAX_VALUE, count;

        for (String s : wordList) {
            if (doWordsDifferByOne(beginWord, s)) {
                count = findHops(s, endWord, adjListMap, visited);
                mn = Math.min(mn, count);
            }
        }
        return mn;
    }

    int findHops(String from, String to, Map<String, List<String>> adjListMap, Set<String> visited) {
        if(from.equals(to)) {
            return 2;
        }
        visited.add(from);
        int mn = Integer.MAX_VALUE, count;
        if(adjListMap.containsKey(from)) {
            for (String adj : adjListMap.get(from)) {
                if (visited.contains(adj)) {
                    continue;
                }
                count = findHops(adj, to, adjListMap, visited);
                if (count != Integer.MAX_VALUE) {
                    count++;
                    mn = Math.min(mn, count);
                }
            }
        }
        visited.remove(from);
        return mn;
    }

    Map<String, List<String>> buildAdjListMap(List<String> wordList) {
        Map<String, List<String>> adjListMap = new HashMap<>();
        for(int i = 0; i < wordList.size(); i++) {
            for(int j = i+1; j < wordList.size(); j++) {
                if(doWordsDifferByOne(wordList.get(i), wordList.get(j))) {
                    adjListMap.putIfAbsent(wordList.get(i), new LinkedList<>());
                    adjListMap.get(wordList.get(i)).add(wordList.get(j));

                    adjListMap.putIfAbsent(wordList.get(j), new LinkedList<>());
                    adjListMap.get(wordList.get(j)).add(wordList.get(i));
                }
            }
        }
        return adjListMap;
    }





    //DFS, recursive - bad choice
    public int sol1(String beginWord, String endWord, List<String> wordList) {
        //System.out.println("Begin: " + beginWord + ", list: " + wordList);
        int mn = Integer.MAX_VALUE, count;
        for(int i = 0; i < wordList.size(); i++) {
            if(doWordsDifferByOne(beginWord, wordList.get(i))) {
                if(wordList.get(i).equals(endWord)) {
                    //System.out.println(">> 2");
                    return 2;
                }

                String temp = wordList.remove(i);
                count = sol1(temp, endWord, wordList);
                if(count != Integer.MAX_VALUE) {
                    //System.out.println("B: " + beginWord + " --> T: " + temp);
                    count ++;
                    mn = Math.min(mn, count);
                }
                wordList.add(i, temp);
                //System.out.println("::: " + wordList);
            }
        }
        //System.out.println(">> " + mn);
        return mn;
    }



    static boolean doWordsDifferByOne(String word1, String word2) {
        int diff = 0;
        for(int i = 0; i < word1.length(); i++) {
            if(word1.charAt(i) != word2.charAt(i)) {
                diff++;
                if(diff > 1) {
                    return false;
                }
            }
        }
        return diff == 1;
    }
}
