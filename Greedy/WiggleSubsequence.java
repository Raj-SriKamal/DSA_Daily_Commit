
package Greedy;

public class WiggleSubsequence {

    public int wiggleMaxLength(int[] nums) {
        int up = 1;
        int down = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }

        return Math.max(up, down);
    }

    public static void main(String[] args) {
        WiggleSubsequence solution = new WiggleSubsequence();

        int[] nums = {1, 7, 4, 9, 2, 5};
        System.out.println(solution.wiggleMaxLength(nums));
    }
}
