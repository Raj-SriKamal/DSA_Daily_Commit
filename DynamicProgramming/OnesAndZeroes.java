package DynamicProgramming;

/*
==================================================
Day 04 - Ones and Zeroes
LeetCode 474
Difficulty: Medium
Pattern: Take / Not Take + 0/1 Knapsack
==================================================

PROBLEM:

You are given an array of binary strings.

You have:
m = maximum number of zeroes
n = maximum number of ones

Return the maximum number of strings you can form
using at most m zeroes and n ones.

--------------------------------------------------
EXAMPLE:

Input:
strs = ["10","0001","111001","1","0"]
m = 5
n = 3

Output:
4

We can choose:

"10"     -> 1 zero, 1 one
"0001"   -> 3 zeroes, 1 one
"1"      -> 0 zeroes, 1 one
"0"      -> 1 zero, 0 ones

Total:
zeroes = 5
ones = 3

Number of strings = 4

--------------------------------------------------
LOGIC:

Each string is an item.

Each string has two costs:

zeros = number of zeroes
ones  = number of ones

We want to maximize the number of strings.

This is a 0/1 Knapsack with TWO capacities:

Capacity 1 = m zeroes
Capacity 2 = n ones

For every string we have two choices:

NOT TAKE:
dp[i][j]

TAKE:
1 + dp[i - zeros][j - ones]

Therefore:

dp[i][j] =
max(dp[i][j],
    1 + dp[i - zeros][j - ones])

--------------------------------------------------
WHY BACKWARD?

Each string can be used only once.

Therefore, both capacities must be processed
backward.

--------------------------------------------------
ALGORITHM:

1. Create a 2D DP array of size (m+1) x (n+1).
2. For every string, count zeroes and ones.
3. Iterate zero capacity backward.
4. Iterate one capacity backward.
5. Apply the take/not-take transition.
6. Return dp[m][n].

--------------------------------------------------
COMPLEXITY:

Let k = number of strings.

Time: O(k * m * n)
Space: O(m * n)

==================================================
*/

public class OnesAndZeroes {

    public static int findMaxForm(String[] strs, int m, int n) {

        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {

            int zeros = 0;
            int ones = 0;

            // Count zeroes and ones
            for (char ch : str.toCharArray()) {

                if (ch == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }

            // Backward because each string is used once
            for (int i = m; i >= zeros; i--) {

                for (int j = n; j >= ones; j--) {

                    int take = 1 + dp[i - zeros][j - ones];

                    int notTake = dp[i][j];

                    dp[i][j] = Math.max(take, notTake);
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {

        String[] strs = {
                "10",
                "0001",
                "111001",
                "1",
                "0"
        };

        int m = 5;
        int n = 3;

        System.out.println(findMaxForm(strs, m, n));
    }
}