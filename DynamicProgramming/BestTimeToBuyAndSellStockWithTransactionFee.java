package DynamicProgramming;

public class BestTimeToBuyAndSellStockWithTransactionFee {

    public static int maxProfit(int[] prices, int fee) {

        int hold = -prices[0];
        int cash = 0;

        for (int i = 1; i < prices.length; i++) {

            int previousHold = hold;
            int previousCash = cash;

            hold = Math.max(previousHold, previousCash - prices[i]);

            cash = Math.max(previousCash, previousHold + prices[i] - fee);
        }

        return cash;
    }

    public static void main(String[] args) {

        int[] prices = { 1, 3, 2, 8, 4, 9 };
        int fee = 2;

        System.out.println(maxProfit(prices, fee));
    }
}