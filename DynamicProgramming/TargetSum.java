package DynamicProgramming;

/*
==================================================
Day 02 - Target Sum
LeetCode 494
Difficulty: Medium
Pattern: Take / Not Take DP + Subset Sum
==================================================

PROBLEM:

You are given an integer array nums.

For every number, choose either '+' or '-'.

Find the number of different expressions that
evaluate to target.

--------------------------------------------------
EXAMPLE:

Input:
nums = [1,1,1,1,1]
target = 3

Output:
5

There are 5 different ways to make 3.

--------------------------------------------------
LOGIC:

Let:

P = sum of numbers given '+'
N = sum of numbers given '-'

We know:

P - N = target

Also:

P + N = totalSum

Adding both equations:

2P = target + totalSum

Therefore:

P = (target + totalSum) / 2

So the problem becomes:

"How many subsets have sum P?"

This is a 0/1 Subset Sum COUNT problem.

--------------------------------------------------
DP:

dp[s] = number of ways to create sum s

Initially:

dp[0] = 1

For every number:

dp[s] += dp[s - num]

We iterate backward because every number can
be used only once.

--------------------------------------------------
ALGORITHM:

1. Calculate total sum.
2. If abs(target) > totalSum, return 0.
3. If (target + totalSum) is odd, return 0.
4. Calculate required subset sum.
5. Create a DP array.
6. Set dp[0] = 1.
7. Process every number using backward iteration.
8. Return dp[subsetSum].

--------------------------------------------------
COMPLEXITY:

Time: O(n * sum)
Space: O(sum)
==================================================
*/

public class TargetSum {

    public static int findTargetSumWays(int[] nums, int target) {

        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        // Target is impossible
        if (Math.abs(target) > sum) {
            return 0;
        }

        // target + sum must be even
        if ((target + sum) % 2 != 0) {
            return 0;
        }

        int subsetSum = (target + sum) / 2;

        // dp[s] = number of ways to make sum s
        int[] dp = new int[subsetSum + 1];

        dp[0] = 1;

        for (int num : nums) {

            // Backward because each number is used once
            for (int s = subsetSum; s >= num; s--) {

                dp[s] += dp[s - num];
            }
        }

        return dp[subsetSum];
    }

    public static void main(String[] args) {

        int[] nums = { 1, 1, 1, 1, 1 };
        int target = 3;

        System.out.println(findTargetSumWays(nums, target));
    }
}