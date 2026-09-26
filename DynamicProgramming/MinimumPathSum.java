package DynamicProgramming;

/*
==================================================
Day 12 - Minimum Path Sum
LeetCode 64
Difficulty: Medium

Pattern:
Grid DP + Minimum

Problem:
Given an m x n grid filled with non-negative numbers,
find a path from top-left to bottom-right that minimizes
the sum of all numbers along its path.

Allowed moves:
    1. Right
    2. Down

TIME:
O(m * n)

SPACE:
O(m * n)
==================================================
*/

public class MinimumPathSum {

    public static int minPathSum(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dp = new int[rows][cols];

        // Starting cell
        dp[0][0] = grid[0][0];

        // First row
        for (int j = 1; j < cols; j++) {
            dp[0][j] = grid[0][j] + dp[0][j - 1];
        }

        // First column
        for (int i = 1; i < rows; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }

        // Remaining cells
        for (int i = 1; i < rows; i++) {

            for (int j = 1; j < cols; j++) {

                dp[i][j] = grid[i][j]
                        + Math.min(
                                dp[i - 1][j],
                                dp[i][j - 1]);
            }
        }

        return dp[rows - 1][cols - 1];
    }

    public static void main(String[] args) {

        int[][] grid = {
                { 1, 3, 1 },
                { 1, 5, 1 },
                { 4, 2, 1 }
        };

        System.out.println(minPathSum(grid));
    }
}