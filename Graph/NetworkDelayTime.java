
package Graph;

import java.util.*;

public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];

            graph[u].add(new int[]{v, w});
        }

        int INF = Integer.MAX_VALUE;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        dist[k] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        pq.offer(new int[]{0, k});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            int currentTime = current[0];
            int node = current[1];

            if (currentTime > dist[node]) {
                continue;
            }

            for (int[] edge : graph[node]) {
                int neighbor = edge[0];
                int weight = edge[1];

                int newTime = currentTime + weight;

                if (newTime < dist[neighbor]) {
                    dist[neighbor] = newTime;
                    pq.offer(new int[]{newTime, neighbor});
                }
            }
        }

        int maxTime = 0;

        for (int i = 1; i <= n; i++) {
            if (dist[i] == INF) {
                return -1;
            }

            maxTime = Math.max(maxTime, dist[i]);
        }

        return maxTime;
    }

    public static void main(String[] args) {
        NetworkDelayTime solution = new NetworkDelayTime();

        int[][] times = {
            {2, 1, 1}, {2, 3, 1}, {3, 4, 1}
        };

        System.out.println(solution.networkDelayTime(times, 4, 2));
    }
}
