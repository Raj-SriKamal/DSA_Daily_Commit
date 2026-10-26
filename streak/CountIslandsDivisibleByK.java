
package streak;

public class CountIslandsDivisibleByK {

    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long islandSum = dfs(grid, i, j);

                    if (islandSum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private long dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length ||
            c < 0 || c >= grid[0].length ||
            grid[r][c] == 0) {
            return 0;
        }

        long sum = grid[r][c];

        // Mark this cell as visited
        grid[r][c] = 0;

        sum += dfs(grid, r + 1, c);
        sum += dfs(grid, r - 1, c);
        sum += dfs(grid, r, c + 1);
        sum += dfs(grid, r, c - 1);

        return sum;
    }

    public static void main(String[] args) {
        CountIslandsDivisibleByK solution =
            new CountIslandsDivisibleByK();

        int[][] grid = {
            {0, 2, 1, 0},
            {0, 3, 0, 0},
            {0, 0, 0, 4},
            {0, 0, 0, 0}
        };

        int k = 3;

        System.out.println(solution.countIslands(grid, k));
    }
}
