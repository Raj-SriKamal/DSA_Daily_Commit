package streak;

public class MinimumAddToMakeParenthesesValid {

    public static int minAddToMakeValid(String s) {

        int open = 0;
        int additions = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    additions++;
                }
            }
        }

        return additions + open;
    }

    public static void main(String[] args) {

        String s = "()))((";

        System.out.println(minAddToMakeValid(s));
    }
}