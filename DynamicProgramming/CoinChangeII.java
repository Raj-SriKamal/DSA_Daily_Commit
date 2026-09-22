package DynamicProgramming;

public class CoinChangeII {
    public static int change(int amount, int[] coins) {

        // dp[a] = number of combinations
        // that make amount a
        int[] dp = new int[amount + 1];

        // Base case:
        // One way to make amount 0:
        // choose nothing
        dp[0] = 1;

        // Process each coin one by one
        for (int coin : coins) {

            // Start from the current coin
            // and move towards the target amount
            for (int a = coin; a <= amount; a++) {

                // Add combinations that can be
                // formed using the current coin
                dp[a] += dp[a - coin];
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {

        int[] coins = { 1, 2, 5 };
        int amount = 5;

        System.out.println(change(amount, coins));
    }

}
