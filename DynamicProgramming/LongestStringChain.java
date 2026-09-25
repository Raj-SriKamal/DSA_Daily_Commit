package DynamicProgramming;

import java.util.*;

/*
==================================================
Day 11 - Longest String Chain
LeetCode 1048
Difficulty: Medium
Pattern: LIS-style DP on Subsequences
==================================================

PROBLEM:

Given an array of words, find the length of the
longest string chain.

wordA is a predecessor of wordB if we can insert
exactly one character anywhere in wordA without
changing the order of the other characters to make
wordB.

--------------------------------------------------
EXAMPLE:

Input:

["a","b","ba","bca","bda","bdca"]

Output:

4

Explanation:

"a" -> "ba" -> "bda" -> "bdca"

Length = 4

--------------------------------------------------
KEY IDEA:

This is similar to LIS.

In normal LIS:

    nums[j] < nums[i]

In String Chain:

    predecessor of word exists

Instead of checking every previous word, we can
generate every possible predecessor by removing
one character from the current word.

ALGORITHM:

1. Sort words by length.
2. Create HashMap dp.
3. For every word:
      currentLength = 1
4. Remove each character.
5. Build predecessor.
6. If predecessor exists in dp:
      currentLength =
          max(currentLength,
              dp[predecessor] + 1)
7. Store dp[word].
8. Update global answer.
9. Return answer.

--------------------------------------------------
COMPLEXITY:

Let L = maximum word length.

Sorting:
O(n log n)

For every word:
L possible predecessors.

Building each predecessor:
O(L)

Therefore approximately:

Time: O(n log n + n * L²)

Space: O(n)

==================================================
*/

public class LongestStringChain {

    public static int longestStrChain(String[] words) {

        // Shorter words must be processed first
        Arrays.sort(
            words,
            (a, b) -> Integer.compare(
                a.length(),
                b.length()
            )
        );

        // dp[word] = longest chain ending at word
        Map<String, Integer> dp = new HashMap<>();

        int answer = 1;

        for (String word : words) {

            // Every word alone forms a chain
            int currentLength = 1;

            // Try removing every character
            for (int i = 0; i < word.length(); i++) {

                String predecessor =
                    word.substring(0, i)
                    + word.substring(i + 1);

                // If predecessor exists
                if (dp.containsKey(predecessor)) {

                    currentLength = Math.max(
                        currentLength,
                        dp.get(predecessor) + 1
                    );
                }
            }

            // Store best chain ending at this word
            dp.put(word, currentLength);

            // Update global answer
            answer = Math.max(
                answer,
                currentLength
            );
        }

        return answer;
    }

    public static void main(String[] args) {

        String[] words = {
            "a",
            "b",
            "ba",
            "bca",
            "bda",
            "bdca"
        };

        System.out.println(
            longestStrChain(words)
        );
    }
}