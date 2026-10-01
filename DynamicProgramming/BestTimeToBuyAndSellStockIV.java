package DynamicProgramming;

public class BestTimeToBuyAndSellStockIV {

    public static int maxProfit(int k, int[] prices) {

        if (prices == null || prices.length == 0 || k == 0) {
            return 0;
        }

        int n = prices.length;

        // If k is large, it becomes unlimited transactions
        if (k >= n / 2) {
            int profit = 0;

            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }

            return profit;
        }

        // dp[t][0] = max profit after at most t transactions
        // while NOT holding a stock
        //
        // dp[t][1] = max profit after at most t transactions
        // while holding a stock

        int[][] dp = new int[k + 1][2];

        for (int t = 0; t <= k; t++) {
            dp[t][1] = Integer.MIN_VALUE;
        }

        for (int price : prices) {

            for (int t = 1; t <= k; t++) {

                // Sell today
                dp[t][0] = Math.max(
                        dp[t][0],
                        dp[t][1] + price);

                // Buy today
                dp[t][1] = Math.max(
                        dp[t][1],
                        dp[t - 1][0] - price);
            }
        }

        return dp[k][0];
    }

    public static void main(String[] args) {

        int k = 2;
        int[] prices = { 3, 2, 6, 5, 0, 3 };

        System.out.println(maxProfit(k, prices));
    }
}