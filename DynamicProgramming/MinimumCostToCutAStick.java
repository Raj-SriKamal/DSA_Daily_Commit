package DynamicProgramming;

import java.util.*;

class Solution {
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        int[] points = new int[m + 2];
        points[0] = 0;
        points[m + 1] = n;
        for (int i = 0; i < m; i++) {
            points[i + 1] = cuts[i];
        }
        Arrays.sort(points);
        int[][] dp = new int[m + 2][m + 2];
        for (int len = 2; len < m + 2; len++) {
            for (int i = 0; i + len < m + 2; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    int cost = dp[i][k]
                            + dp[k][j]
                            + (points[j] - points[i]);
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[0][m + 1];
    }
}
