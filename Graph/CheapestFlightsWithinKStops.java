
package Graph;

import java.util.Arrays;

public class CheapestFlightsWithinKStops {

    public int findCheapestPrice(
        int n, int[][] flights, int src, int dst, int k
    ) {
        int INF = Integer.MAX_VALUE / 2;

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        // At most k stops means at most k + 1 flights
        for (int round = 0; round < k + 1; round++) {
            // Use only costs from the previous round
            int[] temp = dist.clone();

            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int price = flight[2];

                if (dist[u] != INF) {
                    temp[v] = Math.min(temp[v], dist[u] + price);
                }
            }

            dist = temp;
        }

        return dist[dst] == INF ? -1 : dist[dst];
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops solution =
            new CheapestFlightsWithinKStops();

        int[][] flights = {
            {0, 1, 100}, {1, 2, 100}, {0, 2, 500}
        };

        System.out.println(
            solution.findCheapestPrice(3, flights, 0, 2, 1)
        );
    }
}
