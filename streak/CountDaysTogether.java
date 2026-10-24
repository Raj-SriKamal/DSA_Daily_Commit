
package streak;

public class CountDaysTogether {

    // Days in each month for a non-leap year
    private final int[] daysInMonth = {
        31, 28, 31, 30, 31, 30,
        31, 31, 30, 31, 30, 31
    };

    public int countDaysTogether(
        String arriveAlice,
        String leaveAlice,
        String arriveBob,
        String leaveBob
    ) {
        int aStart = convertToDayOfYear(arriveAlice);
        int aEnd = convertToDayOfYear(leaveAlice);
        int bStart = convertToDayOfYear(arriveBob);
        int bEnd = convertToDayOfYear(leaveBob);

        int start = Math.max(aStart, bStart);
        int end = Math.min(aEnd, bEnd);

        return start <= end ? end - start + 1 : 0;
    }

    private int convertToDayOfYear(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int dayOfYear = 0;

        // Add days from all completed previous months
        for (int i = 0; i < month - 1; i++) {
            dayOfYear += daysInMonth[i];
        }

        // Add the days in the current month
        return dayOfYear + day;
    }

    public static void main(String[] args) {
        CountDaysTogether solution = new CountDaysTogether();

        System.out.println(
            solution.countDaysTogether(
                "08-15", "08-18", "08-16", "08-19"
            )
        );
    }
}
