
package Graph;

public class FindTheCity {

    public int findTheCity(
        int n, int[][] edges, int distanceThreshold
    ) {
        int INF = 1_000_000_000;
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = (i == j) ? 0 : INF;
            }
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            dist[u][v] = weight;
            dist[v][u] = weight;
        }

        // Floyd-Warshall: all-pairs shortest paths
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF &&
                        dist[k][j] != INF) {

                        dist[i][j] = Math.min(
                            dist[i][j],
                            dist[i][k] + dist[k][j]
                        );
                    }
                }
            }
        }

        int answer = -1;
        int minNeighbors = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int count = 0;

            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] <= distanceThreshold) {
                    count++;
                }
            }

            // Choose the larger index when counts tie
            if (count <= minNeighbors) {
                minNeighbors = count;
                answer = i;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        FindTheCity solution = new FindTheCity();

        int[][] edges = {
            {0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}
        };

        System.out.println(solution.findTheCity(4, edges, 4));
    }
}
