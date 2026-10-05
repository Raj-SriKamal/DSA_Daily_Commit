package DynamicProgramming;

public class PalindromePartitioningII {
    public int minCut(String s) {
        int n = s.length();
        // Step 1: Find all palindromic substrings
        boolean[][] pal = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)
                        && (j - i <= 1 || pal[i + 1][j - 1])) {
                    pal[i][j] = true;
                }
            }
        }
        // Step 2: Minimum cuts
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (pal[0][i]) {
                dp[i] = 0;
                continue;
            }
            dp[i] = i; // maximum possible cuts
            for (int j = 1; j <= i; j++) {
                if (pal[j][i]) {
                    dp[i] = Math.min(
                            dp[i],
                            dp[j - 1] + 1);
                }
            }
        }
        return dp[n - 1];
    }

}
