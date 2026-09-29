package DynamicProgramming;

public class MinimumASCIIDeleteSum {

    public static int minimumDeleteSum(String s1, String s2) {

        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        // If s2 is empty, delete all characters from s1
        for (int i = 1; i <= m; i++) {
            dp[i][0] =
                dp[i - 1][0] +
                s1.charAt(i - 1);
        }

        // If s1 is empty, delete all characters from s2
        for (int j = 1; j <= n; j++) {
            dp[0][j] =
                dp[0][j - 1] +
                s2.charAt(j - 1);
        }

        // Build DP table
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

                    // Characters match, keep both
                    dp[i][j] = dp[i - 1][j - 1];

                } else {

                    int deleteFromS1 =
                        s1.charAt(i - 1) + dp[i - 1][j];

                    int deleteFromS2 =
                        s2.charAt(j - 1) + dp[i][j - 1];

                    dp[i][j] = Math.min(
                        deleteFromS1,
                        deleteFromS2
                    );
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {

        String s1 = "sea";
        String s2 = "eat";

        System.out.println(minimumDeleteSum(s1, s2));
    }
}
