
package Graph;

import java.util.Arrays;

public class MinCostToConnectAllPoints {

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;

        int[] minDist = new int[n];
        boolean[] visited = new boolean[n];

        // Initially, all points have infinite connection cost
        Arrays.fill(minDist, Integer.MAX_VALUE);

        // Start from point 0
        minDist[0] = 0;

        int totalCost = 0;

        for (int count = 0; count < n; count++) {
            int current = -1;

            // Find the cheapest unvisited point
            for (int i = 0; i < n; i++) {
                if (!visited[i] &&
                    (current == -1 || minDist[i] < minDist[current])) {
                    current = i;
                }
            }

            // Include this point in the MST
            visited[current] = true;
            totalCost += minDist[current];

            // Update minimum costs for remaining points
            for (int next = 0; next < n; next++) {
                if (!visited[next]) {
                    int distance =
                        Math.abs(points[current][0] - points[next][0])
                        + Math.abs(points[current][1] - points[next][1]);

                    minDist[next] = Math.min(minDist[next], distance);
                }
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        MinCostToConnectAllPoints solution =
            new MinCostToConnectAllPoints();

        int[][] points = {
            {0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}
        };

        System.out.println(solution.minCostConnectPoints(points));
    }
}
