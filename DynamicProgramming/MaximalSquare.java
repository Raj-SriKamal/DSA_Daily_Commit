package DynamicProgramming;

public class MaximalSquare {

    public static int maximalSquare(char[][] matrix) {

        if (matrix.length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = new int[rows][cols];

        int maxSide = 0;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                // Only process cells containing '1'
                if (matrix[i][j] == '1') {

                    // First row or first column
                    if (i == 0 || j == 0) {

                        dp[i][j] = 1;

                    } else {

                        dp[i][j] = 1 + Math.min(
                                dp[i - 1][j],
                                Math.min(
                                        dp[i][j - 1],
                                        dp[i - 1][j - 1]));
                    }

                    maxSide = Math.max(
                            maxSide,
                            dp[i][j]);
                }
            }
        }

        return maxSide * maxSide;
    }

    public static void main(String[] args) {

        char[][] matrix = {
                { '1', '0', '1', '0', '0' },
                { '1', '0', '1', '1', '1' },
                { '1', '1', '1', '1', '1' },
                { '1', '0', '0', '1', '0' }
        };

        System.out.println(
                maximalSquare(matrix));
    }
}