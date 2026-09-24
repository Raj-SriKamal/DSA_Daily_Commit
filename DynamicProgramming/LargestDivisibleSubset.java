package DynamicProgramming;

import java.util.*;
/*
==================================================
Day 10 - Largest Divisible Subset
LeetCode 368
Difficulty: Medium
Pattern: LIS + Sorting + DP + Reconstruction
==================================================

PROBLEM:

Given a set of distinct positive integers,
return the largest subset such that for every
pair of elements:

a % b == 0
OR
b % a == 0

--------------------------------------------------
EXAMPLE:
Input:
nums = [1,2,4,8]
Output:
[1,2,4,8]
Because:
2 % 1 = 0
4 % 2 = 0
8 % 4 = 0
--------------------------------------------------
ALGORITHM:

1. Sort nums.
2. Create dp[].
3. Create prev[].
4. Initialize dp[i] = 1.
5. Initialize prev[i] = -1.
6. For every i:
      Check every j < i.
7. If nums[i] % nums[j] == 0:
      Update dp[i].
      Store prev[i] = j.
8. Find index with maximum dp.
9. Reconstruct using prev[].
10. Reverse the result.

--------------------------------------------------
COMPLEXITY:

Sorting: O(n log n)
DP: O(n²)
Reconstruction: O(n)

Overall:

Time: O(n²)
Space: O(n)

==================================================
*/

public class LargestDivisibleSubset {

    public static List<Integer> largestDivisibleSubset(
            int[] nums) {

        int n = nums.length;

        // Sort the array
        Arrays.sort(nums);

        // dp[i] = largest divisible subset
        // ending at i
        int[] dp = new int[n];

        // prev[i] = previous index in the subset
        int[] prev = new int[n];

        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        int maxLength = 1;
        int lastIndex = 0;

        // LIS-style DP
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (nums[i] % nums[j] == 0
                        && dp[j] + 1 > dp[i]) {

                    dp[i] = dp[j] + 1;

                    // Store parent
                    prev[i] = j;
                }
            }

            // Track longest subset
            if (dp[i] > maxLength) {

                maxLength = dp[i];
                lastIndex = i;
            }
        }

        // Reconstruct the subset
        List<Integer> result = new ArrayList<>();

        while (lastIndex != -1) {

            result.add(nums[lastIndex]);

            lastIndex = prev[lastIndex];
        }

        // We reconstructed backwards
        Collections.reverse(result);

        return result;
    }

    public static void main(String[] args) {

        int[] nums = {
                1, 2, 4, 8
        };

        System.out.println(
                largestDivisibleSubset(nums));
    }
}