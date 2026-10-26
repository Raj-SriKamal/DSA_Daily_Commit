
package Greedy;

public class JumpGameII {

    public int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {

            // Find the farthest index reachable
            farthest = Math.max(farthest, i + nums[i]);

            // Current jump range has ended
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }

        return jumps;
    }

    public static void main(String[] args) {
        JumpGameII solution = new JumpGameII();

        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(solution.jump(nums));
    }
}
