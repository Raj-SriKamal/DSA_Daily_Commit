
package Graph;

import java.util.*;

public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        // Build graph: prerequisite -> course
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int prerequisite = pair[1];

            graph.get(prerequisite).add(course);
            indegree[course]++;
        }

        // Add courses with no prerequisites
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int completed = 0;

        // Kahn's Algorithm (Topological Sort)
        while (!queue.isEmpty()) {
            int current = queue.poll();
            completed++;

            for (int neighbor : graph.get(current)) {
                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return completed == numCourses;
    }

    public static void main(String[] args) {
        CourseSchedule solution = new CourseSchedule();

        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};

        System.out.println(solution.canFinish(numCourses, prerequisites));
    }
}
