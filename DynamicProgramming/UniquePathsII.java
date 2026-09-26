package DynamicProgramming;

/*
==================================================
Day 12 - Unique Paths II
LeetCode 63
Difficulty: Medium

Pattern:
Grid DP + Counting

Problem:
Given an m x n grid containing obstacles, return the
number of unique paths from the top-left corner to
the bottom-right corner.

You can only move:
    1. Right
    2. Down

0 = free cell
1 = obstacle

==================================================

TIME:
O(m * n)

SPACE:
O(m * n)
==================================================
*/

public class UniquePathsII {

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        int[][] dp = new int[rows][cols];

        // Starting cell is blocked
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // One way to start from the starting cell
        dp[0][0] = 1;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                // Skip starting cell because it is already initialized
                if (i == 0 && j == 0) {
                    continue;
                }

                // Obstacle means no path through this cell
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }

                // From top
                if (i > 0) {
                    dp[i][j] += dp[i - 1][j];
                }

                // From left
                if (j > 0) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }

        return dp[rows - 1][cols - 1];
    }

    public static void main(String[] args) {

        int[][] obstacleGrid = {
                { 0, 0, 0 },
                { 0, 1, 0 },
                { 0, 0, 0 }
        };

        System.out.println(
                uniquePathsWithObstacles(obstacleGrid));
    }
}