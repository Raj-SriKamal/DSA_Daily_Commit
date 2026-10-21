
package Graph;

public class RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            int rootU = find(u, parent);
            int rootV = find(v, parent);

            if (rootU == rootV) {
                return edge;
            }

            union(rootU, rootV, parent, rank);
        }

        return new int[0];
    }

    private int find(int node, int[] parent) {
        if (parent[node] != node) {
            parent[node] = find(parent[node], parent);
        }

        return parent[node];
    }

    private void union(
        int rootU,
        int rootV,
        int[] parent,
        int[] rank
    ) {
        if (rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
        } else if (rank[rootU] > rank[rootV]) {
            parent[rootV] = rootU;
        } else {
            parent[rootV] = rootU;
            rank[rootU]++;
        }
    }

    public static void main(String[] args) {
        RedundantConnection solution = new RedundantConnection();

        int[][] edges = {
            {1, 2}, {1, 3}, {2, 3}
        };

        int[] result = solution.findRedundantConnection(edges);

        System.out.println(
            "[" + result[0] + ", " + result[1] + "]"
        );
    }
}
