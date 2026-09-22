package DynamicProgramming;

/*
==================================================
Day 03 - Partition Equal Subset Sum
LeetCode 416
Difficulty: Medium
Pattern: Take / Not Take DP + 0/1 Subset Sum
==================================================

PROBLEM:

Given an integer array nums, determine whether the array
can be divided into two subsets such that the sum of
elements in both subsets is equal.

--------------------------------------------------
EXAMPLE:

Input:
nums = [1, 5, 11, 5]

Output:
true

Explanation:

Subset 1 = [1, 5, 5] = 11
Subset 2 = [11] = 11

Both subsets have equal sum.

--------------------------------------------------
LOGIC:

First calculate the total sum.

If the total sum is odd, it is impossible to divide
the array into two equal-sum subsets.

Otherwise:

target = totalSum / 2

Now the problem becomes:

"Can we find a subset whose sum is equal to target?"

This is a 0/1 Subset Sum problem.

--------------------------------------------------
DP:

dp[s] = true if sum s can be created using the
        numbers processed so far.

Initially:

dp[0] = true

For every number:

dp[s] = dp[s] || dp[s - num]

We iterate backward because every number can be
used only once.

--------------------------------------------------
ALGORITHM:

1. Calculate total sum.
2. If total sum is odd, return false.
3. Calculate target = totalSum / 2.
4. Create boolean DP array.
5. Set dp[0] = true.
6. Process every number using backward iteration.
7. Return dp[target].

--------------------------------------------------
COMPLEXITY:

Time: O(n * sum)
Space: O(sum)
==================================================
*/

public class PartitionEqualSubsetSum {

    public static boolean canPartition(int[] nums) {

        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        // Odd total cannot be divided equally
        if (totalSum % 2 != 0) {
            return false;
        }

        int target = totalSum / 2;

        // dp[s] = whether sum s is possible
        boolean[] dp = new boolean[target + 1];

        dp[0] = true;

        for (int num : nums) {

            // Backward because each number is used once
            for (int s = target; s >= num; s--) {

                dp[s] = dp[s] || dp[s - num];
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {

        int[] nums = { 1, 5, 11, 5 };

        System.out.println(canPartition(nums));
    }
}