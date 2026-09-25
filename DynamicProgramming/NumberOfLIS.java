package DynamicProgramming;

/*
==================================================
Day 11 - Number of Longest Increasing Subsequence
LeetCode 673
Difficulty: Medium
Pattern: LIS + Counting DP
==================================================

PROBLEM:

Given an integer array nums, return the number of
longest increasing subsequences.

--------------------------------------------------
EXAMPLE:

Input:

nums = [1,3,5,4,7]

Longest increasing subsequences:

[1,3,5,7]
[1,3,4,7]

Length = 4

Number of LIS = 2

Output:

2

--------------------------------------------------
IMPORTANT:

Normal LIS asks:

"What is the maximum length?"

This problem asks:

1. What is the maximum length?
2. How many LIS have that length?

Therefore we need TWO arrays.
--------------------------------------------------
ALGORITHM:

1. Create length[] and count[].
2. Initialize both with 1.
3. For every i:
      Check every j < i.
4. If nums[j] < nums[i]:
      Handle longer or equal LIS.
5. Find global maximum LIS length.
6. Count all subsequences having that length.
7. Return answer.

--------------------------------------------------
COMPLEXITY:

Time: O(n²)
Space: O(n)

==================================================
*/

public class NumberOfLIS {

    public static int findNumberOfLIS(int[] nums) {

        int n = nums.length;

        // length[i] = longest LIS ending at i
        int[] length = new int[n];

        // count[i] = number of LIS of length[i]
        // ending at i
        int[] count = new int[n];

        // Base case
        for (int i = 0; i < n; i++) {

            length[i] = 1;
            count[i] = 1;
        }

        // LIS DP
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (nums[j] < nums[i]) {

                    // Found a longer LIS
                    if (length[j] + 1 > length[i]) {

                        length[i] = length[j] + 1;

                        count[i] = count[j];
                    }

                    // Found another LIS
                    // with the same length
                    else if (length[j] + 1 == length[i]) {

                        count[i] += count[j];
                    }
                }
            }
        }

        // Find maximum LIS length
        int maxLength = 0;

        for (int len : length) {

            maxLength = Math.max(
                    maxLength,
                    len);
        }

        // Count all LIS having maxLength
        int answer = 0;

        for (int i = 0; i < n; i++) {

            if (length[i] == maxLength) {

                answer += count[i];
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        int[] nums = {
                1, 3, 5, 4, 7
        };

        System.out.println(
                findNumberOfLIS(nums));
    }
}