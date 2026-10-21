
package Graph;

import java.util.*;

public class NumberOfOperationsToMakeNetworkConnected {

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] connection : connections) {
            int u = connection[0];
            int v = connection[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        int components = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                components++;
                dfs(i, graph, visited);
            }
        }

        return components - 1;
    }

    private void dfs(
        int node,
        List<List<Integer>> graph,
        boolean[] visited
    ) {
        visited[node] = true;

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited);
            }
        }
    }

    public static void main(String[] args) {
        NumberOfOperationsToMakeNetworkConnected solution =
            new NumberOfOperationsToMakeNetworkConnected();

        int n = 4;
        int[][] connections = {
            {0, 1}, {0, 2}, {1, 2}
        };

        System.out.println(solution.makeConnected(n, connections));
    }
}
