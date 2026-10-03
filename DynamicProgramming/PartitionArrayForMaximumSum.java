package DynamicProgramming;

public class PartitionArrayForMaximumSum {

    public static int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int maxValue = 0;

            for (int len = 1; len <= k && i - len >= 0; len++) {
                maxValue = Math.max(maxValue, arr[i - len]);

                dp[i] = Math.max(
                    dp[i],
                    dp[i - len] + maxValue * len
                );
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int[] arr = {1, 15, 7, 9, 2, 5, 10};
        int k = 3;

        System.out.println(maxSumAfterPartitioning(arr, k));
    }
}