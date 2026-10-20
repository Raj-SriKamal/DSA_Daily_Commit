
package Graph;

import java.util.*;

public class AlienDictionary {

    public String alienOrder(String[] words) {
        int ALPHABET = 26;

        List<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < ALPHABET; i++) {
            graph.add(new HashSet<>());
        }

        int[] indegree = new int[ALPHABET];
        boolean[] present = new boolean[ALPHABET];
        int uniqueLetters = 0;

        // Step 1: Record all letters that appear.
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                int letter = ch - 'a';

                if (!present[letter]) {
                    present[letter] = true;
                    uniqueLetters++;
                }
            }
        }

        // Step 2: Build ordering constraints.
        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];

            int len = Math.min(first.length(), second.length());
            int j = 0;

            while (j < len && first.charAt(j) == second.charAt(j)) {
                j++;
            }

            // Invalid prefix ordering: "abc" before "ab".
            if (j == len) {
                if (first.length() > second.length()) {
                    return "";
                }
                continue;
            }

            int u = first.charAt(j) - 'a';
            int v = second.charAt(j) - 'a';

            // Add each dependency only once.
            if (graph.get(u).add(v)) {
                indegree[v]++;
            }
        }

        // Step 3: Initialize Kahn's queue.
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < ALPHABET; i++) {
            if (present[i] && indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // Step 4: Topological sort.
        StringBuilder order = new StringBuilder();

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.append((char) ('a' + current));

            for (int neighbor : graph.get(current)) {
                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // A cycle means no valid ordering exists.
        if (order.length() != uniqueLetters) {
            return "";
        }

        return order.toString();
    }

    public static void main(String[] args) {
        AlienDictionary solution = new AlienDictionary();

        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(solution.alienOrder(words));
    }
}
