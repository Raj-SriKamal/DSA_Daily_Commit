package DynamicProgramming;

/*
==================================================
Day 05 - Last Stone Weight II
LeetCode 1049
Difficulty: Medium
Pattern: Take / Not Take + 0/1 Subset Sum
==================================================

PROBLEM:

You are given stones with different weights.

Choose two stones and smash them:

If x == y:
Both stones are destroyed.

If x != y:
The smaller stone is destroyed and the larger
stone becomes y - x.

Return the smallest possible weight of the
remaining stone.

--------------------------------------------------
EXAMPLE:

Input:
stones = [2,7,4,1,8,1]

Output:
1

--------------------------------------------------
LOGIC:

Instead of simulating every possible smashing order,
divide the stones into two groups.

Let:

Group 1 = S1
Group 2 = S2

The final difference is:

|S1 - S2|

Let total sum = S.

Since:

S1 + S2 = S

We can write:

S2 = S - S1

Therefore:

|S1 - S2|
= |S1 - (S - S1)|
= |2*S1 - S|

To minimize the difference, we want S1 to be
as close as possible to S / 2.

So the problem becomes:

Find the largest subset sum <= totalSum / 2.

This is a 0/1 Subset Sum problem.

--------------------------------------------------
DP:

dp[s] = true if subset sum s is possible.

Initially:

dp[0] = true

For every stone:

dp[s] = dp[s] || dp[s - stone]

We iterate backward because every stone can
be used only once.

--------------------------------------------------
ALGORITHM:

1. Calculate total sum.
2. Set target = totalSum / 2.
3. Create boolean DP array.
4. Set dp[0] = true.
5. Process every stone using backward iteration.
6. Find the largest achievable sum <= target.
7. Return:

   totalSum - 2 * bestSubsetSum

--------------------------------------------------
COMPLEXITY:

Time: O(n * sum)
Space: O(sum)

==================================================
*/

public class LastStoneWeightII {

    public static int lastStoneWeightII(int[] stones) {

        int sum = 0;

        // Calculate total sum
        for (int stone : stones) {
            sum += stone;
        }

        int target = sum / 2;

        // dp[s] = whether sum s is possible
        boolean[] dp = new boolean[target + 1];

        dp[0] = true;

        // Process every stone
        for (int stone : stones) {

            // Backward because each stone is used once
            for (int s = target; s >= stone; s--) {

                dp[s] = dp[s] || dp[s - stone];
            }
        }

        // Find the closest possible subset sum to half
        for (int s = target; s >= 0; s--) {

            if (dp[s]) {
                return sum - 2 * s;
            }
        }

        return 0;
    }

    public static void main(String[] args) {

        int[] stones = { 2, 7, 4, 1, 8, 1 };

        System.out.println(lastStoneWeightII(stones));
    }
}