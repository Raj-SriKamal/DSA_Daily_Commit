package DynamicProgramming;

public class BestTimeToBuyAndSellStockII {

    public static int maxProfit(int[] prices) {

        int cash = 0;
        int hold = -prices[0];

        for (int i = 1; i < prices.length; i++) {

            int newCash = Math.max(cash, hold + prices[i]);

            int newHold = Math.max(hold, cash - prices[i]);

            cash = newCash;
            hold = newHold;
        }

        return cash;
    }

    public static void main(String[] args) {

        int[] prices = { 7, 1, 5, 3, 6, 4 };

        System.out.println(maxProfit(prices));
    }
}