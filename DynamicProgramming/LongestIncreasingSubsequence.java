package DynamicProgramming;

import java.util.Arrays;
/*
==================================================
Day 10 - Longest Increasing Subsequence
LeetCode 300
Difficulty: Medium
Pattern: LIS / DP on Subsequences
==================================================
PROBLEM:
Given an integer array nums, return the length of
the longest strictly increasing subsequence.
A subsequence keeps the original order, but the
elements do not need to be adjacent.
--------------------------------------------------
EXAMPLE:
Input:
nums = [10, 9, 2, 5, 3, 7, 101, 18]
Output:
4
One LIS is:
[2, 3, 7, 101]
--------------------------------------------------
DP STATE:
dp[i] = length of the longest increasing
        subsequence ending at index i.
--------------------------------------------------
BASE CASE:
Every single element itself is an increasing
subsequence.
Therefore:
dp[i] = 1
for every i.
--------------------------------------------------
TRANSITION:
For every j < i:
If:
nums[j] < nums[i]
then nums[i] can be added after nums[j].
Therefore:
dp[i] = max(dp[i], dp[j] + 1)
--------------------------------------------------
IMPORTANT:
Strictly increasing means:
nums[j] < nums[i]
NOT:
nums[j] <= nums[i]
Example:
[7, 7, 7]
Answer = 1.
--------------------------------------------------
ALGORITHM:
1. Create dp array of size n.
2. Fill every value with 1.
3. For every i:
      Check every j before i.
4. If nums[j] < nums[i]:
      dp[i] = max(dp[i], dp[j] + 1)
5. Return the maximum value in dp.
--------------------------------------------------
EXAMPLE:
nums = [0,1,0,3,2,3]
Final:
dp = [1,2,1,3,3,4]
Answer = 4.
One LIS:
[0,1,2,3]
--------------------------------------------------
COMPLEXITY:

Time: O(n²)
Space: O(n)

==================================================
*/

public class LongestIncreasingSubsequence {

    public static int lengthOfLIS(int[] nums) {

        int n = nums.length;

        int[] dp = new int[n];

        // Every element alone forms
        // an increasing subsequence of length 1
        Arrays.fill(dp, 1);

        int answer = 1;

        // Consider every element as the ending element
        for (int i = 0; i < n; i++) {

            // Check all previous elements
            for (int j = 0; j < i; j++) {

                // nums[j] can come before nums[i]
                if (nums[j] < nums[i]) {

                    dp[i] = Math.max(
                            dp[i],
                            dp[j] + 1);
                }
            }

            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }

    public static void main(String[] args) {

        int[] nums = {
                10, 9, 2, 5, 3, 7, 101, 18
        };

        System.out.println(lengthOfLIS(nums));
    }
}