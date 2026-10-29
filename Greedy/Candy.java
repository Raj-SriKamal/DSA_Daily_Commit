
package Greedy;

import java.util.Arrays;

public class Candy {

    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];

        Arrays.fill(candies, 1);

        // Left to right: handle increasing ratings
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Right to left: handle decreasing ratings
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(
                    candies[i],
                    candies[i + 1] + 1
                );
            }
        }

        int total = 0;
        for (int count : candies) {
            total += count;
        }

        return total;
    }

    public static void main(String[] args) {
        Candy solution = new Candy();
        System.out.println(solution.candy(new int[]{1, 0, 2}));
    }
}
