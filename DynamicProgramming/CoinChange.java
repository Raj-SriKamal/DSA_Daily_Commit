package DynamicProgramming;

/*
==================================================
Day 07 - Coin Change
LeetCode 322
Difficulty: Medium
Pattern: Infinite Supply DP
==================================================

PROBLEM:

Given coins of different denominations and an amount,
return the fewest number of coins needed to make that
amount.

You can use each coin unlimited times.

If the amount cannot be made, return -1.

--------------------------------------------------
EXAMPLE:

Input:
coins = [1, 2, 5]
amount = 11

Output:
3

Explanation:

11 = 5 + 5 + 1

So the minimum number of coins is 3.

--------------------------------------------------
DP STATE:

dp[a] = minimum number of coins needed to make amount a

For example:

dp[0] = 0
dp[1] = 1
dp[2] = 1
dp[3] = 2
dp[5] = 1

--------------------------------------------------
BASE CASE:

dp[0] = 0

Because zero coins are required to make amount 0.

--------------------------------------------------
TRANSITION:

For every coin:

dp[a] = min(dp[a], 1 + dp[a - coin])

Why +1?

Because we are using one additional coin.

--------------------------------------------------
WHY INFINITE SUPPLY?

Each coin can be used unlimited times.

For example:

coins = [1, 2, 5]

We can use:

5 + 5 + 1

The same denomination can be selected multiple times.

Therefore, this is an INFINITE SUPPLY DP problem.

--------------------------------------------------
ALGORITHM:

1. Create dp array of size amount + 1.

2. Initialize dp values with amount + 1.

   Why amount + 1?

   The maximum possible number of coins is amount
   when coin denomination 1 exists.

   Therefore amount + 1 represents an impossible state.

3. Set dp[0] = 0.

4. For every amount from 1 to amount:

      Try every coin.

5. If coin <= current amount:

      dp[a] = min(dp[a], 1 + dp[a - coin])

6. Return dp[amount].

7. If dp[amount] is still amount + 1,
   return -1.

--------------------------------------------------
DRY RUN:

coins = [1, 2, 5]
amount = 5

Initially:

dp = [0, INF, INF, INF, INF, INF]

Using coin 1:

dp = [0, 1, 2, 3, 4, 5]

Using coin 2:

dp[2] = min(2, dp[0] + 1) = 1

dp[3] = min(3, dp[1] + 1) = 2

dp[4] = min(4, dp[2] + 1) = 2

dp[5] = min(5, dp[3] + 1) = 3

Using coin 5:

dp[5] = min(3, dp[0] + 1) = 1

Final:

dp[5] = 1

Because:

5 = one 5-coin.

--------------------------------------------------
COMPLEXITY:

Time: O(amount * number of coins)

Space: O(amount)

==================================================
*/

public class CoinChange {

    public static int coinChange(int[] coins, int amount) {

        // dp[a] = minimum number of coins needed
        // to make amount a
        int[] dp = new int[amount + 1];

        // Initialize impossible states
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
        }

        // Base case
        dp[0] = 0;

        // Calculate answer for every amount
        for (int a = 1; a <= amount; a++) {

            // Try every coin
            for (int coin : coins) {

                if (coin <= a) {

                    dp[a] = Math.min(
                            dp[a],
                            1 + dp[a - coin]);
                }
            }
        }

        // Amount cannot be formed
        if (dp[amount] == amount + 1) {
            return -1;
        }

        return dp[amount];
    }

    public static void main(String[] args) {

        int[] coins = { 1, 2, 5 };
        int amount = 11;

        System.out.println(coinChange(coins, amount));
    }
}