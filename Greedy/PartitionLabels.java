
package Greedy;

import java.util.*;

public class PartitionLabels {

    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];

        // Store the last occurrence of each character
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);

            if (i == end) {
                result.add(end - start + 1);
                start = i + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        PartitionLabels solution = new PartitionLabels();

        System.out.println(
            solution.partitionLabels("ababcbacadefegdehijhklij")
        );
    }
}
