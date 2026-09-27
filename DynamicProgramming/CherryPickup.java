package DynamicProgramming;

public class CherryPickup {

    public static int cherryPickup(int[][] grid) {

        int n = grid.length;

        int[][][] dp = new int[n][n][n];

        // Initialize states as impossible
        for (int r1 = 0; r1 < n; r1++) {
            for (int c1 = 0; c1 < n; c1++) {
                for (int r2 = 0; r2 < n; r2++) {
                    dp[r1][c1][r2] = Integer.MIN_VALUE;
                }
            }
        }

        dp[0][0][0] = grid[0][0];

        for (int r1 = 0; r1 < n; r1++) {

            for (int c1 = 0; c1 < n; c1++) {

                for (int r2 = 0; r2 < n; r2++) {

                    int c2 = r1 + c1 - r2;

                    // Invalid second person's column
                    if (c2 < 0 || c2 >= n) {
                        continue;
                    }

                    // Blocked cells
                    if (grid[r1][c1] == -1 ||
                            grid[r2][c2] == -1) {
                        continue;
                    }

                    // Starting state
                    if (r1 == 0 && c1 == 0 &&
                            r2 == 0) {
                        continue;
                    }

                    int best = Integer.MIN_VALUE;

                    /*
                     * Previous states:
                     *
                     * Person 1 came from:
                     * top -> r1 - 1
                     * left -> c1 - 1
                     *
                     * Person 2 came from:
                     * top -> r2 - 1
                     * left -> c2 - 1
                     */

                    // Person 1: down
                    // Person 2: down
                    if (r1 > 0 && r2 > 0) {
                        best = Math.max(
                                best,
                                dp[r1 - 1][c1][r2 - 1]);
                    }

                    // Person 1: down
                    // Person 2: right
                    if (r1 > 0 && c2 > 0) {
                        best = Math.max(
                                best,
                                dp[r1 - 1][c1][r2]);
                    }

                    // Person 1: right
                    // Person 2: down
                    if (c1 > 0 && r2 > 0) {
                        best = Math.max(
                                best,
                                dp[r1][c1 - 1][r2 - 1]);
                    }

                    // Person 1: right
                    // Person 2: right
                    if (c1 > 0 && c2 > 0) {
                        best = Math.max(
                                best,
                                dp[r1][c1 - 1][r2]);
                    }

                    if (best == Integer.MIN_VALUE) {
                        continue;
                    }

                    int cherries = grid[r1][c1];

                    // Avoid counting the same cell twice
                    if (r1 != r2 || c1 != c2) {
                        cherries += grid[r2][c2];
                    }

                    dp[r1][c1][r2] = best + cherries;
                }
            }
        }

        int answer = dp[n - 1][n - 1][n - 1];

        return Math.max(answer, 0);
    }

    public static void main(String[] args) {

        int[][] grid = {
                { 0, 1, -1 },
                { 1, 0, -1 },
                { 1, 1, 1 }
        };

        System.out.println(
                cherryPickup(grid));
    }
}