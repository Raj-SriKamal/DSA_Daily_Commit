
package streak;

public class SortPermutation {

    public int sortPermutation(int[] nums) {
        int ans = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                ans &= nums[i];
            }
        }

        return Math.max(ans, 0);
    }

    public static void main(String[] args) {
        SortPermutation solution = new SortPermutation();

        int[] nums = {1, 0, 2};
        System.out.println(solution.sortPermutation(nums));
    }
}
