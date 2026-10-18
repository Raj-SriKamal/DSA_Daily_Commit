
package streak;

import java.util.*;

public class NumberOfWaysToReorderArrayToGetSameBST {

    private static final long MOD = 1_000_000_007L;
    private long[][] table;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        table = new long[n][n];

        // Build Pascal's triangle for combinations
        for (int i = 0; i < n; i++) {
            table[i][0] = 1;

            for (int j = 1; j <= i; j++) {
                table[i][j] =
                    (table[i - 1][j - 1] + table[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        // Subtract the original ordering
        return (int) ((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> list) {
        if (list.size() <= 2) {
            return 1;
        }

        int root = list.get(0);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Divide values according to the BST root
        for (int i = 1; i < list.size(); i++) {
            int val = list.get(i);

            if (val < root) {
                left.add(val);
            } else {
                right.add(val);
            }
        }

        // Choose positions for the left-subtree elements
        long combinations =
            table[left.size() + right.size()][left.size()];

        long leftWays = dfs(left);
        long rightWays = dfs(right);

        return (((combinations * leftWays) % MOD) * rightWays) % MOD;
    }

    public static void main(String[] args) {
        NumberOfWaysToReorderArrayToGetSameBST solution =
            new NumberOfWaysToReorderArrayToGetSameBST();

        int[] nums = {2, 1, 3};

        System.out.println(solution.numOfWays(nums));
    }
}
