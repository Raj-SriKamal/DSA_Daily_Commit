
package Greedy;

import java.util.Arrays;

public class BagOfTokens {

    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);

        int left = 0;
        int right = tokens.length - 1;

        int score = 0;
        int maxScore = 0;

        while (left <= right) {

            // Cheapest token: gain score
            if (power >= tokens[left]) {
                power -= tokens[left];
                score++;

                maxScore = Math.max(maxScore, score);
                left++;

            // Most expensive token: regain power
            } else if (score > 0) {
                power += tokens[right];
                score--;
                right--;

            } else {
                break;
            }
        }

        return maxScore;
    }

    public static void main(String[] args) {
        BagOfTokens solution = new BagOfTokens();

        int[] tokens = {100, 200, 300, 400};
        System.out.println(solution.bagOfTokensScore(tokens, 200));
    }
}
