package org.subhasht.practice.leetcode.problems;

import org.junit.jupiter.api.Assertions;

import java.util.*;

public class ReconstructItinerary_332 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<String>> tickets = List.of(
                List.of("EZE","TIA"),
                List.of("EZE","HBA"),
                List.of("AXA","TIA"),
                List.of("JFK","AXA"),
                List.of("ANU","JFK"),
                List.of("ADL","ANU"),
                List.of("TIA","AUA"),
                List.of("ANU","AUA"),
                List.of("ADL","EZE"),
                List.of("ADL","EZE"),
                List.of("EZE","ADL"),
                List.of("AXA","EZE"),
                List.of("AUA","AXA"),
                List.of("JFK","AXA"),
                List.of("AXA","AUA"),
                List.of("AUA","ADL"),
                List.of("ANU","EZE"),
                List.of("TIA","ADL"),
                List.of("EZE","ANU"),
                List.of("AUA","ANU")
        );
        List<String> expected = List.of("JFK","AXA","AUA","ADL","ANU","AUA","ANU","EZE","ADL","EZE","ANU","JFK","AXA","EZE","TIA","AUA","AXA","TIA","ADL","EZE","HBA");
        Assertions.assertIterableEquals(expected, solution.findItinerary(tickets));
    }

    static class Solution {

        public List<String> findItinerary(List<List<String>> tickets) {
            Map<String, List<String>> fromMap = new HashMap<>();

            for(List<String> t : tickets) {
                fromMap.putIfAbsent(t.get(0), new LinkedList<>());
                fromMap.get(t.get(0)).add(t.get(1));
            }

            for(String f : fromMap.keySet()) {
                Collections.sort(fromMap.get(f));
            }

            Deque<String> ans = new LinkedList<>();

            process("JFK", fromMap, ans);

            return (List<String>) ans;
        }

        boolean process(String from, Map<String, List<String>> fromMap, Deque<String> ans) {
            //we reached here
            ans.add(from);
            //System.out.println("Added: " + from);

            if(fromMap.isEmpty()) return true;//all destinations visited

            if(fromMap.get(from) == null || fromMap.get(from).isEmpty()) {
                //System.out.println("Cannot go from here : " + from);
                ans.removeLast();
                return false;//all destinations not visited yet but you cannot go from here to other destinations
            }

            //let's go to next destination
            List<String> tos = fromMap.get(from);
            //System.out.println("Next available: " + tos);

            for(int i = 0; i < tos.size(); i++) {
                String nextDest = tos.remove(i);
                if(tos.isEmpty())
                    fromMap.remove(from);
                if(!process(nextDest, fromMap, ans)) {
                    //System.out.println("Adding back: " + nextDest);
                    tos.add(i, nextDest);
                    fromMap.putIfAbsent(from, tos);
                } else {
                    return true;
                }
            }
            //System.out.println("Cannot reach till end from here, bad choice : " + from);
            ans.removeLast();
            return false;
        }
    }
}
