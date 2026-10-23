
package Graph;

public class EquationsPossible {

    public boolean equationsPossible(String[] equations) {
        int[] parent = new int[26];
        int[] rank = new int[26];

        // Each variable initially belongs to its own group
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        // Pass 1: Process all equality equations
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                int x = equation.charAt(0) - 'a';
                int y = equation.charAt(3) - 'a';

                union(x, y, parent, rank);
            }
        }

        // Pass 2: Check all inequality equations
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                int x = equation.charAt(0) - 'a';
                int y = equation.charAt(3) - 'a';

                if (find(x, parent) == find(y, parent)) {
                    return false;
                }
            }
        }

        return true;
    }

    private int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent);
        }

        return parent[x];
    }

    private void union(
        int x,
        int y,
        int[] parent,
        int[] rank
    ) {
        int rootX = find(x, parent);
        int rootY = find(y, parent);

        if (rootX == rootY) {
            return;
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    public static void main(String[] args) {
        EquationsPossible solution = new EquationsPossible();

        String[] equations = {"a==b", "b!=a"};
        System.out.println(solution.equationsPossible(equations));
    }
}
