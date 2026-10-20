
package Graph;

import java.util.*;

public class SequenceReconstruction {

    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        int n = nums.length;

        List<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new HashSet<>());
        }

        int[] indegree = new int[n + 1];
        boolean[] seen = new boolean[n + 1];

        // Build graph and avoid duplicate edges.
        for (int[] seq : sequences) {
            for (int num : seq) {
                seen[num] = true;
            }

            for (int i = 0; i < seq.length - 1; i++) {
                int u = seq[i];
                int v = seq[i + 1];

                if (graph.get(u).add(v)) {
                    indegree[v]++;
                }
            }
        }

        // Every number must appear in the sequences.
        for (int i = 1; i <= n; i++) {
            if (!seen[i]) {
                return false;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int index = 0;

        while (!queue.isEmpty()) {
            // More than one choice means ordering is not unique.
            if (queue.size() != 1) {
                return false;
            }

            int current = queue.poll();

            // The unique order must match nums.
            if (index >= n || nums[index] != current) {
                return false;
            }

            index++;

            for (int neighbor : graph.get(current)) {
                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return index == n;
    }

    public static void main(String[] args) {
        SequenceReconstruction solution = new SequenceReconstruction();

        int[] nums = {1, 2, 3};
        int[][] sequences = {{1, 2}, {1, 3}, {2, 3}};

        System.out.println(solution.sequenceReconstruction(nums, sequences));
    }
}
