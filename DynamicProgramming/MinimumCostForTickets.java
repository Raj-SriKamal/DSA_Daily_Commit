package DynamicProgramming;

/*
==================================================
Day 10 - Minimum Cost For Tickets
LeetCode 983
Difficulty: Medium
Pattern: Infinite Supply / Cost DP
==================================================

PROBLEM:

You have planned some train traveling in one year.

You are given:

days:

The days of the year on which you will travel.

You are also given:

costs[0] = cost of a 1-day pass
costs[1] = cost of a 7-day pass
costs[2] = cost of a 30-day pass

Return the minimum cost required to cover all
travel days.

--------------------------------------------------
EXAMPLE:

Input:

days = [1,4,6,7,8,20]

costs = [2,7,15]

Output:

11

Explanation:

Buy:

1-day pass  -> day 1
7-day pass  -> days 4,6,7,8
1-day pass  -> day 20

Total:

2 + 7 + 2 = 11

--------------------------------------------------
DP STATE:

dp[i] = minimum cost needed to cover travel days
        from day i onward.

We will calculate the answer starting from
the last day.

--------------------------------------------------
IMPORTANT IDEA:

If today is NOT a travel day:

No ticket is needed.

So:

dp[i] = dp[i + 1]

If today IS a travel day:

We have three choices.

1. Buy a 1-day pass:

costs[0] + dp[i + 1]

2. Buy a 7-day pass:

costs[1] + dp[i + 7]

3. Buy a 30-day pass:

costs[2] + dp[i + 30]

Take the minimum.

--------------------------------------------------
TRANSITION:

If day i is a travel day:

dp[i] = min(
    costs[0] + dp[i + 1],
    costs[1] + dp[i + 7],
    costs[2] + dp[i + 30]
)

--------------------------------------------------
WHY INFINITE SUPPLY?

There is no restriction on buying passes.

We can buy multiple:

1-day passes

7-day passes

30-day passes

Therefore we can repeatedly choose any pass.

--------------------------------------------------
ALGORITHM:

1. Create dp array of size 366.

2. Start from day 365 and move backwards.

3. Check whether the current day is a travel day.

4. If it is not a travel day:

      dp[day] = dp[day + 1]

5. If it is a travel day:

      Try a 1-day pass.
      Try a 7-day pass.
      Try a 30-day pass.

6. Take the minimum cost.

7. Return dp[1].

--------------------------------------------------
EXAMPLE:

days = [1,4,6,7,8,20]
costs = [2,7,15]

For day 20:

Buy 1-day:
2 + dp[21]

Buy 7-day:
7 + dp[27]

Buy 30-day:
15 + dp[50]

Choose the cheapest.

The same process continues backwards until
day 1.

--------------------------------------------------
COMPLEXITY:

Time: O(365)

Space: O(365)

==================================================
*/

public class MinimumCostForTickets {

    public static int mincostTickets(int[] days, int[] costs) {

        // Mark all travel days
        boolean[] travel = new boolean[366];

        for (int day : days) {
            travel[day] = true;
        }

        // dp[day] = minimum cost from this day onward
        int[] dp = new int[366];

        // Calculate backwards from day 365
        for (int day = 365; day >= 1; day--) {

            // If today is not a travel day,
            // no ticket is required
            if (!travel[day]) {

                dp[day] = dp[day + 1];

            } else {

                // Option 1: 1-day pass
                int oneDay = costs[0] + dp[day + 1];

                // Option 2: 7-day pass
                int sevenDay = costs[1];

                if (day + 7 <= 365) {
                    sevenDay += dp[day + 7];
                }

                // Option 3: 30-day pass
                int thirtyDay = costs[2];

                if (day + 30 <= 365) {
                    thirtyDay += dp[day + 30];
                }

                // Choose the cheapest option
                dp[day] = Math.min(
                        oneDay,
                        Math.min(sevenDay, thirtyDay));
            }
        }

        return dp[1];
    }

    public static void main(String[] args) {

        int[] days = { 1, 4, 6, 7, 8, 20 };

        int[] costs = { 2, 7, 15 };

        System.out.println(
                mincostTickets(days, costs));
    }
}