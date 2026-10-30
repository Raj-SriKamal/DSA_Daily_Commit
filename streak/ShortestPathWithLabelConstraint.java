
package streak;

import java.util.*;

public class ShortestPathWithLabelConstraint {

    private int[] tin;
    private int[] tout;
    private int timer;
    private int[][] up;
    private int LOG;
    private int[] bit;
    private int n;
    private char[] currentChars;

    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        int[][] dist = new int[n][k + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            int d = curr[0];
            int u = curr[1];
            int count = curr[2];

            if (d > dist[u][count]) {
                continue;
            }

            if (u == n - 1) {
                return d;
            }

            char uLabel = labels.charAt(u);

            for (int[] nextEdge : adj[u]) {
                int v = nextEdge[0];
                int w = nextEdge[1];
                char vLabel = labels.charAt(v);

                int nextCount =
                    (uLabel == vLabel) ? count + 1 : 1;

                if (nextCount <= k) {
                    if (d + w < dist[v][nextCount]) {
                        dist[v][nextCount] = d + w;
                        pq.offer(new int[]{d + w, v, nextCount});
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        ShortestPathWithLabelConstraint solution =
            new ShortestPathWithLabelConstraint();

        int n = 4;

        int[][] edges = {
            {0, 1, 1},
            {1, 2, 1},
            {2, 3, 1},
            {0, 2, 5}
        };

        String labels = "aabb";
        int k = 2;

        System.out.println(
            solution.shortestPath(n, edges, labels, k)
        );
    }
}
