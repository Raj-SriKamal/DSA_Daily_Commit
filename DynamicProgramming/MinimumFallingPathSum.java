package DynamicProgramming;

public class MinimumFallingPathSum {

    public static int minFallingPathSum(int[][] matrix) {

        int n = matrix.length;

        int[][] dp = new int[n][n];

        // First row
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
        }

        // Remaining rows
        for (int i = 1; i < n; i++) {

            for (int j = 0; j < n; j++) {

                int minAbove = dp[i - 1][j];

                // Upper-left
                if (j > 0) {
                    minAbove = Math.min(
                        minAbove,
                        dp[i - 1][j - 1]
                    );
                }

                // Upper-right
                if (j < n - 1) {
                    minAbove = Math.min(
                        minAbove,
                        dp[i - 1][j + 1]
                    );
                }

                dp[i][j] = matrix[i][j] + minAbove;
            }
        }

        // Minimum value in the last row
        int answer = dp[n - 1][0];

        for (int j = 1; j < n; j++) {
            answer = Math.min(answer, dp[n - 1][j]);
        }

        return answer;
    }

    public static void main(String[] args) {

        int[][] matrix = {
            {2, 1, 3},
            {6, 5, 4},
            {7, 8, 9}
        };

        System.out.println(
            minFallingPathSum(matrix)
        );
    }
}