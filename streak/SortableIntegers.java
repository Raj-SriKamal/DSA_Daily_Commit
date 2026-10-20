
package streak;

import java.util.*;

public class SortableIntegers {

    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        List<Integer> divisors = new ArrayList<>();

        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);

                if (i * i != n) {
                    divisors.add(n / i);
                }
            }
        }

        int totalSum = 0;

        for (int k : divisors) {
            if (isSortable(nums, k, n)) {
                totalSum += k;
            }
        }

        return totalSum;
    }

    private boolean isSortable(int[] nums, int k, int n) {
        int prevMax = Integer.MIN_VALUE;

        for (int start = 0; start < n; start += k) {
            int currentMin = Integer.MAX_VALUE;
            int currentMax = Integer.MIN_VALUE;
            int descendingTransitions = 0;

            for (int i = 0; i < k; i++) {
                int currIdx = start + i;
                int nextIdx = start + (i + 1) % k;

                if (nums[currIdx] > currentMax) {
                    currentMax = nums[currIdx];
                }

                if (nums[currIdx] < currentMin) {
                    currentMin = nums[currIdx];
                }

                if (nums[currIdx] > nums[nextIdx]) {
                    descendingTransitions++;
                }
            }

            if (descendingTransitions > 1) {
                return false;
            }

            if (prevMax > currentMin) {
                return false;
            }

            prevMax = currentMax;
        }

        return true;
    }

    public static void main(String[] args) {
        SortableIntegers solution = new SortableIntegers();

        int[] nums = {1, 2, 3, 4};
        System.out.println(solution.sortableIntegers(nums));
    }
}
