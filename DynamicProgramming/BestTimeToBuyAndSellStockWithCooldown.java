package DynamicProgramming;

public class BestTimeToBuyAndSellStockWithCooldown {

    public static int maxProfit(int[] prices) {

        int n = prices.length;

        if (n == 0) {
            return 0;
        }

        int hold = -prices[0];
        int sold = 0;
        int rest = 0;

        for (int i = 1; i < n; i++) {

            int previousHold = hold;
            int previousSold = sold;
            int previousRest = rest;

            hold = Math.max(previousHold, previousRest - prices[i]);

            sold = previousHold + prices[i];

            rest = Math.max(previousRest, previousSold);
        }

        return Math.max(sold, rest);
    }

    public static void main(String[] args) {

        int[] prices = {1, 2, 3, 0, 2};

        System.out.println(maxProfit(prices));
    }
}