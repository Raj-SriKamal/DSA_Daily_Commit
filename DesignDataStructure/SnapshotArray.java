package DesignDataStructure;

import java.util.*;

public class SnapshotArray {

    List<int[]>[] history;
    int snapId;

    @SuppressWarnings("unchecked")
    public SnapshotArray(int length) {
        history = new ArrayList[length];

        for (int i = 0; i < length; i++) {
            history[i] = new ArrayList<>();
        }

        snapId = 0;
    }

    public void set(int index, int val) {
        List<int[]> list = history[index];

        if (!list.isEmpty()
                && list.get(list.size() - 1)[0] == snapId) {
            list.get(list.size() - 1)[1] = val;
        } else {
            list.add(new int[]{snapId, val});
        }
    }

    public int snap() {
        return snapId++;
    }

    public int get(int index, int snap_id) {
        List<int[]> list = history[index];

        int left = 0;
        int right = list.size() - 1;
        int answer = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (list.get(mid)[0] <= snap_id) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (answer == -1) {
            return 0;
        }

        return list.get(answer)[1];
    }

    public static void main(String[] args) {
        SnapshotArray snapshotArr = new SnapshotArray(3);

        snapshotArr.set(0, 5);
        System.out.println(snapshotArr.snap()); // 0

        snapshotArr.set(0, 6);
        System.out.println(snapshotArr.get(0, 0)); // 5

        System.out.println(snapshotArr.snap()); // 1
        System.out.println(snapshotArr.get(0, 1)); // 6
    }
}