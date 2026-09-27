package DynamicProgramming;

public class DungeonGame {

    public static int calculateMinimumHP(int[][] dungeon) {

        int rows = dungeon.length;
        int cols = dungeon[0].length;

        int[][] dp = new int[rows][cols];

        // Princess cell
        dp[rows - 1][cols - 1] = Math.max(
                1,
                1 - dungeon[rows - 1][cols - 1]);

        // Last column
        for (int i = rows - 2; i >= 0; i--) {

            dp[i][cols - 1] = Math.max(
                    1,
                    dp[i + 1][cols - 1]
                            - dungeon[i][cols - 1]);
        }

        // Last row
        for (int j = cols - 2; j >= 0; j--) {

            dp[rows - 1][j] = Math.max(
                    1,
                    dp[rows - 1][j + 1]
                            - dungeon[rows - 1][j]);
        }

        // Remaining cells
        for (int i = rows - 2; i >= 0; i--) {

            for (int j = cols - 2; j >= 0; j--) {

                int nextHealth = Math.min(
                        dp[i + 1][j],
                        dp[i][j + 1]);

                dp[i][j] = Math.max(
                        1,
                        nextHealth - dungeon[i][j]);
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {

        int[][] dungeon = {
                { -2, -3, 3 },
                { -5, -10, 1 },
                { 10, 30, -5 }
        };

        System.out.println(
                calculateMinimumHP(dungeon));
    }
}