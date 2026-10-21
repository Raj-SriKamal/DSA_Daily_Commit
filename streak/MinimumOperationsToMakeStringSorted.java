
package streak;

public class MinimumOperationsToMakeStringSorted {

    public int minOperations(String s) {
        int n = s.length();

        boolean isSorted = true;

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                isSorted = false;
                break;
            }
        }

        if (isSorted) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        char minChar = 'z';
        char maxChar = 'a';

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c < minChar) {
                minChar = c;
            }

            if (c > maxChar) {
                maxChar = c;
            }
        }

        char first = s.charAt(0);
        char last = s.charAt(n - 1);

        if (first == minChar || last == maxChar) {
            return 1;
        }

        for (int i = 1; i < n - 1; i++) {
            char mid = s.charAt(i);

            if (mid == minChar || mid == maxChar) {
                return 2;
            }
        }

        return 3;
    }

    public static void main(String[] args) {
        MinimumOperationsToMakeStringSorted solution =
            new MinimumOperationsToMakeStringSorted();

        System.out.println(solution.minOperations("cba"));
    }
}
