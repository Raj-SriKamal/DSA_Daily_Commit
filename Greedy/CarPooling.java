
package Greedy;

public class CarPooling {

    public boolean carPooling(int[][] trips, int capacity) {
        int[] change = new int[1001];

        for (int[] trip : trips) {
            int passengers = trip[0];
            int from = trip[1];
            int to = trip[2];

            change[from] += passengers;
            change[to] -= passengers;
        }

        int passengersInCar = 0;

        for (int i = 0; i <= 1000; i++) {
            passengersInCar += change[i];

            if (passengersInCar > capacity) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        CarPooling solution = new CarPooling();

        int[][] trips = {
            {2, 1, 5},
            {3, 3, 7}
        };

        System.out.println(solution.carPooling(trips, 4));
    }
}
