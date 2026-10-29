
package streak;

import java.util.*;

public class PalindromePathQueries {

    private int[] tin;
    private int[] tout;
    private int timer;
    private int[][] up;
    private int LOG;
    private int[] bit;
    private int n;
    private char[] currentChars;

    public List<Boolean> palindromePath(
        int n, int[][] edges, String s, String[] queries
    ) {
        this.n = n;
        this.currentChars = s.toCharArray();

        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        LOG = (int) (Math.log(n) / Math.log(2)) + 2;
        up = new int[n][LOG];
        tin = new int[n];
        tout = new int[n];
        timer = 0;

        dfs(0, 0, adj);

        bit = new int[timer + 5];

        // Initialize subtree XOR updates for each character
        for (int i = 0; i < n; i++) {
            int mask = 1 << (currentChars[i] - 'a');
            updateRange(tin[i], tout[i], mask);
        }

        List<Boolean> ansList = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);
                char oldChar = currentChars[u];

                if (oldChar != newChar) {
                    int changeMask =
                        (1 << (oldChar - 'a')) ^
                        (1 << (newChar - 'a'));

                    updateRange(tin[u], tout[u], changeMask);
                    currentChars[u] = newChar;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lcaNode = getLCA(u, v);
                int maskU = queryBIT(tin[u]);
                int maskV = queryBIT(tin[v]);
                int maskLCA = 1 << (currentChars[lcaNode] - 'a');

                int fullPathMask = maskU ^ maskV ^ maskLCA;

                // A palindrome can have at most one odd-frequency character
                ansList.add(
                    (fullPathMask & (fullPathMask - 1)) == 0
                );
            }
        }

        return ansList;
    }

    private void dfs(int u, int p, List<Integer>[] adj) {
        tin[u] = ++timer;
        up[u][0] = p;

        for (int i = 1; i < LOG; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }

        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u, adj);
            }
        }

        tout[u] = timer;
    }

    private boolean isAncestor(int u, int v) {
        return tin[u] <= tin[v] && tout[u] >= tout[v];
    }

    private int getLCA(int u, int v) {
        if (isAncestor(u, v)) {
            return u;
        }

        if (isAncestor(v, u)) {
            return v;
        }

        for (int i = LOG - 1; i >= 0; i--) {
            if (!isAncestor(up[u][i], v)) {
                u = up[u][i];
            }
        }

        return up[u][0];
    }

    private void updateRange(int l, int r, int val) {
        updateBIT(l, val);
        updateBIT(r + 1, val);
    }

    private void updateBIT(int idx, int val) {
        for (; idx < bit.length; idx += idx & -idx) {
            bit[idx] ^= val;
        }
    }

    private int queryBIT(int idx) {
        int sum = 0;

        for (; idx > 0; idx -= idx & -idx) {
            sum ^= bit[idx];
        }

        return sum;
    }

    public static void main(String[] args) {
        PalindromePathQueries solution = new PalindromePathQueries();

        int n = 3;
        int[][] edges = {{0, 1}, {0, 2}};
        String s = "aba";

        String[] queries = {
            "query 1 2",
            "update 1 c",
            "query 1 2"
        };

        System.out.println(
            solution.palindromePath(n, edges, s, queries)
        );
    }
}
