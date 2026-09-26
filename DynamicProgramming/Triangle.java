package DynamicProgramming;

/*
==================================================
Day 12 - Triangle
LeetCode 120
Difficulty: Medium

Pattern:
Grid DP + Minimum + Bottom-Up

Problem:
Given a triangle array, return the minimum path sum
from top to bottom.

At each step, you may move to an adjacent number
of the row below.

Example:

        2
       3 4
      6 5 7
     4 1 8 3

Answer = 11

Path:

2 -> 3 -> 5 -> 1

Sum = 11

==================================================

==================================================

TIME:
O(n^2)

SPACE:
O(n)

==================================================
*/

public class Triangle {

    public static int minimumTotal(
            java.util.List<java.util.List<Integer>> triangle) {

        int n = triangle.size();

        // Copy the last row into DP
        int[] dp = new int[n];

        for (int j = 0; j < n; j++) {
            dp[j] = triangle.get(n - 1).get(j);
        }

        // Start from second-last row
        for (int i = n - 2; i >= 0; i--) {

            for (int j = 0; j <= i; j++) {

                dp[j] = triangle.get(i).get(j)
                        + Math.min(dp[j], dp[j + 1]);
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {

        java.util.List<java.util.List<Integer>> triangle = java.util.Arrays.asList(
                java.util.Arrays.asList(2),
                java.util.Arrays.asList(3, 4),
                java.util.Arrays.asList(6, 5, 7),
                java.util.Arrays.asList(4, 1, 8, 3));

        System.out.println(minimumTotal(triangle));
    }
}