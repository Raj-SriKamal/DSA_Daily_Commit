package DynamicProgramming;

/*
==================================================
Day 09 - Perfect Squares
LeetCode 279
Difficulty: Medium
Pattern: Infinite Supply DP
==================================================

PROBLEM:

Given an integer n, return the least number of
perfect square numbers that sum to n.

A perfect square is a number that can be written as:

1, 4, 9, 16, 25, ...

Each perfect square can be used unlimited times.

--------------------------------------------------
EXAMPLE:

Input:
n = 12

Output:
3

Explanation:

12 = 4 + 4 + 4

So the minimum number of perfect squares is 3.

--------------------------------------------------
ANOTHER EXAMPLE:

Input:
n = 13

Output:
2

Explanation:

13 = 4 + 9

--------------------------------------------------
DP STATE:

dp[i] = minimum number of perfect squares
        needed to make the value i

--------------------------------------------------
BASE CASE:

dp[0] = 0

Because zero numbers are required to make 0.

--------------------------------------------------
TRANSITION:

For every number i:

Try every perfect square j*j <= i.

Then:

dp[i] = min(dp[i], dp[i - j*j] + 1)

Why +1?

Because we are using one additional
perfect square j*j.

--------------------------------------------------
WHY INFINITE SUPPLY?

A perfect square can be used multiple times.

For example:

n = 12

4 + 4 + 4

The perfect square 4 is reused.

Therefore this is an INFINITE SUPPLY DP problem.

--------------------------------------------------
ALGORITHM:

1. Create dp array of size n + 1.

2. Initialize every value with n + 1.

3. Set dp[0] = 0.

4. For every value i from 1 to n:

      Try every square j*j <= i.

5. Update:

      dp[i] = min(
          dp[i],
          dp[i - j*j] + 1
      );

6. Return dp[n].

--------------------------------------------------
DRY RUN:

n = 12

Perfect squares:

1, 4, 9

dp[0] = 0

For 1:

1 = 1
dp[1] = 1

For 4:

4 = 4
dp[4] = 1

For 9:

9 = 9
dp[9] = 1

For 12:

Using 1:

12 = 1 + dp[11]

Using 4:

12 = 4 + dp[8]

dp[8] = 2
therefore:

dp[12] = 3

Using 9:

12 = 9 + dp[3]

dp[3] = 3

So:

dp[12] = 3

--------------------------------------------------
COMPLEXITY:

Time: O(n * sqrt(n))
Space: O(n)

==================================================
*/

public class PerfectSquares {

    public static int numSquares(int n) {

        // dp[i] = minimum number of perfect squares
        // needed to make i
        int[] dp = new int[n + 1];

        // Initialize with an impossible large value
        for (int i = 1; i <= n; i++) {
            dp[i] = n + 1;
        }

        // Base case
        dp[0] = 0;

        // Calculate answer for every value
        for (int i = 1; i <= n; i++) {

            // Try every perfect square
            for (int j = 1; j * j <= i; j++) {

                int square = j * j;

                dp[i] = Math.min(
                    dp[i],
                    dp[i - square] + 1
                );
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {

        int n = 12;

        System.out.println(numSquares(n));
    }
}