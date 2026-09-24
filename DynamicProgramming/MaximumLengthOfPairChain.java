package DynamicProgramming;

import java.util.Arrays;
/*
==================================================
Day 10 - Maximum Length of Pair Chain
LeetCode 646
Difficulty: Medium
Pattern: LIS + Sorting + DP
==================================================

PROBLEM:

You are given n pairs:
pairs[i] = [lefti, righti]
A pair [a,b] can follow [c,d] if:
b < c
Return the longest possible chain.
--------------------------------------------------
ALGORITHM:

1. Sort pairs by first value.
2. Create dp[].
3. Fill dp[] with 1.
4. For every i:
      Check every j < i.
5. If pairs[j][1] < pairs[i][0]:
      dp[i] = max(dp[i], dp[j] + 1)
6. Return maximum dp value.

--------------------------------------------------
EXAMPLE:

pairs:
[[1,2],[2,3],[3,4]]
dp:
[1,1,2]
Answer:
2
Chain:
[1,2] -> [3,4]

--------------------------------------------------
COMPLEXITY:

Sorting: O(n log n)
DP: O(n²)

Overall:

Time: O(n²)
Space: O(n)

==================================================
*/

public class MaximumLengthOfPairChain {

    public static int findLongestChain(int[][] pairs) {

        int n = pairs.length;

        // Sort by starting value
        Arrays.sort(
                pairs,
                (a, b) -> Integer.compare(a[0], b[0]));

        // dp[i] = longest chain ending at i
        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        int answer = 1;

        // Consider every pair as the ending pair
        for (int i = 0; i < n; i++) {

            // Check previous pairs
            for (int j = 0; j < i; j++) {

                // Can pair j be followed by pair i?
                if (pairs[j][1] < pairs[i][0]) {

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

        int[][] pairs = {
                { 3, 4 },
                { 1, 2 },
                { 7, 8 },
                { 4, 5 }
        };

        System.out.println(
                findLongestChain(pairs));
    }
}