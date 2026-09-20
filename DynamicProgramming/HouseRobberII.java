package DynamicProgramming;

/*
==================================================
House Robber II
LeetCode 213
Difficulty: Medium
Pattern: Take / Not Take DP
==================================================

PROBLEM:
You are given houses arranged in a circle.
Each house contains some money.

You cannot rob two adjacent houses.

Return the maximum amount of money you can rob.

--------------------------------------------------
EXAMPLE:

Input:
nums = [2, 3, 2]

Output:
3

Explanation:
The first and last houses are adjacent,
so we cannot rob both.

We can rob the middle house:
3

--------------------------------------------------
LOGIC:

Because houses are arranged in a circle,
we cannot take both the first and last house.

So divide into two cases:

Case 1:
Exclude the first house.
Consider nums[1 ... n-1]

Case 2:
Exclude the last house.
Consider nums[0 ... n-2]

Answer:
max(case1, case2)

For each linear case:

Take:
nums[i] + prev2

Not Take:
prev1

Current:
max(take, notTake)

--------------------------------------------------
ALGORITHM:

1. If there is only one house, return nums[0].
2. Solve excluding the first house.
3. Solve excluding the last house.
4. Return the maximum of both cases.

--------------------------------------------------
COMPLEXITY:

Time: O(n)
Space: O(1)
==================================================
*/

public class HouseRobberII {

    public static int rob(int[] nums) {

        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        // Case 1: Exclude first house
        int case1 = robLinear(nums, 1, n - 1);

        // Case 2: Exclude last house
        int case2 = robLinear(nums, 0, n - 2);

        return Math.max(case1, case2);
    }

    private static int robLinear(int[] nums, int start, int end) {

        int prev2 = 0;
        int prev1 = 0;

        for (int i = start; i <= end; i++) {

            int take = nums[i] + prev2;
            int notTake = prev1;

            int current = Math.max(take, notTake);

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public static void main(String[] args) {

        int[] nums = { 2, 3, 2 };

        System.out.println(rob(nums));
    }
}